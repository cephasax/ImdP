package br.ufrn.imd.view.setor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.SetorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SetorListarController implements Initializable {
	@FXML
	private TableView<Setor> tblSetores;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableColumn<Setor, String> setorNome;

	@FXML
	private TableColumn<Setor, String> setorUnidade;

	private ImdAuth imdAuth;

	private SetorService service = new SetorService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		List<Setor> yourClassList = new Gson().fromJson(service.SetorListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdSetor() + " " + yourClassList.get(i).getNome());
		}

		tblSetores.setItems(FXCollections.observableArrayList(yourClassList));
		setorNome.setCellValueFactory(new PropertyValueFactory<Setor, String>("nome"));
		setorUnidade.setCellValueFactory(new PropertyValueFactory<Setor, String>("unidade"));
	}

	@FXML
	public void handleExcluir() throws IOException {
		Setor setor = tblSetores.getSelectionModel().getSelectedItem();
		int resultado = service.SetorDeletar(setor);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarSetorListar();
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
		Setor setor = tblSetores.getSelectionModel().getSelectedItem();
		imdAuth.iniciarSetorEditar(setor);
	}
}
