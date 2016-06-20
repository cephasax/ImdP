package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PontoListarController implements Initializable {
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnExcluir;
	@FXML
	private TableView<Ponto> tblPontos;
	@FXML
	private TableColumn<Ponto, Unidade> pontoUnidade;
	@FXML
	private TableColumn<Ponto, Setor> pontoSetor;
	@FXML
	private TableColumn<Ponto, Usuario> pontoUsuario;
	@FXML
	private TableColumn<Ponto, Vinculo> pontoVinculo;
	@FXML
	private TableColumn<Ponto, Date> pontoData;
	@FXML
	private TableColumn<Ponto, String> pontoTipo;
	@FXML
	private TableColumn<Ponto, String> pontoSituacao;

	private ImdAuth imdAuth;

	private PontoService service = new PontoService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Type listType = new TypeToken<ArrayList<Ponto>>() {
		}.getType();
		List<Ponto> yourClassList = gson.fromJson(service.PontoListar(), listType);

		tblPontos.setItems(FXCollections.observableArrayList(yourClassList));
		pontoUnidade.setCellValueFactory(new PropertyValueFactory<Ponto, Unidade>("unidade"));
		pontoSetor.setCellValueFactory(new PropertyValueFactory<Ponto, Setor>("setor"));
		pontoUsuario.setCellValueFactory(new PropertyValueFactory<Ponto, Usuario>("usuario"));
		pontoVinculo.setCellValueFactory(new PropertyValueFactory<Ponto, Vinculo>("vinculo"));
		pontoData.setCellValueFactory(new PropertyValueFactory<Ponto, Date>("data"));
		pontoTipo.setCellValueFactory(new PropertyValueFactory<Ponto, String>("tipo"));
		pontoSituacao.setCellValueFactory(new PropertyValueFactory<Ponto, String>("situacao"));

	}
}
