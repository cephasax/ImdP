package br.ufrn.imd.view.cargo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CargoEditarController implements Initializable {
	@FXML
	private TextField tfNomeCargo;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;

	private Cargo cargo = new Cargo();

	private CargoService service = new CargoService();

	private int resultado;

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
		tfNomeCargo.setText(cargo.getNome());
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void handleEditar() throws IOException {
		cargo.setNome(tfNomeCargo.getText());
		resultado = service.CargoEditar(cargo);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

			alert.showAndWait();
			imdAuth.iniciarCargoListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}
}
