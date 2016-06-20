package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JustificativaListarController implements Initializable {
	@FXML
	private Button brnCancelar;
	@FXML
	private TableView<JustificativaFalta> tblJustificativasFalta;
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Type listType = new TypeToken<ArrayList<JustificativaFalta>>() {
		}.getType();
		List<JustificativaFalta> yourClassList = gson.fromJson(service.justificativaFaltaListar(), listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(
					yourClassList.get(i).getIdJustificativaFalta() + " " + yourClassList.get(i).getDescricao());
		}

		tblJustificativasFalta.setItems(FXCollections.observableArrayList(yourClassList));
		justificativaUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		justificativaData.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, Date>("dataInicio"));
		justificativaTipo.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("tipoJustificativa"));
		justificativaDescricao.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("descricao"));
		justificativaUnidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("nome"));
		justificativaSetor.setCellValueFactory(new PropertyValueFactory<Setor, String>("setor"));
	}
	
	@FXML
	public void handleExcluir() throws IOException {
		JustificativaFalta justificativaFalta = tblJustificativasFalta.getSelectionModel().getSelectedItem();
		int resultado = service.justificativaFaltaDeletar(justificativaFalta);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarJustificativaListar();
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
		JustificativaFalta justificativaFalta = tblJustificativasFalta.getSelectionModel().getSelectedItem();
		imdAuth.iniciarJustificativaUsuarioEditar(justificativaFalta);
	}
}
