package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.JustificativaFaltaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JustificativaListarController implements Initializable {
	@FXML
	private Button brnCancelar;
	@FXML
	private TableView<JustificativaFalta> tblJustificativasFaltas;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableColumn<Usuario, String> justificativaUsuario;
	@FXML
	private TableColumn<JustificativaFalta, Date> justificativaData;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaTipo;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaDescricao;
	@FXML
	private TableColumn<Unidade, String> justificativaUnidade;
	@FXML
	private TableColumn<Setor, String> justificativaSetor;
	private ImdAuth imdAuth;

	private JustificativaFaltaService service = new JustificativaFaltaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Gson gson = new Gson();
		gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
		Type listType = new TypeToken<ArrayList<JustificativaFalta>>() {
		}.getType();
		List<JustificativaFalta> yourClassList = gson.fromJson(service.justificativaFaltaListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(
					yourClassList.get(i).getIdJustificativaFalta() + " " + yourClassList.get(i).getDescricao());
		}

		tblJustificativasFaltas.setItems(FXCollections.observableArrayList(yourClassList));
		justificativaUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
//		justificativaData.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("data"));
		justificativaTipo.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("tipo"));
		justificativaDescricao.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("descricao"));
		justificativaUnidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("nome"));
		justificativaSetor.setCellValueFactory(new PropertyValueFactory<Setor, String>("nome"));

	}
}
