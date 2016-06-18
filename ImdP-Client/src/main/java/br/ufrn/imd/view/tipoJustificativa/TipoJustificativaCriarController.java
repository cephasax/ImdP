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

public class TipoJustificativaCriarController {
	@FXML
	private TextField tfTipoJustificativa;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;

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
	public void handleCadastrar() throws IOException {
		TipoJustificativa tipoJustificativa = new TipoJustificativa(tfTipoJustificativa.getText());
		int resultado;
		resultado = service.tipoJustificativaCriar(tipoJustificativa);
		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado criado!");

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
