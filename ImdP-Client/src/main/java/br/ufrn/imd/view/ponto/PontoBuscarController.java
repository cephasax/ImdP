package br.ufrn.imd.view.ponto;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PontoBuscarController {
	@FXML
	private TableView tblPontos;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNomeUsuario;
	@FXML
	private ComboBox cbUnidade;
	@FXML
	private ComboBox cbSetor;
	@FXML
	private Button btnExcluir;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
