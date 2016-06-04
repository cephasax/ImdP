package br.ufrn.imd.view.tipoJustificativa;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TipoJustificativaEditarController {
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
