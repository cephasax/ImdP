package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.SetorConverter;
import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.JustificativaFaltaService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class JustificativaBuscarController implements Initializable {
	@FXML
	private TableView<JustificativaFalta> tblJustificativasFalta;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNomeUsuario;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaUsuario;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaData;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaTipo;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaDescricao;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaUnidade;
	@FXML
	private TableColumn<JustificativaFalta, String> justificativaSetor;

	private ImdAuth imdAuth;

	private JustificativaFaltaService service = new JustificativaFaltaService();

	private SetorService serviceSetor = new SetorService();

	private UnidadeService serviceUnidade = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
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

	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.justificativaFaltaBuscar(tfNomeUsuario.getText());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listType = new TypeToken<ArrayList<JustificativaFalta>>() {
		}.getType();
		List<JustificativaFalta> yourClassList = gson.fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(
					yourClassList.get(i).getIdJustificativaFalta() + " " + yourClassList.get(i).getDescricao());
		}

		tblJustificativasFalta.setItems(FXCollections.observableArrayList(yourClassList));
		justificativaUsuario.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<JustificativaFalta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<JustificativaFalta, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getUsuario().getNome());
					}
				});
		justificativaData.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("dataInicio"));
		justificativaTipo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<JustificativaFalta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<JustificativaFalta, String> p) {
						return new SimpleStringProperty(p.getValue().getTipoJustificativa().getNome());
					}
				});
		justificativaDescricao.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("descricao"));
		justificativaUnidade.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<JustificativaFalta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<JustificativaFalta, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getUnidade().getNome());
					}
				});
		justificativaSetor.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<JustificativaFalta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<JustificativaFalta, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getNome());
					}
				});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.UnidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);
		cbUnidade.setConverter(new UnidadeConverter());

		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.SetorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);
		cbSetor.setConverter(new SetorConverter());
	}
}
