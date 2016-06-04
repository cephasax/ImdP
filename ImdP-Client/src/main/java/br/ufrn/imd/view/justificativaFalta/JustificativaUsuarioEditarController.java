package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class JustificativaUsuarioEditarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cbTipoJustificativa;
	@FXML
	private DatePicker dpDataInicial;
	@FXML
	private DatePicker dpDataFinal;
	@FXML
	private TextArea taDescricao;
	@FXML
	private ComboBox cbVinculo;
	@FXML
	private TextField tfComprovante;
	@FXML
	private Button btnProcurar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
