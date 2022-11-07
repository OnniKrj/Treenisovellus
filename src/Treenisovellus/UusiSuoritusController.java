package Treenisovellus;

import java.awt.Dialog;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Onni
 * @version 24.10.2022
 *
 */
public class UusiSuoritusController implements ModalControllerInterface<String> {
    
    @FXML void handleTallenna() {
        tallenna();
    }
    
 
    
    //==============================================

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    


    /**
     * Väliaikainen toteutus tallenna -toiminnolle
     */
    public void tallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa");
    }
    
}
