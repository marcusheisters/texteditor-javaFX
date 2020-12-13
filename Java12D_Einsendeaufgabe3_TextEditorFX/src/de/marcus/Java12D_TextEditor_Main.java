package de.marcus;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Java12D_TextEditor_Main extends Application{
	@Override
	public void start (Stage meineStage) throws Exception {
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader meinLoader = new FXMLLoader(getClass()
				.getResource("sb_editor.fxml"));
		//Die Datei laden
		Parent root = meinLoader.load();
		//Den Controller beschaffen
		FXMLController meinController = meinLoader.getController();
		//Die Stage an den Controller übergeben
		meinController.setMeineStage(meineStage);
		//Scene setzen
		Scene meineScene = new Scene(root,400,400);
		//Scene mit Stage verbinden
		meineStage.setScene(meineScene);
		meineStage.setTitle("JavaFX Editor");
		
		//Stage maximieren und anzeigen
		meineStage.setMaximized(true);
		meineStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
