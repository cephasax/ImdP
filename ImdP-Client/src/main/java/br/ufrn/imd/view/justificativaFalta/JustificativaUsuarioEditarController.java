package br.ufrn.imd.view.justificativaFalta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufrn.imd.converter.TipoJustificativaConverter;
import br.ufrn.imd.converter.VinculoConverter;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.main.ImdAuth;
import br.ufrn.imd.services.JustificativaFaltaService;
import br.ufrn.imd.services.TipoJustificativaService;
import br.ufrn.imd.services.VinculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class JustificativaUsuarioEditarController implements Initializable {
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<TipoJustificativa> cbTipoJustificativa;
	@FXML
	private DatePicker dpDataInicial;
	@FXML
	private DatePicker dpDataFinal;
	@FXML
	private TextArea taDescricao;
	@FXML
	private ComboBox<Vinculo> cbVinculo;
	@FXML
	private TextField tfComprovante;
	@FXML
	private Button btnProcurar;

	private ImdAuth imdAuth;

	private JustificativaFaltaService service = new JustificativaFaltaService();

	private JustificativaFalta justificativaFalta = new JustificativaFalta();
	
	private VinculoService serviceVinculo = new VinculoService();
	
	private TipoJustificativaService serviceTipoJustificativa = new TipoJustificativaService();

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void handleCancelar() throws IOException {
		imdAuth.iniciarTelaPrincipal();
	}

	public void setJustificativaFalta(JustificativaFalta justificativaFalta) {
		this.justificativaFalta = justificativaFalta;

		LocalDate dataInicio = justificativaFalta.getDataInicio().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate dataFinal = justificativaFalta.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		cbVinculo.setValue(justificativaFalta.getVinculo());
		dpDataInicial.setValue(dataInicio);
		dpDataFinal.setValue(dataFinal);
		cbTipoJustificativa.setValue(justificativaFalta.getTipoJustificativa());
		taDescricao.setText(justificativaFalta.getDescricao());
	}

	@FXML
	public void handleEditar() throws IOException {
		LocalDate dataInicial = dpDataInicial.getValue();
		Instant instantDI = Instant.from(dataInicial.atStartOfDay(ZoneId.systemDefault()));
		Date dateInicial = Date.from(instantDI);

		LocalDate dataFinal = dpDataFinal.getValue();
		Instant instantDF = Instant.from(dataFinal.atStartOfDay(ZoneId.systemDefault()));
		Date dateFinal = Date.from(instantDF);

		JustificativaFalta justificativaFalta = new JustificativaFalta(dateInicial, dateFinal,
				cbVinculo.getSelectionModel().getSelectedItem(),
				cbTipoJustificativa.getSelectionModel().getSelectedItem(), null, taDescricao.getText(), null, 'A', "",
				null, 0, 0, "");
		int resultado = service.justificativaFaltaEditar(justificativaFalta);
		if (resultado == 200) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText(null);
			alert.setContentText("Dado criado!");

			alert.showAndWait();
			imdAuth.iniciarJustificativaListar();
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
		Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Type listType = new TypeToken<ArrayList<Vinculo>>() {
		}.getType();
		Collection<Vinculo> vinculos = gson1.fromJson(serviceVinculo.VinculoListar(), listType);

		cbVinculo.getItems().addAll(vinculos);
		cbVinculo.setConverter(new VinculoConverter());

		Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Type listTypeS = new TypeToken<ArrayList<TipoJustificativa>>() {
		}.getType();
		Collection<TipoJustificativa> tipoJustificativaes = gson2.fromJson(serviceTipoJustificativa.tipoJustificativaListar(), listTypeS);

		cbTipoJustificativa.getItems().addAll(tipoJustificativaes);
		cbTipoJustificativa.setConverter(new TipoJustificativaConverter());

	}

}
