package br.ufrn.imd.view.usuario;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class UsuarioCriarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfCPF;
	@FXML
	private ComboBox cbSexo;
	@FXML
	private DatePicker dpDataNascimento;
	@FXML
	private TextField tfNomePai;
	@FXML
	private TextField tfNomeMae;
	@FXML
	private TextField tfRG;
	@FXML
	private DatePicker dpDataRG;
	@FXML
	private TextField tfOrgaoRG;
	@FXML
	private ComboBox tfEstado;
	@FXML
	private TextField tfCNH;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfTelefone1;
	@FXML
	private TextField tfTelefone2;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
