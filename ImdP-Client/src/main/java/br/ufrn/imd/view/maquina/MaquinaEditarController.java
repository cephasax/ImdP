package br.ufrn.imd.view.maquina;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.MaquinaService;
import br.ufrn.imd.services.UnidadeService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MaquinaEditarController implements Initializable {
	@FXML
	private TextField tfNomeMaquina;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfIP;
	@FXML
	private ComboBox<Unidade> cbUnidade;

	private ImdAuth imdAuth;

	private Maquina maquina = new Maquina();

	private MaquinaService service = new MaquinaService();

	private UnidadeService serviceUnidade = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
		tfNomeMaquina.setText(maquina.getDenominacao());
		tfIP.setText(maquina.getIp());
		cbUnidade.setValue(maquina.getUnidade());
		cbUnidade.setConverter(new UnidadeConverter());

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleEditar() throws IOException {
		maquina.setDenominacao(tfNomeMaquina.getText());
		maquina.setIp(tfIP.getText());
		maquina.setUnidade(cbUnidade.getSelectionModel().getSelectedItem());

		int resultado = service.maquinaEditar(maquina);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

			alert.showAndWait();
			imdAuth.iniciarMaquinaListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.UnidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);
		cbUnidade.setConverter(new UnidadeConverter());

	}
}
