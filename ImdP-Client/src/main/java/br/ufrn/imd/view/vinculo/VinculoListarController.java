package br.ufrn.imd.view.vinculo;

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

import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.VinculoService;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class VinculoListarController implements Initializable {
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<Vinculo> tblVinculos;
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

	private ImdAuth imdAuth;

	private VinculoService service = new VinculoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
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

		Type listType = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		List<Vinculo> yourClassList = gson.fromJson(service.vinculoListar(), listType);
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

	@FXML
	public void handleExcluir() throws IOException {
		Vinculo vinculo = tblVinculos.getSelectionModel().getSelectedItem();
		int resultado = service.vinculoDeletar(vinculo);

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
	public void handleEditar(MouseEvent event) throws IOException {
		if (event.getClickCount() > 1) {
			Vinculo vinculo = tblVinculos.getSelectionModel().getSelectedItem();
			imdAuth.iniciarVinculoEditar(vinculo);
		}
	}

	public String status(char status) {
		if (status == 'A') {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}
}
