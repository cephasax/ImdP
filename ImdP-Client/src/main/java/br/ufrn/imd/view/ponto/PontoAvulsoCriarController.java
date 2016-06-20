package br.ufrn.imd.view.ponto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
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
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.PontoService;
import br.ufrn.imd.services.SetorService;
import br.ufrn.imd.services.UnidadeService;
import br.ufrn.imd.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PontoAvulsoCriarController implements Initializable {
	@FXML
	private Button btnCriarPontoAvulso;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Unidade> cbUnidade;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private DatePicker dpData;
	@FXML
	private ComboBox<?> tfTipo;
	@FXML
	private TextField taObservacao;
	@FXML
	private ComboBox<Usuario> cbUsuario;

	private UnidadeService serviceUnidade = new UnidadeService();

	private SetorService serviceSetor = new SetorService();

	private UsuarioService serviceUsuario = new UsuarioService();

	private PontoService service = new PontoService();

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;
	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	@FXML
	public void handleCadastrar() throws IOException {
		LocalDate localDate = dpData.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);

		Date dataAtual = new Date();
		
		Ponto ponto = new Ponto(date, 'm', 'm', taObservacao.getText(), dataAtual, 1, null, null);
		
		int resultado = service.PontoCriar(ponto);
		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado criado!");

			alert.showAndWait();
			imdAuth.iniciarPontoListar();
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

		Type listTypeS = new TypeToken<ArrayList<Setor>>() {
		}.getType();
		Collection<Setor> setores = new Gson().fromJson(serviceSetor.SetorListar(), listTypeS);

		cbSetor.getItems().addAll(setores);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listTypeUs = new TypeToken<ArrayList<Usuario>>() {
		}.getType();
		List<Usuario> usuarios = gson.fromJson(serviceUsuario.usuarioListar(), listTypeUs);

		cbUsuario.getItems().addAll(usuarios);

	}
}
