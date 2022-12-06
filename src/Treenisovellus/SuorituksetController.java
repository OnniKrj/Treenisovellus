package Treenisovellus;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import treeni.Liike;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 8.11.2022
 * Suoritukset n‰kym‰n ohjuri
 */
public class SuorituksetController implements ModalControllerInterface<Treeni>, Initializable {
    
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;
    
    
    @FXML void handleDefaultCancel() {
        //
    }

    @FXML void handleDefaultOK() {
        //
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
    private TextArea areaSuoritus = new TextArea(); // TODO: poista lopuksi
    
    /*
    private void naytaSuoritus() {
        suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        
        areaSuoritus.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaSuoritus)) {
            suoritusKohdalla.tulosta(os);
        }
    }*/
    
    private void hae(int tnro) {
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
     * Uuden liikkeen lis‰‰minen
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
        
        areaSuoritus.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaSuoritus)) {
            suoritusKohdalla.tulosta(os);
            List<Liike> liikkeet = treeni.annaLiikkeet(suoritusKohdalla);
            for (Liike liike : liikkeet)
                liike.tulosta(os);
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
        Dialogs.showMessageDialog("Viel‰ ei osata tallentaa!");
    }
    
    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }
    
    
    private void alusta() {
        panelSuoritus.setContent(areaSuoritus);
        areaSuoritus.setFont(new Font("Courier New", 12));
        panelSuoritus.setFitToHeight(true);
        chooserSuoritukset.clear();
        chooserSuoritukset.addSelectionListener(e -> naytaSuoritus());
        
    }
    
    
    //T‰h‰n metodi, jota kutsutaan p‰‰kontrollerista
    /**
     * @param modalityStage todo 
     * @param oletus todo
     * @return todo
     */
    public static Treeni avaaSuoritukset(Stage modalityStage, Treeni oletus) {
        return ModalController.<Treeni, SuorituksetController>showModal(SuorituksetController.class.getResource("Suoritukset.fxml"),
                "Treenin lis‰ys",
                modalityStage, oletus,
                null 
            );
    }

    @Override
    public void setDefault(Treeni arg0) {
        this.treeni = arg0;
        
    }
    

}
