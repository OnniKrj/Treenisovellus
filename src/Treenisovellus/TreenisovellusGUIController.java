package Treenisovellus;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import treeni.SailoException;
import treeni.Suoritus;
import treeni.Treeni;

/**
 * @author Onni
 * @version 7.10.2022
 *
 * Luokka k‰sittelem‰‰n treenisovelluksen tapahtumia.
 */
public class TreenisovellusGUIController {
    
    @FXML private ListChooser<Suoritus> chooserSuoritukset;

    @FXML void handleUusiLiike() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("UusiLiike.fxml"),
                "Lis‰‰ liike", null, "");
    }

    @FXML void handleUusiSuoritus() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("UusiSuoritus.fxml"),
                "Lis‰‰ suoritus", null, "");
    }
    
    @FXML void handleVersio() {
        naytaVersio();
    }
    
    @FXML void handleMuokkaaSuoritusta() {
        ModalController.showModal(TreenisovellusGUIController.class.getResource("Suoritukset.fxml"),
                "Muokkaa", null, "");
        
    }
    
    
    @FXML void handlePoistuEtusivulta() {
        suljeSovellus();
    }
    
    //================================================================================
    
    private Treeni treeni;
    
    /**
     * Avataan suoritusten muokkaus ikkuna.
     */
    /*public void muokkaaSuoritusta() {
        Dialogs.showMessageDialog("Ei pysty muokkaamaan aiempia suorituksia viel‰");
    }*/
    
    /**
     * N‰ytet‰‰n sovelluksen tiedot.
     */
    public void naytaVersio() {
        Dialogs.showMessageDialog("Viel‰ ei osata katsoa versiota");
        
    }
    //TODO: V‰‰r‰ss‰ kontrollerissa? LUENTO 14! Pit‰‰ siirt‰‰ Suoritukset controlleriin
    private void hae(int treeniNro) {
        chooserSuoritukset.clear();
        
        int index = 0;
        for (int i = 0; i < treeni.getSuorituksia(); i++) {
            Suoritus suoritus = treeni.annaSuoritus(i);
            if (suoritus.getTreeniNro() == treeniNro) index = i;
            chooserSuoritukset.add("" + suoritus.getTreeniNro(), suoritus);
        }
        chooserSuoritukset.setSelectedIndex(index);
    }
    

    /**
     * N‰ytet‰‰n ikkuna uuden suoritusten lis‰‰mist‰ varten.
     * TODO: V‰‰r‰ss‰ controllerissa? LUENTO 14 ASIAA
     */
    public void uusiSuoritus() {
       
        Suoritus uusi = new Suoritus();
        uusi.kirjaa();
        uusi.taytaTreeniTiedoilla();
        try {
            treeni.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }
        hae(uusi.getTreeniNro());
                
    }
    
    
    /**
     *  N‰ytet‰‰n suoritusten muokkaus ikkuna
     */
    public void muokkaaSuoritusta() {
        //
    }
    
    
    /**
     * N‰ytet‰‰n uuden liikkeen lis‰ysikkuna
     */
    public void uusiLiike() {
        //
    }
    
    
    /**
     * Suljetaan koko sovellus
     */
    public void suljeSovellus() {
        Platform.exit();
        
        //Dialogs.showMessageDialog("Viel‰ ei pysty sulkemaan t‰st‰");
    }
    
    
    /**
     * Siirryt‰‰n p‰‰n‰kym‰‰n
     */
    public void avaaSovellus() {
        Dialogs.showMessageDialog("Sovellus ei aukea t‰st‰ viel‰");
    }
    
    
    /**
     * @param treeni Treeni jota k‰ytet‰‰n
     */
    public void setTreeni(Treeni treeni) {
        this.treeni = treeni;
    }
}