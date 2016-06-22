package br.ufrn.imd.view.unidade;

import java.io.IOException;

import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.UnidadeService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class UnidadeEditarController {
	@FXML
	private TextField tfNomeUnidade;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableColumn<Unidade, String> unidadeNome;

	private ImdAuth imdAuth;

	private Unidade unidade = new Unidade();

	private UnidadeService service = new UnidadeService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
		tfNomeUnidade.setText(unidade.getNome());
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleEditar() throws IOException {
		unidade.setNome(tfNomeUnidade.getText());
		int resultado = service.unidadeEditar(unidade);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

			alert.showAndWait();
			imdAuth.iniciarUnidadeListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}
}
