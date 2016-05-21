package br.ufrn.imd.view.justificativaFalta;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.control.DatePicker;

public class JustificativaCriarController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cbTipoJustificativa;
	@FXML
	private DatePicker dpDataInicial;
	@FXML
	private DatePicker dpDataFinal;
	@FXML
	private TextArea taDescricao;
	@FXML
	private ComboBox cbVinculo;
	@FXML
	private TextField tfComprovante;
	@FXML
	private Button btnProcurar;

}
