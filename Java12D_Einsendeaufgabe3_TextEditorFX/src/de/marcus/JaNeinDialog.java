package de.marcus;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class JaNeinDialog extends Alert {
	//Eine Klasse, welche einen Ja/Nein Dialog ainzeigt
	//Die anzuzeigenden Texte werden als Argumente �bergeben
	//Die Auswahl des Users wird zur�ckgegeben
	
	//Die Variablen f�r die anzuzeigenden Texte
	String �berschrift, text;
	//Die ButtonTypes
	ButtonType buttonTypeJa, buttonTypeNein;
	//Der Konstruktor
	
	JaNeinDialog(String titel, String header, String message) {
		//Der Dialog soll ein ConfirmationAlert werden
		//�ber den �bergeordneten Konstruktor werden der AlertType,
		//die anzuzeigende NAchricht sowie die beiden Buttons �bergeben
		super(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
		//Die Argumente verarbeiten und Titel, Header
		setTitle(titel);
		setHeaderText(header);
		//Den Dialog anzeigen und auf Eingabe warten
		//Der Dialog gibt ButtonType.YES oder ButtonType.NO zur�ck, je nachdem
		//was geklickt wurde		
		showAndWait();
		
	}
}
