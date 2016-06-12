package br.ufrn.imd.view.unidade;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UnidadeCriarController {
	@FXML
	private TextField tfNomeUnidade;
	@FXML
	private ImageView ivLogo;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public UnidadeCriarController() {

	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

}
