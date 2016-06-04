package br.ufrn.imd.view.ponto;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PontoConfirmarController {
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cbVinculo;
	@FXML
	private Button btnAutenticar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
