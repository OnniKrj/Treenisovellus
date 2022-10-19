package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author Onni
 * @version 7.10.2022
 *
 */
public class TreenisovellusGUIController {

    @FXML void handleUusiLiike() {
        Dialogs.showMessageDialog("Viel� ei osata lis�t� treeniliikkeit�");
    }

    @FXML void handleUusiSuoritus() {
        Dialogs.showMessageDialog("Viel� ei osata lis�t� suorituksia");
    }
    
    @FXML void handleVersio() {
        Dialogs.showMessageDialog("Viel� ei osata katsoa versiota");
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia viel�");
    }

}