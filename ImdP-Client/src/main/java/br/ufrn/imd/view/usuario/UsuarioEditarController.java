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

public class UsuarioEditarController implements Initializable {
	@FXML
	private Button btnConfirmar;
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

	private Usuario usuario = new Usuario();

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

		LocalDate dataNascimento = usuario.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataRg = usuario.getDataExpedicaoRg().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		tfNome.setText(usuario.getNome());
		cbSexo.setValue(sexoReverso(usuario.getSexo()));
		dpDataNascimento.setValue(dataNascimento);
		tfNomePai.setText(usuario.getNomePai());
		tfNomeMae.setText(usuario.getNomeMae());
		tfCPF.setText(usuario.getCpf());
		tfRG.setText(usuario.getRg());
		dpDataRG.setValue(dataRg);
		tfOrgaoRG.setText(usuario.getOrgaoExpedicaoRg());
		cbEstado.setValue(usuario.getEstadoRg());
		tfCNH.setText(usuario.getCnh());
		tfEmail.setText(usuario.getEmail());
		tfTelefone1.setText(usuario.getTelefone1());
		tfTelefone2.setText(usuario.getTelefone2());

	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleEditar() throws IOException {
		LocalDate dataNasc = dpDataNascimento.getValue();
		Instant instantDN = Instant.from(dataNasc.atStartOfDay(ZoneId.systemDefault()));
		Date dateNasc = Date.from(instantDN);

		LocalDate dataRG = dpDataRG.getValue();
		Instant instantDRG = Instant.from(dataRG.atStartOfDay(ZoneId.systemDefault()));
		Date dateRG = Date.from(instantDRG);

		usuario.setNome(tfNome.getText());
		usuario.setSexo(sexo(cbSexo.getSelectionModel().getSelectedItem()));
		usuario.setDataNascimento(dateNasc);
		usuario.setNomePai(tfNomePai.getText());
		usuario.setNomeMae(tfNomeMae.getText());
		usuario.setCpf(tfCPF.getText());
		usuario.setRg(tfRG.getText());
		usuario.setDataExpedicaoRg(dateRG);
		usuario.setOrgaoExpedicaoRg(tfOrgaoRG.getText());
		usuario.setEstadoRg(cbEstado.getSelectionModel().getSelectedItem());
		usuario.setCnh(tfCNH.getText());
		usuario.setEmail(tfEmail.getText());
		usuario.setTelefone1(tfTelefone1.getText());
		usuario.setTelefone2(tfTelefone2.getText());

		imdAuth.iniciarUsuarioEditarAcesso(usuario);
	}

	public String sexo(String sexo) {
		if (sexo.equals("Masculino")) {
			return "M";
		} else {
			return "F";
		}
	}

	public String sexoReverso(String sexo) {
		if (sexo.equals("M")) {
			return "Masculino";
		} else {
			return "Feminino";
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbSexo.getItems().addAll("Masculino", "Feminino");
		cbEstado.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
				"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
	}
}
