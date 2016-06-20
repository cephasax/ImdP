package br.ufrn.imd.view.usuario;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UsuarioCriarController implements Initializable {
	@FXML
	private Button btnProximo;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfCPF;
	@FXML
	private ComboBox<String> cbSexo;
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
	private ComboBox<String> cbEstado;
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

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleProximo() throws IOException {
		LocalDate dataNasc = dpDataNascimento.getValue();
		Instant instantDN = Instant.from(dataNasc.atStartOfDay(ZoneId.systemDefault()));
		Date dateNasc = Date.from(instantDN);

		LocalDate dataRG = dpDataRG.getValue();
		Instant instantDRG = Instant.from(dataRG.atStartOfDay(ZoneId.systemDefault()));
		Date dateRG = Date.from(instantDRG);		
		
		Usuario usuario = new Usuario(tfNome.getText(), sexo(cbSexo.getSelectionModel().getSelectedItem()), dateNasc, tfCPF.getText(), tfRG.getText(), tfOrgaoRG.getText(), dateRG, cbEstado.getSelectionModel().getSelectedItem(), tfCNH.getText(), tfNomePai.getText(), tfNomeMae.getText(), tfEmail.getText(), tfTelefone1.getText(), tfTelefone2.getText());
		imdAuth.iniciarUsuarioCriarAcesso(usuario);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbSexo.getItems().addAll("Masculino", "Feminino");
		cbEstado.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
				"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");

	}

	public String sexo(String sexo) {
		if (sexo.equals("Masculino")) {
			return "M";
		} else {
			return "F";
		}
	}
}
