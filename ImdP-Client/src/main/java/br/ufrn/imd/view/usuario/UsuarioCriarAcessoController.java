package br.ufrn.imd.view.usuario;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;

import javafx.scene.control.PasswordField;

public class UsuarioCriarAcessoController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfLogin;
	@FXML
	private Button btnFoto;
	@FXML
	private ImageView imgFoto;
	@FXML
	private PasswordField tfSenha;
	@FXML
	private PasswordField tfConfSenha;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
