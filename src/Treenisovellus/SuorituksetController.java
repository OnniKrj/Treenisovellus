package Treenisovellus;

import java.io.PrintStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import treeni.Liike;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 8.11.2022
 * Suoritukset nkymn ohjuri
 */
public class SuorituksetController implements ModalControllerInterface<Treeni>, Initializable {
    
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;
    @FXML StringGrid<Liike> tableLiikkeet;
    @FXML TextField editPvm;
    
    
    @FXML void handleDefaultCancel() {
        ModalController.closeStage(panelSuoritus);
    }

    @FXML void handleDefaultOK() {
        tallenna();
    }
    
    @FXML
    void handleMuokkaa() {
        muokkaa();
    }
    
    @FXML void handleLisaaUusiSuoritus() {
        uusiSuoritus();
    }
    
    @FXML
    void handleLisaaUusiLiike() {
        uusiLiike();
    }
    
    @FXML
    void handleTallenna() {
        tallenna();
    }
    
    @FXML private ScrollPane panelSuoritus;
    
    
    //==============================================================================

    private Treeni treeni;
    private Suoritus suoritusKohdalla;
    private TextField[] edits;
    
    //TODO: uusi listChooser Liikkeet ikkunalle!!
   
    
    
    private void hae(int tnro) {
        
        //Collection<Suoritus> suoritukset;
        chooserSuoritukset.clear();
        
        int index = 0;
        for (int i = 0; i < treeni.getSuorituksia(); i++) {
            Suoritus suoritus = treeni.annaSuoritus(i);
            if (suoritus.getTreeniNro() == tnro) index = i;
            chooserSuoritukset.add(""+suoritus.getTreeniNro(), suoritus);
        }
        chooserSuoritukset.setSelectedIndex(index);
    }
    
    private void uusiSuoritus() {
        Suoritus uusi = new Suoritus();
        uusi.kirjaa();
        uusi.taytaTreeniTiedoilla();
        try {
            treeni.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }
        hae(uusi.getTreeniNro());
    }
    
    /**
     * Uuden liikkeen lisminen
     */
    public void uusiLiike() {
        if (suoritusKohdalla == null) return;
        Liike liike = new Liike();
        liike.kirjaa();
        liike.TaytaLiikeTiedoilla(suoritusKohdalla.getTreeniNro());
        treeni.lisaa(liike);
        hae(suoritusKohdalla.getTreeniNro());
        
    }
    
    private void naytaSuoritus() {
        suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        
        MuokkaaSuoritustaController.naytaSuoritus(edits, suoritusKohdalla);
        naytaLiikkeet(suoritusKohdalla);
    }
    
    private void naytaLiikkeet(Suoritus suoritus) {
        tableLiikkeet.clear();
        if (suoritus == null) return;
        List<Liike> liikkeet = treeni.annaLiikkeet(suoritus);
        if (liikkeet.size() == 0) return;
        for (Liike liik : liikkeet)
            naytaLiike(liik);
    }
    
    private void naytaLiike(Liike liik) {
        String[] rivi = liik.toString().split("\\|");
        tableLiikkeet.add(liik, rivi[1], rivi[2],rivi[3],rivi[4],rivi[5]);
    }
    
    
    
    private void muokkaa() {
        suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        try {
            Suoritus suoritus = MuokkaaSuoritustaController.kysySuoritus(null, suoritusKohdalla.clone());
            if (suoritus == null) return;
            treeni.korvaaTaiLisaa(suoritus);
            hae(suoritus.getTreeniNro());
        } catch (CloneNotSupportedException | SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());;
        }

    }
    
    /**
     * @param os Tulostusvirta
     * @param suoritus Tulostettava suoritus
     */
    public void tulosta(PrintStream os, final Suoritus suoritus) {
        os.println("---------------------------------------");
        suoritus.tulosta(os);
        os.println("---------------------------------------");
        List<Liike> liikkeet = treeni.annaLiikkeet(suoritus);
        for (Liike liike : liikkeet)
            liike.tulosta(os);
    }
    
    private void tallenna() {
        try {
            treeni.tallenna();
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        hae(0);
        
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
        
        
    }

    
    
    private void alusta() {
        
        panelSuoritus.setFitToHeight(true);
        chooserSuoritukset.clear();
        chooserSuoritukset.addSelectionListener(e -> naytaSuoritus());
        TextField[] edts = {editPvm};
        edits = edts;
    }
    
    
    /**
     * @param modalityStage todo 
     * @param oletus todo
     * @return todo
     */
    public static Treeni avaaSuoritukset(Stage modalityStage, Treeni oletus) {
        return ModalController.<Treeni, SuorituksetController>showModal(SuorituksetController.class.getResource("Suoritukset.fxml"),
                "Treenin lisys",
                modalityStage, oletus,
                null 
            );
    }

    @Override
    public void setDefault(Treeni arg0) {
        this.treeni = arg0;
        
    }
    


}
