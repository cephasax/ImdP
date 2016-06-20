package br.ufrn.imd.view.vinculo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.CargoConverter;
import br.ufrn.imd.converter.SetorConverter;
import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.converter.UsuarioConverter;
import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.CargoService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.UsuarioService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VinculoEditarController implements Initializable {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private TextField tfCargaHorariaSemanal;
	@FXML
	private TextField tfCargaHorariaMensal;
	@FXML
	private ComboBox<Cargo> cbCargo;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Usuario> cbUsuario;
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
	
	private UsuarioService serviceUsuario = new UsuarioService();
	
	private Vinculo vinculo = new Vinculo();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}
	
	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
		tfDescricao.setText(vinculo.getDescricao());
		cbSetor.setValue(vinculo.getSetor());
		cbSetor.setConverter(new SetorConverter());
		cbCargo.setValue(vinculo.getCargo());
		cbCargo.setConverter(new CargoConverter());
		cbUsuario.setValue(vinculo.getUsuario());
		cbUsuario.setConverter(new UsuarioConverter());
		tfCargaHorariaDiaria.setText(String.valueOf(vinculo.getCargaHorariaDiaria()));
		tfCargaHorariaSemanal.setText(String.valueOf(vinculo.getCargaHorariaSemanal()));
		tfCargaHorariaMensal.setText(String.valueOf(vinculo.getCargaHorariaMensal()));
//		checkboxAtivo.set
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}
	
	@FXML
	public void handleEditar() throws IOException {
		vinculo.setCargaHorariaMensal(Integer.parseInt(tfCargaHorariaMensal.getText()));
		vinculo.setCargaHorariaSemanal(Integer.parseInt(tfCargaHorariaSemanal.getText()));
		vinculo.setSetor(cbSetor.getSelectionModel().getSelectedItem());
		vinculo.setCargo(cbCargo.getSelectionModel().getSelectedItem());
		
		int resultado = service.VinculoEditar(vinculo);

		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado editado!");

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Type listType = new TypeToken<ArrayList<Unidade>>() {
		}.getType();
		Collection<Unidade> unidades = new Gson().fromJson(serviceUnidade.UnidadeListar(), listType);

		cbUnidade.getItems().addAll(unidades);
		cbUnidade.setConverter(new UnidadeConverter());
		
		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.SetorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);
		cbSetor.setConverter(new SetorConverter());

		Type listTypeC = new TypeToken<ArrayList<Cargo>>() {
		}.getType();
		Collection<Cargo> cargos = new Gson().fromJson(serviceCargo.CargoListar(), listTypeC);

		cbCargo.getItems().addAll(cargos);
		cbCargo.setConverter(new CargoConverter());

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listTypeUs = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> usuarios = gson.fromJson(serviceUsuario.usuarioListar(), listTypeUs);
		
		cbUsuario.getItems().addAll(usuarios);
		cbUsuario.setConverter(new UsuarioConverter());

	}
}
