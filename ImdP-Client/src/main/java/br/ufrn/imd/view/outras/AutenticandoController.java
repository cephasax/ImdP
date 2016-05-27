package br.ufrn.imd.view.outras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;

public class AutenticandoController {
	@FXML
	private ImageView imgAutenticando;
	@FXML
	private ProgressBar progressbar;

	private ImdAuth imdAuth;

	
	public AutenticandoController() {
	}

	@FXML
	public void mudandoDeTela() throws IOException {
		System.out.println("teste");
		imdAuth.iniciarAutenticacao();
	}

	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
