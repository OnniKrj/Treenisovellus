package Treenisovellus;

import java.awt.Dialog;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Onni
 * @version 24.10.2022
 *
 */
public class UusiSuoritusController implements ModalControllerInterface<String>, Initializable {
    
    
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
     * V�liaikainen toteutus tallenna -toiminnolle
     */
    public void tallenna() {
        Dialogs.showMessageDialog("Viel� ei osata tallentaa");
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
        
    }
    

                
    }
    
    
    
