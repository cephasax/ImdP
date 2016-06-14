package br.ufrn.imd.view.tipoJustificativa;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.TipoJustificativaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TipoJustificativaListarController implements Initializable {
	@FXML
	private TableView<TipoJustificativa> tblTiposJustificativas;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<TipoJustificativa, String> tiposJustificativaNome;

	private ImdAuth imdAuth;

	private TipoJustificativaService service = new TipoJustificativaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<TipoJustificativa>>() {
		}.getType();
		List<TipoJustificativa> yourClassList = new Gson().fromJson(service.tipoJustificativaListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdTipoJustificativa() + " " + yourClassList.get(i).getNome());
		}

		tblTiposJustificativas.setItems(FXCollections.observableArrayList(yourClassList));
		tiposJustificativaNome.setCellValueFactory(new PropertyValueFactory<TipoJustificativa, String>("nome"));

	}
}
