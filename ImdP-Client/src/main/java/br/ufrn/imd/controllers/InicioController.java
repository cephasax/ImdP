package br.ufrn.imd.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.Initializable;

public class InicioController implements Initializable {

	private ImdAuth imdAuth;

	/**
	 * O construtor é chamado antes do método inicialize().
	 */

	public InicioController() {
	}

	/**
	 * Inicializa a classe controller. Este método é chamado automaticamente
	 * após o arquivo fxml ter sido carregado.
	 */
	private void initialize() {
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
