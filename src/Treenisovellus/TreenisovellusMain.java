package Treenisovellus;

import javafx.application.Application;
import javafx.stage.Stage;
import treeni.Treeni;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Onni
 * @version 7.10.2022
 * 
 * Treenisovelluksen käynnistysohjelma.
 */
public class TreenisovellusMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TreenisovellusGUIView.fxml"));
            final Pane root = ldr.load();
            final TreenisovellusGUIController treeniCtrl = (TreenisovellusGUIController) ldr.getController();;
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("treenisovellus.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Treenisovellus");
            primaryStage.show();
            
            Treeni treeni = new Treeni();
            treeniCtrl.setTreeni(treeni);
            

            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}