package br.ufrn.imd.view.unidade;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UnidadeService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class UnidadeListarController implements Initializable {
	@FXML
	private TableView<Unidade> tblUnidades;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<Unidade, String> unidadeNome;
	
	private ImdAuth imdAuth;

	private UnidadeService service = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		List<Unidade> yourClassList = new Gson().fromJson(service.UnidadeListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdUnidade() + " " + yourClassList.get(i).getNome());
		}

		tblUnidades.setItems(FXCollections.observableArrayList(yourClassList));
		unidadeNome.setCellValueFactory(new PropertyValueFactory<Unidade, String>("nome"));

	}
	
	@FXML
	public void handleExcluir() throws IOException {
		Unidade unidade = tblUnidades.getSelectionModel().getSelectedItem();
		int resultado;
		resultado = service.UnidadeDeletar(unidade);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarUnidadeListar();
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
		Unidade unidade = tblUnidades.getSelectionModel().getSelectedItem();
		imdAuth.iniciarUnidadeEditar(unidade);
	}
}
