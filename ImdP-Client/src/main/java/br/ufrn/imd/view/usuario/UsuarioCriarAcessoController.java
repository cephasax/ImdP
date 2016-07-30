package br.ufrn.imd.view.usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import br.ufrn.imd.dominio.ImpressaoDigital;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.ImpressaoDigitalService;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.view.fingerprint.DigitalPersona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UsuarioCriarAcessoController {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField tfLogin;
	@FXML
	private Button btnFoto;
	@FXML
	private Button btnDigital;
	@FXML
	private ImageView imgFoto;
	@FXML
	private PasswordField tfSenha;
	@FXML
	private PasswordField tfConfSenha;

	private ImdAuth imdAuth;

	private UsuarioService service = new UsuarioService();

	private ImpressaoDigitalService digitalService = new ImpressaoDigitalService();

	private Usuario usuario = new Usuario();

	private ImpressaoDigital digital = new ImpressaoDigital();

	@FXML
	public void handleBtnCadastrar() throws IOException {
		if (confereSenha()) {
			usuario.setLogin(tfLogin.getText());
			usuario.setSenha(tfSenha.getText());
			usuario.setFoto("foto");
			int resultado;
			resultado = service.usuarioCriar(usuario);

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

			Type listType = new TypeToken<ArrayList<Usuario>>() {
			}.getType();
			List<Usuario> yourClassList = customGson.fromJson(service.usuarioBuscarCPF(usuario.getCpf()), listType);

			digital.setUsuario(yourClassList.get(0));
			digital.setNomeDedo("teste");
			resultado = digitalService.impressaoDigitalCriar(digital);
			if (resultado == 200) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Dado criado!");

				alert.showAndWait();
				imdAuth.iniciarUsuarioListar();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Feedback");
				alert.setHeaderText(null);
				alert.setContentText("Ocorreu um erro!");

				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	@FXML
	public void handleBtnTirarFoto() throws IOException {
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleBtnDigital() throws IOException {
		DigitalPersona dp = new DigitalPersona();
		dp.principal();

		digital = new ImpressaoDigital(dp.get());
//		 digitalService.impressaoDigitalCriar(impDigital);
//		 usuario.setDigital(dp.get());
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean confereSenha() {
		if (tfSenha.getText().equals(tfConfSenha.getText())) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!\nSenhas não conferem.");
			alert.showAndWait();
			return false;
		}
	}

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

	private static final String[] DATE_FORMATS = new String[] { "MMM dd, yyyy HH:mm:ss", "MMM dd, yyyy",
			"yyyy.MM.dd G 'at' HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
			"yyyy-MM-dd'T'HH:mm:ssZ", "yyyyy.MMMMM.dd GGG hh:mm aaa", "yyyy-MM-dd",

	};
}
