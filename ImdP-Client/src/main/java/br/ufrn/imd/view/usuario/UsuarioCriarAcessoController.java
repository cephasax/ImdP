package br.ufrn.imd.view.usuario;

import java.io.IOException;

import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.view.fingerprint.DigitalPersona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
	private Button btnDigital;
	@FXML
	private ImageView imgFoto;
	@FXML
	private PasswordField tfSenha;
	@FXML
	private PasswordField tfConfSenha;

	private ImdAuth imdAuth;

	private UsuarioService service = new UsuarioService();

	private Usuario usuario = new Usuario();

	@FXML
	public void handleBtnCadastrar() throws IOException {
		if (confereSenha()) {
			usuario.setLogin(tfLogin.getText());
			usuario.setSenha(tfSenha.getText());
			usuario.setFoto("foto");
			int resultado;
			resultado = service.usuarioCriar(usuario);
			if (resultado == 200) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Dado criado!");

				alert.showAndWait();
				imdAuth.iniciarUsuarioListar();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Ocorreu um erro!");

				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	@FXML
	public void handleBtnTirarFoto() throws IOException {
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
	
	@FXML
	public void handleBtnDigital() throws IOException {
		DigitalPersona dp = new DigitalPersona();
		dp.principal();
		usuario.setDigital(dp.get());
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean confereSenha() {
		if (tfSenha.getText().equals(tfConfSenha.getText())) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!\nSenhas não conferem.");
			alert.showAndWait();
			return false;
		}
	}
}
