package br.ufrn.imd.view.cargo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CargoBuscarController {
	@FXML
	private AnchorPane telaBuscaCargo;
	@FXML
	private TableView<Cargo> tblCargos;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfBuscaCargo;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableColumn<Cargo, String> cargoNome;

	private ImdAuth imdAuth;

	private CargoService service = new CargoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.CargoBuscar(tfBuscaCargo.getText());

		Type listType = new TypeToken<ArrayList<Cargo>>() {
		}.getType();
		List<Cargo> yourClassList = new Gson().fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdCargo() + " " + yourClassList.get(i).getNome());
		}

		tblCargos.setItems(FXCollections.observableArrayList(yourClassList));
		cargoNome.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nome"));
	}

	@FXML
	public void handleExcluir() throws IOException {
		Cargo cargo = tblCargos.getSelectionModel().getSelectedItem();
		int resultado = service.CargoDeletar(cargo);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarCargoListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	@FXML
	public void handleEditar(MouseEvent event) throws IOException {
		if (event.getClickCount() > 1) {
			Cargo cargo = tblCargos.getSelectionModel().getSelectedItem();
			imdAuth.iniciarCargoEditar(cargo);
		}
	}
}
