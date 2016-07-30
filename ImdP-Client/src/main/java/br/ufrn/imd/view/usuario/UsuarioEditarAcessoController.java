package br.ufrn.imd.view.usuario;

import java.io.IOException;

import br.ufrn.imd.dominio.ImpressaoDigital;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.ImpressaoDigitalService;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.view.fingerprint.DigitalPersona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UsuarioEditarAcessoController {
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

	private Usuario usuario = new Usuario();

	private UsuarioService service = new UsuarioService();

	private ImpressaoDigitalService digitalService = new ImpressaoDigitalService();

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

		tfLogin.setText(usuario.getLogin());
		tfSenha.setText(usuario.getSenha());
		tfConfSenha.setText(usuario.getSenha());

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
		System.out.println("to aqui.");
		DigitalPersona dp = new DigitalPersona();
		dp.principal();

		ImpressaoDigital impDigital = new ImpressaoDigital(usuario, dp.get());
		digitalService.impressaoDigitalCriar(impDigital);
//		usuario.setDigital(dp.get());
	}
	
	@FXML
	public void handleEditar() throws IOException {
		if (confereSenha()) {
			usuario.setLogin(tfLogin.getText());
			usuario.setSenha(tfSenha.getText());
			usuario.setFoto("foto");
			int resultado;
			resultado = service.usuarioEditar(usuario);
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
