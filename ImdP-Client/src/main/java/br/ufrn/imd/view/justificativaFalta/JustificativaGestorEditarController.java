package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class JustificativaGestorEditarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfVinculo;
	@FXML
	private TextField tfUsuario;
	@FXML
	private TextField tfUnidade;
	@FXML
	private TextField tfSetor;
	@FXML
	private TextField tfDataEnvio;
	@FXML
	private TextField tfDataInicial;
	@FXML
	private TextField tfDataFinal;
	@FXML
	private TextField tfTipo;
	@FXML
	private TextField tfSituacaoAtual;
	@FXML
	private TextField taDescricao;
	@FXML
	private Button btnAbrirComprovante;
	@FXML
	private TextField taObservacao;
	@FXML
	private ChoiceBox cbAprovar;
	@FXML
	private TextField tfHorasAprovadas;
	@FXML
	private Slider sliderHorasAprovadas;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
