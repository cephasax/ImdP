package br.ufrn.imd.view.vinculo;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class VinculoListarController {
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView tblVinculos;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
