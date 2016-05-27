package br.ufrn.imd.view.tipoJustificativa;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoJustificativaBuscarController {
	@FXML
	private TableView tblTipoJustificativa;
	@FXML
	private Button btnExcluir;
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
