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

import br.ufrn.imd.converter.SetorConverter;
import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.converter.UsuarioConverter;
import br.ufrn.imd.converter.VinculoConverter;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PontoGestorEditarController implements Initializable {
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Usuario> cbUsuario;
	@FXML
	private TextField cbDataEnvio;
	@FXML
	private TextField cbDataAnalise;
	@FXML
	private ChoiceBox<String> cbSituacaoAtual;
	@FXML
	private TextField taObservacao;
	@FXML
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private ChoiceBox<String> cbTipoPonto;
	@FXML
	private TextField tfUsuarioAnalise;

	private ImdAuth imdAuth;

	private UnidadeService serviceUnidade = new UnidadeService();

	private SetorService serviceSetor = new SetorService();

	private PontoService service = new PontoService();

	private UsuarioService serviceUsuario = new UsuarioService();

	private VinculoService serviceVinculo = new VinculoService();

	private Ponto ponto = new Ponto();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;

		cbUsuario.setValue(ponto.getVinculo().getUsuario());
		cbUsuario.setConverter(new UsuarioConverter());
		cbVinculo.setValue(ponto.getVinculo());
		cbVinculo.setConverter(new VinculoConverter());
		cbUnidade.setValue(ponto.getVinculo().getSetor().getUnidade());
		cbUnidade.setConverter(new UnidadeConverter());
		cbSetor.setValue(ponto.getVinculo().getSetor());
		cbSetor.setConverter(new SetorConverter());
		cbTipoPonto.setValue(tipo(ponto.getTipo()));
		taObservacao.setText(ponto.getObservacao());
		cbDataEnvio.setText(String.valueOf(ponto.getTimeStamp()));
		cbDataEnvio.setText(String.valueOf(ponto.getTimeStampAlteracao()));
		cbSituacaoAtual.setValue(validado(ponto.getValidado()));
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	public String validado(char status) {
		if (status == 'Y') {
			return "Validado";
		} else {
			return "Não validado";
		}
	}

	public char validadoReverso(String status) {
		if (status.equals("Validado")) {
			return 'Y';
		} else {
			return 'N';
		}
	}

	public String tipo(char status) {
		if (status == 'A') {
			return "Avulso";
		} else {
			return "Normal";
		}
	}

	public char tipoReverso(String status) {
		if (status.equals("Avulso")) {
			return 'A';
		} else {
			return 'N';
		}
	}

	@FXML
	public void handleEditar() throws IOException {

		ponto.setVinculo(cbVinculo.getSelectionModel().getSelectedItem());
		ponto.getVinculo().getSetor().setUnidade(cbUnidade.getSelectionModel().getSelectedItem());
		ponto.getVinculo().setSetor(cbSetor.getSelectionModel().getSelectedItem());
		ponto.setTipo(tipoReverso(cbTipoPonto.getValue()));
		ponto.setObservacao(taObservacao.getText());
		ponto.setValidado(validadoReverso(cbSituacaoAtual.getValue()));

		int resultado = service.pontoEditar(ponto);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

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

		Type listTypeV = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		List<Vinculo> vinculos = gson.fromJson(serviceVinculo.vinculoListar(), listTypeV);

		cbVinculo.getItems().addAll(vinculos);
		cbVinculo.setConverter(new VinculoConverter());

		Type listTypeUs = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> usuarios = gson.fromJson(serviceUsuario.usuarioListar(), listTypeUs);

		cbUsuario.getItems().addAll(usuarios);
		cbUsuario.setConverter(new UsuarioConverter());

		cbTipoPonto.getItems().addAll("Avulso", "Normal");

		cbSituacaoAtual.getItems().addAll("Validado", "Não validado");

		tfUsuarioAnalise.setText(imdAuth.getUsuario().getNome());

	}

}
