package Treenisovellus;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kanta.Tietue;
import treeni.Suoritus;
import treeni.Treeni;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/**
 * @author Onni
 * @version 21.10.2022
 * @param <TYPE> a
 *
 */
public class TietueDialogController<TYPE extends Tietue> implements ModalControllerInterface<TYPE>,Initializable  {


    
    @FXML TextField editPvm;
    @FXML Label labelVirhe;
    @FXML GridPane gridSuoritus;
    @FXML ScrollPane panelSuoritus;
    //@FXML TextField editSarjat;
    //@FXML TextField editToistot;
    //@FXML TextField editPaino;
    //@FXML TextField editLiikeNro;
    
    
    @FXML private void handleOK() {
        if (tietueKohdalla != null && tietueKohdalla.anna(tietueKohdalla.ekaKentta()).trim().equals("")) {
            naytaVirhe("P‰iv‰m‰‰r‰ ei voi olla tyhj‰");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        tietueKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public TYPE getResult() {
        return tietueKohdalla;
    }

    @Override
    public void handleShown() {
        //
        
    }

    @Override
    public void setDefault(TYPE oletus) {
        this.tietueKohdalla = oletus;
        naytaTietue(edits, tietueKohdalla);
    }
    
    //====================================================================================
    
    
    private TYPE tietueKohdalla;
    private TextField[] edits;
    private static Suoritus apusuoritus = new Suoritus();
    private int kentta = 0;

    
    /**
     * Palautetaan komponentin id:st saatava luku
     * @param obj tutkittava komponentti
     * @param oletus mik arvo, jos id ei ole kunnollinen
     * @return Komponentin id lukuna
     */
    public static int getFieldId(Object obj, int oletus) {
        if ( !( obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        return Mjonot.erotaInt(node.getId().substring(1),oletus);
    }
    
    
    /**
     * Luodaan GridPaneen suorituksen tiedot
     * @param gridSuoritus Mihin tiedot luodaan
     * @return luodut tekstikentt
     */
    public static TextField[] luoKentat(GridPane gridSuoritus) {
        gridSuoritus.getChildren().clear();
        TextField[] edits = new TextField[apusuoritus.getKenttia()];
        
        for (int i = 0, k = apusuoritus.ekaKentta(); k < apusuoritus.getKenttia(); k++, i++) {
            Label label = new Label(apusuoritus.getKysymys(k));
            gridSuoritus.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e"+k);
            gridSuoritus.add(edit, 1, i);
        }
        return edits;
    }
    
    
    private void alusta() {
        edits = luoKentat(gridSuoritus);
        for (TextField edit : edits)
            if (edit != null)
                edit.setOnKeyReleased( e -> kasitteleMuutosSuoritukseen((TextField)(e.getSource())));
        panelSuoritus.setFitToHeight(true);
        
    }
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    
    /*
     * Ksitelln suoritukseen tullut muutos
     * @param k Kentt jota muokataan, toistaiseksi vain pvm muokattavana
     */
    private void kasitteleMuutosSuoritukseen(TextField edit) {
        if (tietueKohdalla == null) return;
        String s = edit.getText();
        int k = getFieldId(edit, apusuoritus.ekaKentta());
        String virhe = tietueKohdalla.aseta(k, s);
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
     * @param edits Taulukko jossa on tarvittavat tekstikentt
     * @param tietue N‰ytett‰v‰ suoritus
     */
    public static void naytaTietue(TextField[] edits, Tietue tietue) {
        
        if (tietue == null) return;
        for (int k = tietue.ekaKentta(); k < tietue.getKenttia(); k++) {
            edits[k].setText(tietue.anna(k));
            
        }
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
     * @param <TYPE> a
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Mit dataan nytetn oletuksena
     * @param kentta b
     * @return null jos painetaa Peruuta, muuten tytetty tietue
     */
    public static<TYPE extends Tietue> TYPE kysyTietue(Stage modalityStage, TYPE oletus, int kentta) {
        return ModalController.<TYPE, TietueDialogController<TYPE>>showModal(
                TietueDialogController.class.getResource("MuokkaaSuoritusta.fxml"),
                "Treeni",
                modalityStage, oletus,
                ctrl -> ctrl.setKentta(kentta)
                );
    }

}

