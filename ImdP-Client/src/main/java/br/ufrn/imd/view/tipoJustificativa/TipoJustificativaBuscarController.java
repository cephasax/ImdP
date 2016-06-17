package br.ufrn.imd.view.tipoJustificativa;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.TipoJustificativaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TipoJustificativaBuscarController {
	@FXML
	private TableView<TipoJustificativa> tblTipoJustificativa;
	@FXML
	private Button btnExcluir;
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<TipoJustificativa, String> tiposJustificativaNome;

	private ImdAuth imdAuth;

	private TipoJustificativaService service = new TipoJustificativaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleBuscar() throws IOException {
		String resultado = service.tipoJustificativaBuscar(tfTipoJustificativa.getText());

		Type listType = new TypeToken<ArrayList<TipoJustificativa>>() {
		}.getType();
		List<TipoJustificativa> yourClassList = new Gson().fromJson(resultado, listType);
		for (int i = 0; i < yourClassList.size(); i++) {
			System.out.println(yourClassList.get(i).getIdTipoJustificativa() + " " + yourClassList.get(i).getNome());
		}

		tblTipoJustificativa.setItems(FXCollections.observableArrayList(yourClassList));
		tiposJustificativaNome.setCellValueFactory(new PropertyValueFactory<TipoJustificativa, String>("nome"));
	}

	@FXML
	public void handleExcluir() throws IOException {
		TipoJustificativa tipoJustificativa = tblTipoJustificativa.getSelectionModel().getSelectedItem();
		int resultado = service.tipoJustificativaDeletar(tipoJustificativa);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado deletado!");

			alert.showAndWait();
			imdAuth.iniciarTipoJustificativaListar();
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
		TipoJustificativa tipoJustificativa = tblTipoJustificativa.getSelectionModel().getSelectedItem();
		imdAuth.iniciarTipoJustificativaEditar(tipoJustificativa);
	}
}
