package br.ufrn.imd.view.vinculo;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class VinculoBuscarController {
	@FXML
	private TableView tblVinculos;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private Font x1;
	@FXML
	private TextField tfNome;
	@FXML
	private ComboBox cbUnidade;
	@FXML
	private ComboBox cbSetor;
	@FXML
	private Font x12;
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
