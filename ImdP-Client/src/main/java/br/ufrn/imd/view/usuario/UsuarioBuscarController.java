package br.ufrn.imd.view.usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UsuarioService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuarioBuscarController {
	@FXML
	private TableView<Usuario> tblUsuarios;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfCPF;
	@FXML
	private Button btnExcluir;
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
	public void handleBuscar() throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		String resultado = service.usuarioBuscar(tfNome.getText());

		Type listType = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> yourClassList = gson.fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdUsuario() + " " + yourClassList.get(i).getNome());
		}

		tblUsuarios.setItems(FXCollections.observableArrayList(yourClassList));
		usuarioNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		usuarioCPF.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
		usuarioTelefone.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone1"));
		usuarioEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
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
	
	@FXML
	public void handleEditar() throws IOException {
		Usuario usuario = tblUsuarios.getSelectionModel().getSelectedItem();
		imdAuth.iniciarUsuarioEditar(usuario);
	}
}
