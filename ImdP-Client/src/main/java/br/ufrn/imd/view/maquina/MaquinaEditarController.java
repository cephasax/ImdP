package br.ufrn.imd.view.maquina;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class MaquinaEditarController {
	@FXML
	private Font x1;
	@FXML
	private TextField tfNomeMaquina;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfIp1;
	@FXML
	private ComboBox cbUnidade;
	@FXML
	private TextField tfIp2;
	@FXML
	private TextField tfIp3;
	@FXML
	private TextField tfIp4;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
