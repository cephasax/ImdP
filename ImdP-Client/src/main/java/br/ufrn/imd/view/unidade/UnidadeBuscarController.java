package br.ufrn.imd.view.unidade;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UnidadeBuscarController {
	@FXML
	private TableView tblUnidade;
	@FXML
	private Button btnExcluir;
	@FXML
	private TextField tfNomeUnidade;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
