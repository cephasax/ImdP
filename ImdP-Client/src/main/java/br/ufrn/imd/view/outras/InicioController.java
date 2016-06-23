package br.ufrn.imd.view.outras;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Timestamp;
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

import br.ufrn.imd.converter.VinculoConverter;
import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class InicioController implements Initializable {

	private ImdAuth imdAuth;

	@FXML
	private ImageView imgPonto;
	@FXML
	private ImageView imdLogo;
	@FXML
	private Text lblAindaNaoECadastrado;
	@FXML
	private Label lblCPF;
	@FXML
	private Label lblSenha;
	@FXML
	private Label lblVinculo;
	@FXML
	private Button btnLogar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private TextField tfSenha;
	@FXML
	private TextField tfCPF;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Text lblCliqueAqui;
	@FXML
	private Menu menuMenu;
	@FXML
	private Menu menuSistema;
	@FXML
	private Menu menuUnidade;
	@FXML
	private Menu menuSetor;
	@FXML
	private Menu menuUsuario;
	@FXML
	private Menu menuAjuda;
	@FXML
	private MenuItem menuItemLogin;
	@FXML
	private MenuItem menuItemLogout;
	
	private PontoService servicePonto = new PontoService();

	@FXML
	public void handleBtnLogin() throws IOException {
		imdAuth.iniciarPontoNormalCriar();
	}

	@FXML
	public void handleCliqueAqui() throws IOException {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText("Contacte o administrador do sistema.");
		alerta.showAndWait();
	}

	@FXML
	public void handleCargoCriar() throws IOException {
		imdAuth.iniciarCargoCriar();
	}

	@FXML
	public void handleCargoBuscar() throws IOException {
		imdAuth.iniciarCargoBuscar();
	}

	@FXML
	public void handleCargoEditar(Cargo cargo) throws IOException {
		imdAuth.iniciarCargoEditar(cargo);
	}

	@FXML
	public void handleCargoListar() throws IOException {
		imdAuth.iniciarCargoListar();
	}

	@FXML
	public void handleJustificativaBuscar() throws IOException {
		imdAuth.iniciarJustificativaBuscar();
	}

	@FXML
	public void handleJustificativaCriar() throws IOException {
		imdAuth.iniciarJustificativaCriar();
	}

	@FXML
	public void handleJustificativaGestorEditar() throws IOException {
		imdAuth.iniciarJustificativaGestorEditar();
	}

	@FXML
	public void handleJustificativaListar() throws IOException {
		imdAuth.iniciarJustificativaListar();
	}

	@FXML
	public void handleJustificativaUsuarioEditar(JustificativaFalta justificativaFalta) throws IOException {
		imdAuth.iniciarJustificativaUsuarioEditar(justificativaFalta);
	}

	@FXML
	public void handleJustificativaUsuarioListar() throws IOException {
		imdAuth.iniciarJustificativaUsuarioListar();
	}

	@FXML
	public void handleMaquinaBuscar() throws IOException {
		imdAuth.iniciarMaquinaBuscar();
	}

	@FXML
	public void handleMaquinaCriar() throws IOException {
		imdAuth.iniciarMaquinaCriar();
	}

	@FXML
	public void handleMaquinaEditar(Maquina maquina) throws IOException {
		imdAuth.iniciarMaquinaEditar(maquina);
	}

	@FXML
	public void handleMaquinaListar() throws IOException {
		imdAuth.iniciarMaquinaListar();
	}

	@FXML
	public void handlePontoAvulsoCriar() throws IOException {
		imdAuth.iniciarPontoAvulsoCriar();
	}

	@FXML
	public void handlePontoBuscar() throws IOException {
		imdAuth.iniciarPontoBuscar();
	}

	@FXML
	public void handlePontoConfirmar() throws IOException {
		imdAuth.iniciarPontoConfirmar();
	}

	@FXML
	public void handlePontoGestorEditar(Ponto ponto) throws IOException {
		imdAuth.iniciarPontoGestorEditar(ponto);
	}

	@FXML
	public void handlePontoListar() throws IOException {
		imdAuth.iniciarPontoListar();
	}

	@FXML
	public void handlePontoNormalCriar() throws IOException {
		imdAuth.iniciarPontoNormalCriar();
	}

	@FXML
	public void handlePontoUsuarioListar() throws IOException {
		imdAuth.iniciarPontoUsuarioListar();
	}

	@FXML
	public void handleSetorBuscar() throws IOException {
		imdAuth.iniciarSetorBuscar();
	}

	@FXML
	public void handleSetorCriar() throws IOException {
		imdAuth.iniciarSetorCriar();
	}

	@FXML
	public void handleSetorListar() throws IOException {
		imdAuth.iniciarSetorListar();
	}

	@FXML
	public void handleSetorEditar(Setor setor) throws IOException {
		imdAuth.iniciarSetorEditar(setor);
	}

	@FXML
	public void handleTipoJustificativaBuscar() throws IOException {
		imdAuth.iniciarTipoJustificativaBuscar();
	}

	@FXML
	public void handleTipoJustificativaCriar() throws IOException {
		imdAuth.iniciarTipoJustificativaCriar();
	}

	@FXML
	public void handleTipoJustificativaEditar(TipoJustificativa tipoJustificativa) throws IOException {
		imdAuth.iniciarTipoJustificativaEditar(tipoJustificativa);
	}

	@FXML
	public void handleTipoJustificativaListar() throws IOException {
		imdAuth.iniciarTipoJustificativaListar();
	}

	@FXML
	public void handleUnidadeBuscar() throws IOException {
		imdAuth.iniciarUnidadeBuscar();
	}

	@FXML
	public void handleUnidadeCriar() throws IOException {
		imdAuth.iniciarUnidadeCriar();
	}

	@FXML
	public void handleUnidadeEditar(Unidade unidade) throws IOException {
		imdAuth.iniciarUnidadeEditar(unidade);
	}

	@FXML
	public void handleUnidadeListar() throws IOException {
		imdAuth.iniciarUnidadeListar();
	}

	@FXML
	public void handleUsuarioBuscar() throws IOException {
		imdAuth.iniciarUsuarioBuscar();
	}

	@FXML
	public void handleUsuarioCriarAcesso(Usuario usuario) throws IOException {
		imdAuth.iniciarUsuarioCriarAcesso(usuario);
	}

	@FXML
	public void handleUsuarioCriar() throws IOException {
		imdAuth.iniciarUsuarioCriar();
	}

	@FXML
	public void handleUsuarioEditar(Usuario usuario) throws IOException {
		imdAuth.iniciarUsuarioEditar(usuario);
	}

	@FXML
	public void handleUsuarioEditarAcesso(Usuario usuario) throws IOException {
		imdAuth.iniciarUsuarioEditarAcesso(usuario);
	}

	@FXML
	public void handleUsuarioListar() throws IOException {
		imdAuth.iniciarUsuarioListar();
	}

	@FXML
	public void handleVinculoBuscar() throws IOException {
		imdAuth.iniciarVinculoBuscar();
	}

	@FXML
	public void handleVinculoCriar() throws IOException {
		imdAuth.iniciarVinculoCriar();
	}

	@FXML
	public void handleVinculoEditar(Vinculo vinculo) throws IOException {
		imdAuth.iniciarVinculoEditar(vinculo);
	}

	@FXML
	public void handleVinculoListar() throws IOException {
		imdAuth.iniciarVinculoListar();
	}

	@FXML
	public void handleExit() throws IOException {
		imdAuth.exit();
	}

	@FXML
	public void handleLogout() throws IOException {
		imdAuth.iniciarLogout();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarLogout();
	}

	@FXML
	public void actionLogin() {
		lblSenha.setVisible(true);
		tfSenha.setVisible(true);
		lblVinculo.setVisible(false);
		cbVinculo.setVisible(false);
		btnCadastrar.setVisible(false);
		btnLogar.setVisible(true);
		btnCancelar.setVisible(true);
		lblAindaNaoECadastrado.setVisible(true);
		lblCliqueAqui.setVisible(true);
	}

	@FXML
	public void actionLogar() throws IOException {
		try {
			UsuarioService service = new UsuarioService();

			Gson customGson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
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
			Type listTypeUs = new TypeToken<ArrayList<Usuario>>() {
			}.getType();
			List<Usuario> usuarios = customGson.fromJson(service.usuarioBuscarCPF(tfCPF.getText()), listTypeUs);

			imdAuth.setUsuario(usuarios.get(0));
			if (imdAuth.getUsuario().getSenha().equals(tfSenha.getText())) {

				menuAjuda.setDisable(false);
				menuMenu.setDisable(false);
				menuSetor.setDisable(false);
				menuSistema.setDisable(false);
				menuUnidade.setDisable(false);
				menuUsuario.setDisable(false);
				menuItemLogin.setDisable(true);
				menuItemLogout.setDisable(false);

				imdAuth.iniciarTelaPrincipal();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText(null);
				alert.setContentText("Senha incorreta tente de novo.");

				alert.showAndWait();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText("Usuário não existe.");

			alert.showAndWait();
		}
	}

	@FXML
	public void actionVerificarDigital() throws IOException {
		imdAuth.setVinculo(cbVinculo.getSelectionModel().getSelectedItem());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Feedback");
		alert.setHeaderText(null);
		alert.setContentText("Ponha sua digital.");
		alert.show();

		if (imdAuth.testeVerificarDigital()) {
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Date dataAtual = new Date(stamp.getTime());

			Ponto ponto = new Ponto(dataAtual, 'N', ' ', "", dataAtual, imdAuth.getUsuario().getIdUsuario(), imdAuth.getVinculo() ,imdAuth.getMaquina());

			int resultado = servicePonto.pontoCriar(ponto);
			if (resultado == 200) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Digital aprovada e Ponto lançado!");

				alert.showAndWait();
				imdAuth.iniciarLogout();
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Ocorreu um erro!");

				alert.showAndWait();
			}
		} else {
			Alert alert3 = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Digital não aprovada!");

			alert.showAndWait();
		}
	}

	@FXML
	public void actionVerificarUser() {
		try {
			UsuarioService service = new UsuarioService();

			Gson customGson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
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
			Type listTypeUs = new TypeToken<ArrayList<Usuario>>() {
			}.getType();
			List<Usuario> usuarios = customGson.fromJson(service.usuarioBuscarCPF(tfCPF.getText()), listTypeUs);

			imdAuth.setUsuario(usuarios.get(0));
			actionPreencherVinculo();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText("Usuário não existe.");

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

	@FXML
	public void actionPreencherVinculo() {
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
		Collection<Vinculo> vinculos = gson.fromJson(new VinculoService().vinculoBuscarUsuario(imdAuth.getUsuario()),
				listType);

		cbVinculo.getItems().addAll(vinculos);
		cbVinculo.setConverter(new VinculoConverter());
	}

	public void initialize(URL location, ResourceBundle resources) {
		imgPonto.setImage(
				new Image(ImdAuth.class.getResourceAsStream("../../../../main/resources/images/pontoAntigo/logo.png")));
		imdLogo.setImage(new Image(
				ImdAuth.class.getResourceAsStream("../../../../main/resources/images/pontoAntigo/logosmall-old.png")));
		menuItemLogout.setDisable(true);

	}
}
