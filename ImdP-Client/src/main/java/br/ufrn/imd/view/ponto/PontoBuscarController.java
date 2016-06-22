package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.SetorConverter;
import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.converter.VinculoConverter;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.VinculoService;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PontoBuscarController implements Initializable {
	@FXML
	private TableView<Ponto> tblPontos;
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
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private Button btnExcluir;
	@FXML
    private TableColumn<Ponto, String> pontoUnidade;
    @FXML
    private TableColumn<Ponto, String> pontoSetor;
    @FXML
    private TableColumn<Ponto, String> pontoUsuario;
    @FXML
    private TableColumn<Ponto, String> pontoData;
    @FXML
    private TableColumn<Ponto, String> pontoTipo;
    @FXML
    private TableColumn<Ponto, String> pontoSituacao;

	private SetorService serviceSetor = new SetorService();
	
	private UnidadeService serviceUnidade = new UnidadeService();
	
	private VinculoService serviceVinculo = new VinculoService();
	
	private PontoService service = new PontoService();

	private ImdAuth imdAuth;

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

	public class JsonDateDeserializer implements JsonDeserializer<Date> {
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			String s = json.getAsJsonPrimitive().getAsString();
			long l = Long.parseLong(s.substring(6, s.length() - 2));
			Date d = new Date(l);
			return d;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Type listTypeV = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		Collection<Vinculo> vinculos = gson.fromJson(serviceVinculo.vinculoListar(), listTypeV);
		
		cbVinculo.getItems().addAll(vinculos);
		cbVinculo.setConverter(new VinculoConverter());
		
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.unidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);
		cbUnidade.setConverter(new UnidadeConverter());
		
		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.setorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);
		cbSetor.setConverter(new SetorConverter());
		
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
	
	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.pontoBuscarNome(tfNomeUsuario.getText());
		
		// Creates the json object which will manage the information received
		GsonBuilder builder = new GsonBuilder();

		// Register an adapter to manage the date types as long values
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				for (String format : DATE_FORMATS) {
					try {
						return new SimpleDateFormat(format, Locale.US).parse(json.getAsString());
					} catch (ParseException e) {
					}
				}
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});
		
		Type listType = new TypeToken<ArrayList<Ponto>>() {
		}.getType();
		
		Gson gson = builder.create();

		List<Ponto> yourClassList = gson.fromJson(resultado, listType);

		tblPontos.setItems(FXCollections.observableArrayList(yourClassList));
		pontoUsuario.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getUsuario().getNome());
					}
				});
		pontoData.setCellValueFactory(new PropertyValueFactory<Ponto, String>("timeStamp"));
		pontoTipo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(tipo(p.getValue().getTipo()));
					}
				});
		pontoSetor.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getNome());
					}
				});
		pontoSituacao.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(validado(p.getValue().getValidado()));
					}
				});
		pontoUnidade.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ponto, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ponto, String> p) {
						return new SimpleStringProperty(p.getValue().getVinculo().getSetor().getUnidade().getNome());
					}
				});
	}
	
	public String tipo(char status) {
		if (status == 'A') {
			return "Avulso";
		} else {
			return "Normal";
		}
	}

	public String validado(char status) {
		if (status == 'Y') {
			return "Validado";
		} else {
			return "Não validado";
		}
	}
}
