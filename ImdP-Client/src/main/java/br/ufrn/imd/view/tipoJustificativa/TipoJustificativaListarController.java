package br.ufrn.imd.view.tipoJustificativa;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TipoJustificativaListarController {
	@FXML
	private TableView tblTiposJustificativas;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
