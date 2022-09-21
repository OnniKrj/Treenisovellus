/**
 * 
 * @author Onni
 * asd
 */
module Treenisovellus {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens fxTreenisovellus to javafx.graphics, javafx.fxml;
}
