package br.ufrn.imd.view.vinculo;

import java.io.IOException;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VinculoEditarController {
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Setor> tfSetor;
	@FXML
	private TextField tfCargaHorariaSemanal;
	@FXML
	private TextField tfCargoHorariaMensal;
	@FXML
	private ComboBox<Cargo> tfCargo;
	@FXML
	private ComboBox<Unidade> tfUnidade;
	@FXML
	private CheckBox checkboxAtivo;

	private ImdAuth imdAuth;
	
	private Vinculo vinculo = new Vinculo();
	
	private VinculoService service = new VinculoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}
	
	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
}
