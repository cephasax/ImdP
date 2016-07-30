package br.ufrn.imd.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dialog {
	private int resultado;
	private Alert alert;

	public Dialog(int numero) {
		this.resultado = numero;
	}

	public boolean alerta() {
		if (resultado == 200) {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ação concluída!");

			alert.showAndWait();
			return true;
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
			return false;
		}
	}
}
