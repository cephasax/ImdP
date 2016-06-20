package br.ufrn.imd.view.maquina;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.MaquinaService;
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

public class MaquinaBuscarController implements Initializable {
	@FXML
	private TableView<Maquina> tblMaquinas;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNomeMaquina;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<Maquina, String> maquinaNome;
	@FXML
	private TableColumn<Maquina, String> maquinaIP;
	@FXML
	private TableColumn<Maquina, String> maquinaUnidade;

	private ImdAuth imdAuth;

	private MaquinaService service = new MaquinaService();

	private UnidadeService serviceUnidade = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.MaquinaBuscar(tfNomeMaquina.getText());

		Type listType = new TypeToken<ArrayList<Maquina>>() {
		}.getType();
		List<Maquina> yourClassList = new Gson().fromJson(resultado, listType);

		tblMaquinas.setItems(FXCollections.observableArrayList(yourClassList));
		maquinaNome.setCellValueFactory(new PropertyValueFactory<Maquina, String>("denominacao"));
		maquinaIP.setCellValueFactory(new PropertyValueFactory<Maquina, String>("ip"));
		maquinaUnidade.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Maquina, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Maquina, String> p) {
						return new SimpleStringProperty(p.getValue().getUnidade().getNome());
					}
				});
	}

	@FXML
	public void handleExcluir() throws IOException {
		Maquina maquina = tblMaquinas.getSelectionModel().getSelectedItem();
		int resultado = service.MaquinaDeletar(maquina);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarMaquinaListar();
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
		Maquina maquina = tblMaquinas.getSelectionModel().getSelectedItem();
		imdAuth.iniciarMaquinaEditar(maquina);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.UnidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);
		cbUnidade.setConverter(new UnidadeConverter());
	}
}
