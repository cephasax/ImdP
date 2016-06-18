package br.ufrn.imd.view.maquina;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.MaquinaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MaquinaListarController implements Initializable {
	@FXML
	private TableView<Maquina> tblMaquinas;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<Maquina, String> maquinaNome;
	@FXML
	private TableColumn<Maquina, String> maquinaIP;
	@FXML
	private TableColumn<Unidade, String> maquinaUnidade;

	private ImdAuth imdAuth;

	private MaquinaService service = new MaquinaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Maquina>>() {
		}.getType();
		List<Maquina> yourClassList = new Gson().fromJson(service.MaquinaListar(), listType);

		tblMaquinas.setItems(FXCollections.observableArrayList(yourClassList));
		maquinaNome.setCellValueFactory(new PropertyValueFactory<Maquina, String>("denominacao"));
		maquinaIP.setCellValueFactory(new PropertyValueFactory<Maquina, String>("ip"));
		maquinaUnidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("unidade"));
	}

	@FXML
	public void handleExcluir() throws IOException {
		Maquina maquina = tblMaquinas.getSelectionModel().getSelectedItem();
		int resultado = service.MaquinaDeletar(maquina);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

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

	@FXML
	public void handleEditar() throws IOException {
		Maquina maquina = tblMaquinas.getSelectionModel().getSelectedItem();
		imdAuth.iniciarMaquinaEditar(maquina);
	}
}
