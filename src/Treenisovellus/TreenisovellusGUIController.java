package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka k‰sittelem‰‰n treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController {

    @FXML void handleUusiLiike() {
        uusiLiike();
    }

    @FXML void handleUusiSuoritus() {
        uusiSuoritus();
    }
    
    @FXML void handleVersio() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        muokkaaSuoritusta();
        
    }
    
    @FXML void handleAvaaSovellus() {
        avaaSovellus();
    }
    
    @FXML void handlePoistuEtusivulta() {
        suljeSovellus();
    }
    
    //================================================================================
    
    /**
     * Avataan suoritusten muokkaus ikkuna.
     */
    /*public void muokkaaSuoritusta() {
        Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia viel‰");
    }*/
    
    /**
     * N‰ytet‰‰n sovelluksen tiedot.
     */
    public void naytaVersio() {
        Dialogs.showMessageDialog("Viel‰ ei osata katsoa versiota");
        
    }

    /**
     * N‰ytet‰‰n ikkuna uuden suoritusten lis‰‰mist‰ varten.
     */
    public void uusiSuoritus() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("UusiSuoritus.fxml"),
                "Lis‰‰ suoritus", null, "");
    }
    
    /**
     *  N‰ytet‰‰n suoritusten muokkaus ikkuna
     */
    public void muokkaaSuoritusta() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("Suoritukset.fxml"),
                "Muokkaa", null, "");
    }
    
    /**
     * N‰ytet‰‰n uuden liikkeen lis‰ysikkuna
     */
    public void uusiLiike() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("UusiLiike.fxml"),
                "Lis‰‰ liike", null, "");
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