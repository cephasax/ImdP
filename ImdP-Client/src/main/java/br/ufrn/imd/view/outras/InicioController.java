package br.ufrn.imd.view.outras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InicioController {

	private ImdAuth imdAuth;

	@FXML
	private Button btnLogar;

	@FXML
	private TextField txtLogin;

	public InicioController() {
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param ImdAuth
	 * @throws IOException
	 */
	@FXML
	public void mudandoDeTela() throws IOException {
		System.out.println("teste");
		imdAuth.iniciarUnidadeCriar();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

}
