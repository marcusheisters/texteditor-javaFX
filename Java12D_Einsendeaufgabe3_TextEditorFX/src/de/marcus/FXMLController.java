package de.marcus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import javafx.application.Platform;
import javafx.event.ActionEvent;

public class FXMLController {
	//Instanzvariablen
	@FXML private TextArea editor;
	Stage meineStage;
	//Für Die Warnungen
	Alert meinDialog, bearbeitenFortsetzen;
	
	//Die MEthode setStage
	//Sie setzt die Stage auf den übergebenen Wert
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}
	

	//Die Methode zum Erstellen eines neuen Dokuments
	//Es wird gefragt, ob wirklich gelöscht werden soll
	//wenn ja, Editor leeren
	//wenn nein, Infomeldung ausgeben
	//danach löscht sie das Editorfeld über die Methode clear
	@FXML protected void neuKlick(ActionEvent e) {
		meinDialog = new JaNeinDialog("Neues Dokument erstellen",
				"Warnung",
				"Wollen sie wirklich das aktuelle Dokument löschen?");
		if (meinDialog.getResult() == ButtonType.YES) 
			editor.clear();
		if (meinDialog.getResult() == ButtonType.NO) {
			bearbeitenFortsetzen = new Alert(AlertType.INFORMATION, "Bearbeiten Fortsetzen");
			bearbeitenFortsetzen.showAndWait();
		}
	}
	
	//Die Methode Öffnen eines Laden-Dialogs
	@FXML protected void ladenKlick(ActionEvent e) {
		//Sie erzeugt eine Instanz der Klasse FileChooser
		//Filtert nach Textdateien
		//und gibt eine Datei zurück
		
		//Die Instanz erzeugen
		FileChooser oeffnenDialog = new FileChooser();
		oeffnenDialog.setTitle("Datei öffnen");
		//Den Filter setzen
		oeffnenDialog.getExtensionFilters()
			.add(new ExtensionFilter("Textdateien", "*.txt"));
		//Den Ordner Eclipse/Workspace standardmäßig oeffnen
		oeffnenDialog.setInitialDirectory(
				new File(System.getProperty("user.home")));
		//Öffnendialog anzeigen und Ergebnis beschafffen
		File datei = oeffnenDialog.showOpenDialog(meineStage);
		//wurde etwas ausgewählt?
		if (datei != null)
			//Den Inhalt über eigene Methode einlesen
			editor.setText(datenLesen(datei));
	}
	
	//Die Methode zum anzeigen des SpeichernDialogs
	@FXML protected void speichernKlick (ActionEvent e) {
		//Sie erzeugt eine Instanz der Klasse FileChooser
		//Filtert nach Textdateien
		//beschafft eine Datei aus dem Dialog
		//und führt dann die Methode datenSchreiben aus
		
		FileChooser speichernDialog = new FileChooser();
		speichernDialog.setTitle("Datei speichern");
		//Die Filter setzen
		speichernDialog.getExtensionFilters().add(
				new ExtensionFilter("Textdateien", "*.txt"));
		//Speicherndialog anzeigen und Ergebnis beschaffen
		File datei = speichernDialog.showSaveDialog(meineStage);
		if (datei != null)
			//Den Inhalt über eigene Methode speichern
			datenSchreiben(datei);
	}
	
	//Die Methode zum Schreiben
	//sie erhält die Datei, die geschrieben werden soll
	private void datenSchreiben(File dateiSchreiben) {
		//Eine Instanz der Klasse FileWriter mit der Datei erzeugen
		try (FileWriter tempDatei = new FileWriter(dateiSchreiben)) {
			//Der Inhalt des Textfelde wird direkt in die Datei 
			//geschrieben
			tempDatei.write(editor.getText());			
		}
		catch (IOException e) {
			meinDialog = new Alert (AlertType.ERROR,
					"Beim Speichern ist ein Fehler aufgetreten.");
			meinDialog.setHeaderText("Fehler beim Speichern");
			meinDialog.showAndWait();
		}
	}
	
	//Die Methode zum Laden
	//sie erhält die Datei, die gelesen werden soll
	//und liefert eine Zeichenkette mit dem Inhalt zurück
	private String datenLesen(File dateiLesen) {
		//gelesen wird als int
		int zeichen = 0;
		StringBuilder text = new StringBuilder();
		//Eine Instanz der Klasse FileReader mit der Datei erzeugen
		try (FileReader tempDatei = new FileReader(dateiLesen)) {
			//Solange das Ende der Datei nicht erreicht ist,
			//werden die Zeichen einzeln eingelesen und 
			//an einen StringBuilder angehängt
			zeichen = tempDatei.read();
			while (zeichen != -1) {
				//Zeichen wird in den Typ char umgewandelt
				text.append((char)zeichen);
				zeichen = tempDatei.read();					
			}
		}
		catch (IOException e) {
			meinDialog = new Alert(AlertType.ERROR,
					"Beim Laden ist ein Fehler aufgetreten.");
			meinDialog.setHeaderText("Fehler beim Laden");
			meinDialog.showAndWait();
					
		}			
	return (text.toString());
	}
	//Die Methode zum ausschneiden cut()
	@FXML protected void ausscheidenKlick() {
		editor.cut();
	}
	//Die Methode zum einfügen 
	@FXML protected void einfuegenKlick() {
		editor.paste();
	}
	//Die Methode zum kopieren
	@FXML protected void kopierenKlick() {
		editor.copy();
	}
	
	//Die Methode zum beenden
	@FXML protected void beendenKlick(ActionEvent e) {
		Platform.exit();
	}
}
