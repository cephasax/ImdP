package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.JustificativaFaltaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class JustificativaBuscarController {
	@FXML
	private TableView<JustificativaFalta> tblJustificativasFalta;
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
	private Button btnExcluir;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaUsuario;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaData;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaTipo;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaDescricao;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaUnidade;
    @FXML
    private TableColumn<JustificativaFalta, String> justificativaFaltaSetor;

	private ImdAuth imdAuth;
	
	private JustificativaFaltaService service = new JustificativaFaltaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
	
	@FXML
	public void handleExcluir() throws IOException {
		JustificativaFalta justificativaFalta = tblJustificativasFalta.getSelectionModel().getSelectedItem();
		int resultado = service.justificativaFaltaDeletar(justificativaFalta);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarJustificativaListar();
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
		JustificativaFalta justificativaFalta = tblJustificativasFalta.getSelectionModel().getSelectedItem();
		imdAuth.iniciarJustificativaUsuarioEditar(justificativaFalta);
	}
	
	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.justificativaFaltaBuscar(tfNomeUsuario.getText());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listType = new TypeToken<ArrayList<JustificativaFalta>>() {
		}.getType();
		List<JustificativaFalta> yourClassList = gson.fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdJustificativaFalta() + " " + yourClassList.get(i).getDescricao());
		}

		tblJustificativasFalta.setItems(FXCollections.observableArrayList(yourClassList));
		justificativaFaltaUsuario.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("usuario"));
		justificativaFaltaData.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("data"));
		justificativaFaltaTipo.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("tipoJustificativa"));
		justificativaFaltaDescricao.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("descricao"));
		justificativaFaltaUnidade.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("unidade"));
		justificativaFaltaSetor.setCellValueFactory(new PropertyValueFactory<JustificativaFalta, String>("setor"));
		
	}
}
