package br.ufrn.imd.view.outras;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InicioController {

	private ImdAuth imdAuth;

	@FXML
	private Button btnLogar;

	@FXML
	private TextField txtLogin;

	@FXML
	private Text lblCliqueAqui;

	@FXML
	public void handleBtnLogin() throws IOException {
		imdAuth.iniciarPontoNormalCriar();
	}

	@FXML
	public void handleCliqueAqui() throws IOException {
		imdAuth.iniciarUsuarioCriarAcesso();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

}
