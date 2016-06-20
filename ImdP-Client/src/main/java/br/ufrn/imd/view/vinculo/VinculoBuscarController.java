package br.ufrn.imd.view.vinculo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.VinculoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class VinculoBuscarController {
	@FXML
	private TableView<Vinculo> tblVinculos;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TableColumn<Vinculo, String> vinculoNome;
	@FXML
	private TableColumn<Vinculo, String> vinculoCPF;
	@FXML
	private TableColumn<Vinculo, String> vinculoUnidade;
	@FXML
	private TableColumn<Vinculo, String> vinculoSetor;
	@FXML
	private TableColumn<Vinculo, String> vinculoCHSemanal;
	@FXML
	private TableColumn<Vinculo, String> vinculoStatus;
	@FXML
	private TextField tfNome;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private Button btnExcluir;

	private VinculoService service = new VinculoService();

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleExcluir() throws IOException {
		Vinculo vinculo = tblVinculos.getSelectionModel().getSelectedItem();
		int resultado = service.VinculoDeletar(vinculo);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarVinculoListar();
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
		Vinculo vinculo = tblVinculos.getSelectionModel().getSelectedItem();
		imdAuth.iniciarVinculoEditar(vinculo);
	}

	@FXML
	public void handleBuscar() throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		String resultado = service.VinculoBuscar(tfNome.getText());

		Type listType = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		List<Vinculo> yourClassList = gson.fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdVinculo() + " " + yourClassList.get(i).getDescricao());
		}

		tblVinculos.setItems(FXCollections.observableArrayList(yourClassList));
		vinculoNome.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(p.getValue().getUsuario().getNome());
					}
				});
		vinculoCPF.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(p.getValue().getUsuario().getCpf());
					}
				});
		vinculoUnidade.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(p.getValue().getSetor().getUnidade().getNome());
					}
				});
		vinculoSetor.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(p.getValue().getSetor().getNome());
					}
				});
		vinculoCHSemanal.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(String.valueOf(p.getValue().getCargaHorariaSemanal()));
					}
				});
		vinculoStatus.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vinculo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vinculo, String> p) {
						return new SimpleStringProperty(status(p.getValue().getSituacao()));
					}
				});
	}
	
	public String status(char status) {
		if (status == 'A') {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}
}
