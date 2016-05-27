package br.ufrn.imd.view.justificativaFalta;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class JustificativasListarController {
	@FXML
	private Button brnCancelar;
	@FXML
	private TableView tblJustificativasFaltas;
	@FXML
	private Button btnExcluir;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
