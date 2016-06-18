package br.ufrn.imd.view.usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UsuarioService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Type listType = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> yourClassList = new Gson().fromJson(service.UsuarioListar(), listType);

		tblUsuarios.setItems(FXCollections.observableArrayList(yourClassList));
		usuarioNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		usuarioCPF.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
		usuarioTelefone.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone"));
		usuarioEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

	}

}
