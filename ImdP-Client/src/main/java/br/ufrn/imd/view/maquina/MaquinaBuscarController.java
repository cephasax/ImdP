package br.ufrn.imd.view.maquina;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class MaquinaBuscarController {
	@FXML
	private TableView tblMaquinas;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnBuscar;
	@FXML
	private Font x1;
	@FXML
	private TextField tfNomeMaquina;
	@FXML
	private ComboBox cbUnidade;
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
