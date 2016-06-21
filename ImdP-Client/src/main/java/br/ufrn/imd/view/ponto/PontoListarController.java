package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PontoListarController implements Initializable {
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableView<Ponto> tblPontos;
	@FXML
	private TableColumn<Ponto, String> pontoUnidade;
	@FXML
	private TableColumn<Ponto, String> pontoSetor;
	@FXML
	private TableColumn<Ponto, String> pontoUsuario;
	@FXML
	private TableColumn<Ponto, String> pontoVinculo;
	@FXML
	private TableColumn<Ponto, String> pontoData;
	@FXML
	private TableColumn<Ponto, String> pontoTipo;
	@FXML
	private TableColumn<Ponto, String> pontoSituacao;

	private ImdAuth imdAuth;

	private PontoService service = new PontoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").create();
		GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());

			}
		});
	
		Gson gson = builder.create();
		Type listType = new TypeToken<ArrayList<Ponto>>() {
		}.getType();
		List<Ponto> yourClassList = gson.fromJson(service.pontoListar(), listType);

		tblPontos.setItems(FXCollections.observableArrayList(yourClassList));
		pontoUnidade.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getUnidade().getNome());
					}
				});
		pontoSetor.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getNome());
					}
				});
		pontoUsuario.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getUsuario().getNome());
					}
				});
		pontoVinculo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getDescricao());
					}
				});
		pontoData.setCellValueFactory(new PropertyValueFactory<Ponto, String>("timeStamp"));
		pontoTipo.setCellValueFactory(new PropertyValueFactory<Ponto, String>("tipo"));
		pontoSituacao.setCellValueFactory(new PropertyValueFactory<Ponto, String>("situacao"));

	}
}
