package br.ufrn.imd.view.cargo;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CargoBuscarController {
	@FXML
	private AnchorPane telaBuscaCargo;
	@FXML
	private TableView tblListaCargo;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfBuscaCargo;
	@FXML
	private Button btnBuscar;
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
