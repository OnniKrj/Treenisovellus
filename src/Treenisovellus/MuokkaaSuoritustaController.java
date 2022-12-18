package Treenisovellus;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import treeni.Suoritus;
import treeni.Treeni;
import javafx.scene.control.Label;

/**
 * @author Onni
 * @version 21.10.2022
 *
 */
public class MuokkaaSuoritustaController  implements ModalControllerInterface<Suoritus>, Initializable {


    
    @FXML TextField editPvm;
    @FXML Label labelVirhe;
    //@FXML TextField editSarjat;
    //@FXML TextField editToistot;
    //@FXML TextField editPaino;
    //@FXML TextField editLiikeNro;
    
    
    @FXML private void handleOK() {
        if (suoritusKohdalla != null && suoritusKohdalla.getPvm().trim().equals("")) {
            naytaVirhe("P‰iv‰m‰‰r‰ ei voi olla tyhj‰");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        suoritusKohdalla = null;
        ModalController.closeStage(editPvm);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Suoritus getResult() {
        return suoritusKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Suoritus oletus) {
        this.suoritusKohdalla = oletus;
        naytaSuoritus(edits, suoritusKohdalla);
    }
    
    //====================================================================================
    
    
    private Suoritus suoritusKohdalla;
    private TextField[] edits;

    
    private void alusta() {
        edits = new TextField[]{editPvm};
        int i = 0;
        for (TextField edit : edits) {
            final int k = i++;
            edit.setOnKeyReleased(e -> kasitteleMuutosSuoritukseen(k, edit));
        }
        
    }
    
    
    /*
     * K‰sitell‰‰n suoritukseen tullut muutos
     * @param k Kentt‰ jota muokataan, toistaiseksi vain pvm muokattavana
     */
    private void kasitteleMuutosSuoritukseen(int k, TextField edit) {
        if (suoritusKohdalla == null) return;
        String s = edit.getText();
        String virhe = suoritusKohdalla.aseta(k, s);
        if (virhe != null) {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit, "");
            edit.getStyleClass().add("normaali");
            naytaVirhe("");
        }
    }
    
    
    /**
     * @param edits Taulukko jossa on tarvittavat tekstikent‰t
     * @param suoritus N‰ytett‰v‰ suoritus
     */
    public static void naytaSuoritus(TextField[] edits, Suoritus suoritus) {
        
        if (suoritus == null) return;
        edits[0].setText(suoritus.getPvm());
        //edits[1].setText(""+suoritus.getLiikeNro());
        //edits[2].setText(""+suoritus.getPaino());
        //edits[3].setText(""+suoritus.getSarjaMaara());
        //edits[4].setText(""+suoritus.getToistoMaara()); 
    }
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("Virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
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
