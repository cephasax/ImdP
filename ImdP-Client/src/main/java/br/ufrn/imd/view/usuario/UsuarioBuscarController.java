package br.ufrn.imd.view.usuario;

import java.io.IOException;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TableView;

public class UsuarioBuscarController {
	@FXML
	private TableView tblUsuarios;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNome;
	@FXML
	private ComboBox tfUnidade;
	@FXML
	private ComboBox tfSetor;
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
