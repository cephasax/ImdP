package br.ufrn.imd.view.setor;

import java.io.IOException;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class SetorBuscarController {
	@FXML
	private TableView<Setor> tblSetor;
	@FXML
	private Button btnCancelar;
	@FXML
	private Font x1;
	@FXML
	private TextField tfNomeSetor;
	@FXML
	private Button btnBuscar;
	@FXML
	private ComboBox<Unidade> cbUnidade;
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
