package br.ufrn.imd.view.tipoJustificativa;

import java.io.IOException;

import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.TipoJustificativaService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TipoJustificativaEditarController {
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	private TipoJustificativa tipoJustificativa = new TipoJustificativa();

	private TipoJustificativaService service = new TipoJustificativaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	public void setTipoJustificativa(TipoJustificativa tipoJustificativa) {
		this.tipoJustificativa = tipoJustificativa;
		tfTipoJustificativa.setText(tipoJustificativa.getNome());
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleEditar() throws IOException {
		tipoJustificativa.setNome(tfTipoJustificativa.getText());
		int resultado = service.tipoJustificativaEditar(tipoJustificativa);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

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
}
