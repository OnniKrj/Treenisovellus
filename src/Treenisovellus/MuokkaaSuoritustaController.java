package Treenisovellus;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 21.10.2022
 *
 */
public class MuokkaaSuoritustaController  implements ModalControllerInterface<Suoritus>, Initializable {

    @FXML TextField editPvm;
    @FXML TextField naytaTreeniNro;
    @FXML TextField editSarjat;
    @FXML TextField editToistot;
    @FXML TextField editPaino;
    @FXML TextField editLiikeNro;
    
    
    @FXML private void handleOK() {
        //TODO: laita oikea k‰sittely
    }

    
    @FXML private void handleCancel() {
        ModalController.closeStage(editPvm);
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
    public void setDefault(Suoritus oletus) {
        this.suoritusKohdalla = oletus;
        naytaSuoritus(suoritusKohdalla);
    }
    
    //====================================================================================
    
    
    private Suoritus suoritusKohdalla;

    
    private void naytaSuoritus(Suoritus suoritus) {
        if (suoritus == null) return;
        naytaTreeniNro.setText(""+suoritus.getTreeniNro());
        editPvm.setText(suoritus.getPvm());
        editPaino.setText(""+suoritus.getPaino());
        editLiikeNro.setText(""+suoritus.getLiikeNro());
        editSarjat.setText(""+suoritus.getSarjaMaara());
        editToistot.setText(""+suoritus.getToistoMaara());
    }
    

    /**
     * Luodaan suorituksen muokkausdialogi ja palautetaan se muokattuna tai nullina
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Mit‰ dataan n‰ytet‰‰n oletuksena
     * @return null jos painetaa Peruuta, muuten t‰ytetty tietue
     */
    public static Suoritus kysySuoritus(Stage modalityStage, Suoritus oletus) {
        return ModalController.showModal(TreenisovellusGUIController.class.getResource("MuokkaaSuoritusta.fxml"), "Suoritus", modalityStage, oletus);
        
    }
}
