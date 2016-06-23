package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

	public class JsonDateDeserializer implements JsonDeserializer<Date> {
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			String s = json.getAsJsonPrimitive().getAsString();
			long l = Long.parseLong(s.substring(6, s.length() - 2));
			Date d = new Date(l);
			return d;
		}
	}

	private static final String[] DATE_FORMATS = new String[] { "MMM dd, yyyy HH:mm:ss", "MMM dd, yyyy",
			"yyyy.MM.dd G 'at' HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
			"yyyy-MM-dd'T'HH:mm:ssZ", "yyyyy.MMMMM.dd GGG hh:mm aaa", "yyyy-MM-dd",

	};

	public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

		@Override
		public JsonElement serialize(byte[] src, Type type, JsonSerializationContext jsc) {
			Base64 base = new Base64();
			return new JsonPrimitive(base.encodeToString(src));
		}

		@Override
		public byte[] deserialize(JsonElement json, Type type, JsonDeserializationContext jdc)
				throws JsonParseException {
			Base64 base = new Base64();
			return base.decode(json.getAsString());
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				for (String format : DATE_FORMATS) {
					try {
						return new SimpleDateFormat(format, Locale.US).parse(json.getAsString());
					} catch (ParseException e) {
					}
				}
				return new Date(json.getAsLong());
			}
		}).registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).create();
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

	@FXML
	public void handleExcluir() throws IOException {
		Ponto ponto = tblPontos.getSelectionModel().getSelectedItem();
		int resultado = service.pontoDeletar(ponto);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarPontoListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	@FXML
	public void handleEditar(MouseEvent event) throws IOException {
		if (event.getClickCount() > 1) {
			Ponto ponto = tblPontos.getSelectionModel().getSelectedItem();
			imdAuth.iniciarPontoGestorEditar(ponto);
		}
	}
}
