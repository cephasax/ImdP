package br.ufrn.imd.view.ponto;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PontoAvulsoCriarController {
	@FXML
	private Button btnCriarPontoAvulso;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox tfUnidade;
	@FXML
	private ComboBox tfSetor;
	@FXML
	private DatePicker dpData;
	@FXML
	private ComboBox tfTipo;
	@FXML
	private TextField taObservacao;
	@FXML
	private ComboBox tfUsuario;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
