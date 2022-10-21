package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka k‰sittelem‰‰n treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController {

    @FXML void handleUusiLiike() {
        Dialogs.showMessageDialog("Viel‰ ei osata lis‰t‰ treeniliikkeit‰");
    }

    @FXML void handleUusiSuoritus() {
        Dialogs.showMessageDialog("Viel‰ ei osata lis‰t‰ suorituksia");
    }
    
    @FXML void handleVersio() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("Suoritukset.fxml"),
                "Muokkaa", null, "");
        
    }
    
    @FXML void handleAvaaSovellus() {
        Dialogs.showMessageDialog("Sovellus ei aukea t‰st‰ viel‰");
    }
    
    @FXML void handlePoistuEtusivulta() {
        Dialogs.showMessageDialog("Viel‰ ei osata poistua t‰st‰");
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

}