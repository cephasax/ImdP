package br.ufrn.imd.view.cargo;

import java.io.IOException;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import javafx.fxml.FXML;
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
		
		service.CargoCriar(cargo);
	}
}
