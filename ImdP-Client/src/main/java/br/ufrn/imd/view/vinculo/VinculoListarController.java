package br.ufrn.imd.view.vinculo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.VinculoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VinculoListarController implements Initializable {
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<Vinculo> tblVinculos;
	@FXML
	private TableColumn<Vinculo, String> vinculoNome;
	@FXML
	private TableColumn<Vinculo, String> vinculoCPF;
	@FXML
	private TableColumn<Unidade, String> vinculoUnidade;
	@FXML
	private TableColumn<Setor, String> vinculoSetor;
	@FXML
	private TableColumn<Vinculo, String> vinculoCH;
	@FXML
	private TableColumn<Vinculo, String> vinculoStatus;

	private ImdAuth imdAuth;

	private VinculoService service = new VinculoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		List<Vinculo> yourClassList = new Gson().fromJson(service.VinculoListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdVinculo() + " " + yourClassList.get(i).getDescricao());
		}

		tblVinculos.setItems(FXCollections.observableArrayList(yourClassList));
		vinculoNome.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("nome"));
		vinculoCPF.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("CPF"));
		vinculoUnidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("unidade"));
		vinculoSetor.setCellValueFactory(new PropertyValueFactory<Setor, String>("setor"));
		vinculoCH.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("ch"));
		vinculoStatus.setCellValueFactory(new PropertyValueFactory<Vinculo, String>("status"));
	}
}
