package br.ufrn.imd.view.usuario;

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

import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UsuarioService;
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

public class UsuarioListarController implements Initializable {
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<Usuario> tblUsuarios;
	@FXML
	private TableColumn<Usuario, String> usuarioNome;
	@FXML
	private TableColumn<Usuario, String> usuarioCPF;
	@FXML
	private TableColumn<Usuario, String> usuarioTelefone;
	@FXML
	private TableColumn<Usuario, String> usuarioEmail;

	private ImdAuth imdAuth;

	private UsuarioService service = new UsuarioService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleExcluir() throws IOException {
		Usuario usuario = tblUsuarios.getSelectionModel().getSelectedItem();
		int resultado = service.usuarioDeletar(usuario);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarUsuarioListar();
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

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
		List<Usuario> yourClassList = customGson.fromJson(service.usuarioListar(), listType);

		tblUsuarios.setItems(FXCollections.observableArrayList(yourClassList));
		usuarioNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		usuarioCPF.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
		usuarioTelefone.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone1"));
		usuarioEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

	}

	@FXML
	public void handleEditar(MouseEvent event) throws IOException {
		if (event.getClickCount() > 1) {
			Usuario usuario = tblUsuarios.getSelectionModel().getSelectedItem();
			imdAuth.iniciarUsuarioEditar(usuario);
		}
	}

	
	public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

	    @Override
	    public JsonElement serialize(byte[] src, Type type, JsonSerializationContext jsc) {
	        Base64 base = new Base64();
	        return new JsonPrimitive(base.encodeToString(src));
	    }

	    @Override
	    public byte[] deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
	        Base64 base = new Base64();
	        return base.decode(json.getAsString());
	    }
	    
	}
	
}
