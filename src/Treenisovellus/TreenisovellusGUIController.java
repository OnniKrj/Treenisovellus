package Treenisovellus;

import java.io.PrintStream;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka k�sittelem��n treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController {
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;

    @FXML void handleUusiLiike() {
        UusiLiikeController.avaaLiikkeet(null, treeni);
    }

    @FXML void handleUusiSuoritus() {
        
        SuorituksetController.avaaSuoritukset(null, treeni);
       
    }
    
    @FXML
    void handlePaivitaLista() {
        paivita();
    }
    
    @FXML void handleVersio() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        muokkaaSuoritusta();
        
    }
    
    
    @FXML void handlePoistuEtusivulta() {
        suljeSovellus();
    }
    
    @FXML private ScrollPane panelSuoritus;
    
    //================================================================================
        
    private Treeni treeni;
    private TextArea areaSuoritus = new TextArea(); // TODO: poista lopuksi
    
    /**
     * @param treeni todo
     */
    public void setTreeni(Treeni treeni) {
        this.treeni = treeni;
        
    }
    
    
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
    
    private void naytaSuoritus() {
        Suoritus suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        
        areaSuoritus.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaSuoritus)) {
            suoritusKohdalla.tulosta(os);
        }
    }
    
    private void alusta() {
        panelSuoritus.setContent(areaSuoritus);
        areaSuoritus.setFont(new Font("Courier New", 12));
        panelSuoritus.setFitToHeight(true);
        chooserSuoritukset.clear();
        chooserSuoritukset.addSelectionListener(e -> naytaSuoritus());
        
    }
    
    /**
     * N�ytet��n sovelluksen tiedot.
     */
    public void naytaVersio() {
        Dialogs.showMessageDialog("Viel� ei osata katsoa versiota");
        
    }
    
    public void paivita() {
        alusta();
        naytaSuoritus();
    }
    
    
    
    /**
     *  N�ytet��n suoritusten muokkaus ikkuna
     */
    public void muokkaaSuoritusta() {
        SuorituksetController.avaaSuoritukset(null, treeni);
        //Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia viel�");
    }
    
    
    /**
     * N�ytet��n uuden liikkeen lis�ysikkuna
     */
    public void uusiLiike() {
        //
    }
    
    
    /**
     * Suljetaan koko sovellus
     */
    public void suljeSovellus() {
        Platform.exit();
        
        //Dialogs.showMessageDialog("Viel� ei pysty sulkemaan t�st�");
    }
    
    
    /**
     * Siirryt��n p��n�kym��n
     */
    public void avaaSovellus() {
        Dialogs.showMessageDialog("Sovellus ei aukea t�st� viel�");
    }
    
}
