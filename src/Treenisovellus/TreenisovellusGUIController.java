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
        Dialogs.showMessageDialog("Vielä ei osata lisätä treeniliikkeitä");
    }

    @FXML void handleUusiSuoritus() {
        Dialogs.showMessageDialog("Vielä ei osata lisätä suorituksia");
    }
    
    @FXML void handleVersio() {
        Dialogs.showMessageDialog("Vielä ei osata katsoa versiota");
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia vielä");
    }

}