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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SetorListarController implements Initializable{
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
}
