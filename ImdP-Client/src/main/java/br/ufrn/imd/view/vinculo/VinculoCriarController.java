package br.ufrn.imd.view.vinculo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VinculoCriarController implements Initializable {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private TextField tfCargaHorariaSemanal;
	@FXML
	private TextField tfCargaHorarioMensal;
	@FXML
	private ComboBox<Cargo> cbCargo;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private CheckBox checkboxAtivo;
	@FXML
	private TextField tfDescricao;
	@FXML
	private TextField tfCargaHorariaDiaria;

	private ImdAuth imdAuth;

	private VinculoService service = new VinculoService();

	private UnidadeService serviceUnidade = new UnidadeService();

	private SetorService serviceSetor = new SetorService();

	private CargoService serviceCargo = new CargoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.UnidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);

		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.SetorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);

		Type listTypeC = new TypeToken<ArrayList<Cargo>>() {
		}.getType();
		Collection<Cargo> cargos = new Gson().fromJson(serviceCargo.CargoListar(), listTypeC);

		cbCargo.getItems().addAll(cargos);
	}

	@FXML
	public void handleCadastrar() throws IOException {
		Vinculo vinculo = new Vinculo(tfDescricao.getText(), cbCargo.getSelectionModel().getSelectedItem(),
				cbSetor.getSelectionModel().getSelectedItem(), Integer.parseInt(tfCargaHorariaDiaria.getText()),
				Integer.parseInt(tfCargaHorariaSemanal.getText()), Integer.parseInt(tfCargaHorarioMensal.getText()),
				marked(checkboxAtivo));
		int resultado;
		resultado = service.VinculoCriar(vinculo);
		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado criado!");

			alert.showAndWait();
			imdAuth.iniciarVinculoListar();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Ocorreu um erro!");

			alert.showAndWait();
		}
	}

	public char marked(CheckBox checkbox) {
		if (checkbox.isSelected()) {
			return 'A';
		} else
			return 'D';
	}
}
