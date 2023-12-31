package Treenisovellus;

import static Treenisovellus.TietueDialogController.getFieldId;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import treeni.Liike;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 8.11.2022
 * Suoritukset nkymn ohjuri
 */
public class SuorituksetController implements ModalControllerInterface<Treeni>, Initializable {
    
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;
    @FXML StringGrid<Liike> tableLiikkeet;
    @FXML TextField editPvm;
    @FXML private GridPane gridSuoritus;
    @FXML ComboBoxChooser<String> cbKentat;
    @FXML TextField hakuehto;
    
    
    
    @FXML void handleDefaultCancel() {
        ModalController.closeStage(panelSuoritus);
    }

    @FXML void handleDefaultOK() {
        tallenna();
    }
    
    @FXML
    void handleMuokkaa() {
        muokkaa(kentta);
    }
    
    
    @FXML
    void handleTulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea()); 
    }
    
    @FXML void handleTietoja() {
        naytaVersio();
    }

    
    @FXML void handleLisaaUusiSuoritus() {
        uusiSuoritus();
    }
    
    @FXML
    void handleLisaaUusiLiike() {
        uusiLiike();
    }
    
    @FXML
    void handleTallenna() {
        tallenna();
    }
    
    
    @FXML
    private void handleHakuehto() {
        hae(0);
    }
    
    
    @FXML
    void handlePoistaLiike() {
        poistaLiike();
    }
    
    @FXML
    void handlePoistaSuoritus() {
        poistaSuoritus();
    }
    
    @FXML private ScrollPane panelSuoritus;
    
    
    //==============================================================================

    private Treeni treeni;
    private Suoritus suoritusKohdalla;
    private TextField[] edits;
    private static Liike apuliike = new Liike();
    private int kentta = 0;
    private static Suoritus apusuoritus = new Suoritus();
    
   
    
    private void hae(int tnr) {
        int tnro = tnr;
        if ( tnro <= 0 ) {
            Suoritus kohdalla = suoritusKohdalla;
            if ( kohdalla != null ) tnro = kohdalla.getTreeniNro();
        }
        
        int k = cbKentat.getSelectedIndex() + apusuoritus.ekaKentta();
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";
        
        
        chooserSuoritukset.clear();
        
        int index = 0;
        Collection<Suoritus> suoritukset;
        try {
            suoritukset = treeni.etsi(ehto, k);
            int i = 0;
            for (Suoritus suoritus : suoritukset) {
                if (suoritus.getTreeniNro() == tnro) index = i;
                chooserSuoritukset.add(""+suoritus.getTreeniNro(), suoritus);
                i++;
            }
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Suorituksen hakemisessa ongelmia! " + e.getMessage());
        }
        chooserSuoritukset.setSelectedIndex(index);
        
        
    }
    
    private void uusiSuoritus() {
        try {
            Suoritus uusi = new Suoritus();
            uusi = TietueDialogController.kysyTietue(null, uusi, 1);
            if (uusi == null) return;
            uusi.kirjaa();
            treeni.lisaa(uusi);
            hae(uusi.getTreeniNro());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
    }
    
    /**
     * Uuden liikkeen lis��minen
     */
    public void uusiLiike() {
        if (suoritusKohdalla == null) return;
        Liike uusi =  new Liike(suoritusKohdalla.getTreeniNro());
        uusi = TietueDialogController.kysyTietue(null, uusi, 0);
        if (uusi == null) return;
        uusi.kirjaa();
        treeni.lisaa(uusi);
        naytaLiikkeet(suoritusKohdalla);
        tableLiikkeet.selectRow(1000);
        
    }
    
    private void naytaSuoritus() {
        suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        
        TietueDialogController.naytaTietue(edits, suoritusKohdalla);
        naytaLiikkeet(suoritusKohdalla);
    }
    
    private void naytaLiikkeet(Suoritus suoritus) {
        tableLiikkeet.clear();
        if (suoritus == null) return;
        List<Liike> liikkeet = treeni.annaLiikkeet(suoritus);
        if (liikkeet.size() == 0) return;
        for (Liike liik : liikkeet)
            naytaLiike(liik);
    }
    
    private void naytaLiike(Liike liik) {
        
        int kenttia = liik.getKenttia(); 
        String[] rivi = new String[kenttia-liik.ekaKentta()]; 
        for (int i=0, k=liik.ekaKentta(); k < kenttia; i++, k++) 
            rivi[i] = liik.anna(k); 
        tableLiikkeet.add(liik,rivi);
        //String[] rivi = liik.toString().split("\\|");
        //tableLiikkeet.add(liik, rivi[1], rivi[2],rivi[3],rivi[4],rivi[5]);
    }
    
    
    
    private void muokkaa(int k) {
        suoritusKohdalla = chooserSuoritukset.getSelectedObject();
        if (suoritusKohdalla == null) return;
        try {
            Suoritus suoritus = TietueDialogController.kysyTietue(null, suoritusKohdalla.clone(), k);
            if (suoritus == null) return;
            treeni.korvaaTaiLisaa(suoritus);
            hae(suoritus.getTreeniNro());
        } catch (CloneNotSupportedException | SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());;
        }

    }
    
    private void muokkaaLiiketta() {
        int r = tableLiikkeet.getRowNr();
        if (r < 0) return;
        Liike liik = tableLiikkeet.getObject();
        if (liik == null) return;
        int k = tableLiikkeet.getColumnNr() + liik.ekaKentta();
        try {
            liik = TietueDialogController.kysyTietue(null, liik.clone(), k);
            if (liik == null) return;
            treeni.korvaaTaiLisaa(liik);
            naytaLiikkeet(suoritusKohdalla);
            tableLiikkeet.selectRow(r);
        } catch (CloneNotSupportedException e) {
            // TODO: handle exception
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia lis��misess�: " + e.getMessage());
        }
    }
    
    
    /**
     * @param os Tulostusvirta
     * @param suoritus Tulostettava suoritus
     */
    public void tulosta(PrintStream os, final Suoritus suoritus) {
        os.println("---------------------------------------");
        suoritus.tulosta(os);
        os.println("---------------------------------------");
        List<Liike> liikkeet = treeni.annaLiikkeet(suoritus);
        for (Liike liike : liikkeet)
            liike.tulosta(os);
    }
    
    private String tallenna() {
        try {
            treeni.tallenna();
            return null;
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
            return e.getMessage();
        }
    }
    
    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        hae(0);
        
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    
    private void poistaLiike() {
        int rivi = tableLiikkeet.getRowNr();
        if ( rivi < 0 ) return;
        Liike liike = tableLiikkeet.getObject();
        if (liike == null) return;
        treeni.poistaLiike(liike);
        naytaLiikkeet(suoritusKohdalla);
        int liikkeita = tableLiikkeet.getItems().size();
        if (rivi >= liikkeita) rivi = liikkeita -1;
        tableLiikkeet.getFocusModel().focus(rivi);
        tableLiikkeet.getSelectionModel().select(rivi);
        
    }
    
    private void poistaSuoritus() {
        Suoritus suoritus = suoritusKohdalla;
        if (suoritus == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko suoritus: " + suoritus.getTreeniNro(), "Kyll�","Ei")) return;
        treeni.poista(suoritus);
        int index = chooserSuoritukset.getSelectedIndex();
        hae(0);
        chooserSuoritukset.setSelectedIndex(index);
    }
    
    private void alusta() {
        
        
        chooserSuoritukset.clear();
        chooserSuoritukset.addSelectionListener(e -> naytaSuoritus());
        edits = TietueDialogController.luoKentat(gridSuoritus, apusuoritus);
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));
                edit.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaa(kentta);}); 
            }

        
        int eka = apuliike.ekaKentta(); 
        int lkm = apuliike.getKenttia(); 
        String[] headings = new String[lkm-eka]; 
        for (int i=0, k=eka; k<lkm; i++, k++) headings[i] = apuliike.getKysymys(k); 
        tableLiikkeet.initTable(headings); 
        tableLiikkeet.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        tableLiikkeet.setEditable(false); 
        tableLiikkeet.setPlaceholder(new Label("Ei viel� harrastuksia"));
        tableLiikkeet.setOnMouseClicked( e -> { if ( e.getClickCount() > 1 ) muokkaaLiiketta(); } );
        tableLiikkeet.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaaLiiketta();}); 
    }
    
    
    
    /**
     * Nytetn sovelluksen tiedot.
     */
    public void naytaVersio() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022s/ht/ontajoka#mtypuo4cyMgg");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
            
        } catch (IOException e) {
            return;

        }
    }
    
    
    /**
     * Tulostaa listassa olevat j�senet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki suoritukset");
            Collection<Suoritus> suoritukset = treeni.etsi("", -1); 
            for (Suoritus suoritus : suoritukset) { 
                tulosta(os, suoritus);
                os.println("\n\n");
            }
        } catch (SailoException ex) { 
            Dialogs.showMessageDialog("Suorituksen hakemisessa ongelmia! " + ex.getMessage()); 

        }

    }

    
    
    /**
     * @param modalityStage todo 
     * @param oletus todo
     * @return todo
     */
    public static Treeni avaaSuoritukset(Stage modalityStage, Treeni oletus) {
        return ModalController.<Treeni, SuorituksetController>showModal(SuorituksetController.class.getResource("Suoritukset.fxml"),
                "Treenin lis�ys",
                modalityStage, oletus,
                null 
            );
    }

    @Override
    public void setDefault(Treeni arg0) {
        this.treeni = arg0;
        
    }


}
