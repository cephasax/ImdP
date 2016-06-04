package br.ufrn.imd.view.maquina;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MaquinaListarController {
	@FXML
	private TableView tblMaquinas;
	@FXML
	private Button btnExcluir;
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
