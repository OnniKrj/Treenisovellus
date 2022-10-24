package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Onni
 * @version 24.10.2022
 *
 */
public class UusiLiikeController implements  ModalControllerInterface<String> {
    @FXML  void handleTallenna() {
        tallenna();
    }
    
    
    //========================================
    
    private void tallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa");
    }
    

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
}
