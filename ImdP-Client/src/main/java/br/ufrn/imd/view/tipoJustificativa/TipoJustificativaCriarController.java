package br.ufrn.imd.view.tipoJustificativa;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TipoJustificativaCriarController {
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
