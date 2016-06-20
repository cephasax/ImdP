package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.SetorConverter;
import br.ufrn.imd.converter.UnidadeConverter;
import br.ufrn.imd.converter.VinculoConverter;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PontoBuscarController implements Initializable {
	@FXML
	private TableView<Ponto> tblPontos;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField tfNomeUsuario;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private Button btnExcluir;
	
	private SetorService serviceSetor = new SetorService();
	
	private UnidadeService serviceUnidade = new UnidadeService();
	
	private VinculoService serviceVinculo = new VinculoService();

	private ImdAuth imdAuth;

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
		cbUnidade.setConverter(new UnidadeConverter());
		
		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.SetorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);
		cbSetor.setConverter(new SetorConverter());
		
		Type listTypeV = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		Collection<Vinculo> vinculos = new Gson().fromJson(serviceVinculo.VinculoListar(), listTypeV);

		cbVinculo.getItems().addAll(vinculos);
		cbVinculo.setConverter(new VinculoConverter());
		
	}
}
