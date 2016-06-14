package br.ufrn.imd.view.setor;

import java.io.IOException;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class SetorEditarController {
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Font x1;
	@FXML
	private TextField tfNomeSetor;
	@FXML
	private ComboBox<Unidade> cbUnidade;

	private ImdAuth imdAuth;

	private Setor setor = new Setor();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
