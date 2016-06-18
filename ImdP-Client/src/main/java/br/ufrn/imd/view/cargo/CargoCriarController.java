package br.ufrn.imd.view.cargo;

import java.io.IOException;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CargoCriarController {
	@FXML
	private TextField tfNomeCargo;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;

	private ImdAuth imdAuth;
	
	private CargoService service = new CargoService();
	
	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
	
	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
	
	@FXML
	public void handleCadastrar() throws IOException {
		Cargo cargo = new Cargo(tfNomeCargo.getText());
		int resultado;
		resultado = service.CargoCriar(cargo);
		if(resultado == 200){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado criado!");

			alert.showAndWait();
			imdAuth.iniciarCargoListar();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}
}
