package Treenisovellus;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import treeni.Liike;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 24.10.2022
 *
 */
public class UusiLiikeController implements  ModalControllerInterface<Treeni>, Initializable {
    @FXML  void handleTallenna() {
        tallenna();
    }
    
    //========================================
    
    
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;
    @FXML private ListChooser<Liike> chooserLiikkeet;
    
    private void tallenna() {
        //Dialogs.showMessageDialog("Vielä ei osata tallentaa");
        
    }
    

    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param arg0 Ei käytössä
     */
    public void setDefault(@SuppressWarnings("unused") String arg0) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //uusiLiike(); // vaihda paikkaa
        
    }


    @Override
    public void setDefault(Treeni arg0) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @param modalityStage tayta
     * @param oletus tayta
     * @return tayta
     */
    public static Treeni avaaLiikkeet(Stage modalityStage, Treeni oletus) {
        return ModalController.<Treeni, UusiLiikeController>showModal(SuorituksetController.class.getResource("UusiLiike.fxml"),
                "Liikkeen lisäys",
                modalityStage, oletus,
                null 
            );
    }
    

}
