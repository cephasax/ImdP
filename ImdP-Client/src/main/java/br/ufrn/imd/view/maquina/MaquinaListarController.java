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
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdMaquina() + " " + yourClassList.get(i).getDenominacao());
		}

		tblMaquinas.setItems(FXCollections.observableArrayList(yourClassList));
		maquinaNome.setCellValueFactory(new PropertyValueFactory<Maquina, String>("denominacao"));
		maquinaIP.setCellValueFactory(new PropertyValueFactory<Maquina, String>("ip"));
		maquinaUnidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("unidade"));

	}
}
