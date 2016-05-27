package br.ufrn.imd.view.justificativaFalta;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class JustificativasUsuarioListarController {
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView tblJustificativasFaltas;
	@FXML
	private Button btnExcluir;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
