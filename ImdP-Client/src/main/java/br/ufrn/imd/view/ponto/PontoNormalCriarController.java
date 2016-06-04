package br.ufrn.imd.view.ponto;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PontoNormalCriarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfCPF;
	@FXML
	private PasswordField tfSenha;

	private ImdAuth imdAuth;

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleBtnCadastrarPonto() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
