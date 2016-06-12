package br.ufrn.imd.view.ponto;

import java.io.IOException;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PontoGestorEditarController {
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfUsuario;
	@FXML
	private TextField cbDataEnvio;
	@FXML
	private TextField cbDataAnalise;
	@FXML
	private TextField tfSituacaoAtual;
	@FXML
	private TextField taObservacao;
	@FXML
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private ChoiceBox cbTipoPonto;
	@FXML
	private TextField tfUsuarioAnalise;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
