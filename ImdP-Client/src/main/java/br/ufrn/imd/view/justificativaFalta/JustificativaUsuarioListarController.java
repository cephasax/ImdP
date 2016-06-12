package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;

import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class JustificativaUsuarioListarController {
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<JustificativaFalta> tblJustificativasFaltas;
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
