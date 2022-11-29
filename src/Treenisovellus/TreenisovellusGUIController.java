package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka k‰sittelem‰‰n treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController {
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;

    @FXML void handleUusiLiike() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("UusiLiike.fxml"),
                "Lis‰‰ liike", null, "");
    }

    @FXML void handleUusiSuoritus() {
        
        SuorituksetController.avaaSuoritukset(null, treeni);
       
    }
    
    @FXML void handleVersio() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("Suoritukset.fxml"),
                "Muokkaa suoritusta", null, "");
        
    }
    
    
    @FXML void handlePoistuEtusivulta() {
        suljeSovellus();
    }
    
    //================================================================================
        
    private Treeni treeni;
    
    /**
     * @param treeni todo
     */
    public void setTreeni(Treeni treeni) {
        this.treeni = treeni;
        
    }
    
    
    
    /**
     * N‰ytet‰‰n sovelluksen tiedot.
     */
    public void naytaVersio() {
        Dialogs.showMessageDialog("Viel‰ ei osata katsoa versiota");
        
    }
    
    
    /**
     *  N‰ytet‰‰n suoritusten muokkaus ikkuna
     */
    public void muokkaaSuoritusta() {
        Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia viel‰");
    }
    
    
    /**
     * N‰ytet‰‰n uuden liikkeen lis‰ysikkuna
     */
    public void uusiLiike() {
        //
    }
    
    
    /**
     * Suljetaan koko sovellus
     */
    public void suljeSovellus() {
        Platform.exit();
        
        //Dialogs.showMessageDialog("Viel‰ ei pysty sulkemaan t‰st‰");
    }
    
    
    /**
     * Siirryt‰‰n p‰‰n‰kym‰‰n
     */
    public void avaaSovellus() {
        Dialogs.showMessageDialog("Sovellus ei aukea t‰st‰ viel‰");
    }
    
}
