package br.ufrn.imd.view.cargo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CargoListarController implements Initializable {
	@FXML
	private TableView<Cargo> tblCargos;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<Cargo, String> cargoNome;

	private ImdAuth imdAuth;

	private CargoService service = new CargoService();

	public CargoListarController() throws Exception {
		tblCargos = new TableView<Cargo>();
		cargoNome = new TableColumn<Cargo, String>();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	public void initialize(URL location, ResourceBundle resources) {

		Type listType = new TypeToken<ArrayList<Cargo>>() {
		}.getType();
		List<Cargo> yourClassList = new Gson().fromJson(service.CargoListar(), listType);
		for(int i=0; i<yourClassList.size(); i++){
			System.out.println(yourClassList.get(i).getIdCargo() + " " + yourClassList.get(i).getNome());
		}
		
		tblCargos.setItems(FXCollections.observableArrayList(yourClassList));
		cargoNome.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nome"));
	}
}
