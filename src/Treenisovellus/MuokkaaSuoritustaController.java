package Treenisovellus;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 21.10.2022
 *
 */
public class MuokkaaSuoritustaController  implements ModalControllerInterface<Suoritus>, Initializable {

    @FXML private void handleOK() {
        //TODO: laita oikea käsittely
    }

    
    @FXML private void handleCancel() {
        //TODO: laita oikea käsittely
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Suoritus getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Suoritus arg0) {
        // TODO Auto-generated method stub
        
    }
}
