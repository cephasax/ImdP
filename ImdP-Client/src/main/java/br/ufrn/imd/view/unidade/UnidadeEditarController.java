package br.ufrn.imd.view.unidade;

import java.io.IOException;

import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UnidadeService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UnidadeEditarController {
	@FXML
	private TextField tfUnidade;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;
	
	private Unidade unidade = new Unidade();
	
	private UnidadeService service = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
