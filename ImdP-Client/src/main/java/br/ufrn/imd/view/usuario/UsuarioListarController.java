package br.ufrn.imd.view.usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listType = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> yourClassList = gson.fromJson(service.usuarioListar(), listType);

		tblUsuarios.setItems(FXCollections.observableArrayList(yourClassList));
		usuarioNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		usuarioCPF.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
		usuarioTelefone.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone1"));
		usuarioEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

	}
	
	@FXML
	public void handleEditar() throws IOException {
		Usuario usuario = tblUsuarios.getSelectionModel().getSelectedItem();
		imdAuth.iniciarUsuarioEditar(usuario);
	}

}
