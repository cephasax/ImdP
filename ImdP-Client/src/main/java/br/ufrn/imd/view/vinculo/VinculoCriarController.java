package br.ufrn.imd.view.vinculo;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VinculoCriarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cbSetor;
	@FXML
	private TextField tfCargaHorariaSemanal;
	@FXML
	private TextField tfCargaHorarioMensal;
	@FXML
	private ComboBox cbCargo;
	@FXML
	private ComboBox cbUnidade;
	@FXML
	private CheckBox checkboxAtivo;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
