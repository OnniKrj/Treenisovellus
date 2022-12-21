package Treenisovellus;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import treeni.Liike;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka ksittelemn treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController implements Initializable {
    
    @FXML private GridPane gridSuoritus;
    @FXML private TextField editPvm;

    

    @FXML void handleUusiSuoritus() {
        
        SuorituksetController.avaaSuoritukset(null, treeni);
       
    }
    
    
    @FXML void handleTietoja() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        muokkaaSuoritusta();
    }
    
    @FXML
    void handleTulosta() {
        //
    }
    
    
    @FXML void handlePoistuEtusivulta() {
        suljeSovellus();
    }
    
    @FXML private ScrollPane panelSuoritus;
    
    //================================================================================
        
    private Treeni treeni;
    
    
    
    
    /**
     * @param treeni Treeni -luokka tuodaan kyttliittymlle
     */
    public void setTreeni(Treeni treeni) {
        this.treeni = treeni;
        lue();
        
    }
    
    
    private String lueTiedosto(String nimi) {
        try {
            treeni.lueTiedostosta(nimi);
            
            return null;
            
        } catch (SailoException e) {
            
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;

        }

    }
    
    /**
     * Nimetn luettava tiedosto ja kutsutaan lueTiedosto -metodia
     */
    public void lue() {
        String nimi = "treeni";
        lueTiedosto(nimi);
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
     *  Nytetn suoritusten muokkaus ikkuna
     */
    public void muokkaaSuoritusta() {
        SuorituksetController.avaaSuoritukset(null, treeni);
        
    }
    

    
    /**
     * Suljetaan koko sovellus
     */
    public void suljeSovellus() {
        Platform.exit();
        
    }
    
    
    /**
     * Siirrytn pnkymn
     */
    public void avaaSovellus() {
        Dialogs.showMessageDialog("Sovellus ei aukea tst viel");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
        
    }
    
}
