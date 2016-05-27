package br.ufrn.imd.view.ponto;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class PontoListarController {
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableView tblPontos;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
