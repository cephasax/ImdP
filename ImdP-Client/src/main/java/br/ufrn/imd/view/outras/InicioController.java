package br.ufrn.imd.view.outras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class InicioController implements Initializable {

	private ImdAuth imdAuth;

	@FXML
	private ImageView imgPonto;

	@FXML
	private ImageView imdLogo;

	@FXML
	private Text lblAindaNaoECadastrado;

	@FXML
	private Label lblCPF;

	@FXML
	private Label lblSenha;

	@FXML
	private Label lblVinculo;

	@FXML
	private Button btnLogar;

	@FXML
	private TextField txtLogin;

	@FXML
	private ComboBox<?> cbVinculo;

	@FXML
	private TextField tfSenha;

	@FXML
	private TextField tfCPF;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Text lblCliqueAqui;

	@FXML
	private Menu menuMenu;

	@FXML
	private Menu menuSistema;

	@FXML
	private Menu menuUnidade;

	@FXML
	private Menu menuSetor;

	@FXML
	private Menu menuUsuario;

	@FXML
	private Menu menuAjuda;

    @FXML
    private MenuItem menuItemLogin;

    @FXML
    private MenuItem menuItemLogout;
	
	@FXML
	public void handleBtnLogin() throws IOException {
		imdAuth.iniciarPontoNormalCriar();
	}

	@FXML
	public void handleCliqueAqui() throws IOException {
		imdAuth.iniciarUsuarioCriarAcesso();
	}

	@FXML
	public void handleCargoCriar() throws IOException {
		imdAuth.iniciarCargoCriar();
	}

	@FXML
	public void handleCargoBuscar() throws IOException {
		imdAuth.iniciarCargoBuscar();
	}

	@FXML
	public void handleCargoEditar() throws IOException {
		imdAuth.iniciarCargoEditar();
	}

	@FXML
	public void handleCargoListar() throws IOException {
		imdAuth.iniciarCargoListar();
	}

	@FXML
	public void handleJustificativaBuscar() throws IOException {
		imdAuth.iniciarJustificativaBuscar();
	}

	@FXML
	public void handleJustificativaCriar() throws IOException {
		imdAuth.iniciarJustificativaCriar();
	}

	@FXML
	public void handleJustificativaGestorEditar() throws IOException {
		imdAuth.iniciarJustificativaGestorEditar();
	}

	@FXML
	public void handleJustificativaListar() throws IOException {
		imdAuth.iniciarJustificativaListar();
	}

	@FXML
	public void handleJustificativaUsuarioEditar() throws IOException {
		imdAuth.iniciarJustificativaUsuarioEditar();
	}

	@FXML
	public void handleJustificativaUsuarioListar() throws IOException {
		imdAuth.iniciarJustificativaUsuarioListar();
	}

	@FXML
	public void handleMaquinaBuscar() throws IOException {
		imdAuth.iniciarMaquinaBuscar();
	}

	@FXML
	public void handleMaquinaCriar() throws IOException {
		imdAuth.iniciarMaquinaCriar();
	}

	@FXML
	public void handleMaquinaEditar() throws IOException {
		imdAuth.iniciarMaquinaEditar();
	}

	@FXML
	public void handleMaquinaListar() throws IOException {
		imdAuth.iniciarMaquinaListar();
	}

	@FXML
	public void handlePontoAvulsoCriar() throws IOException {
		imdAuth.iniciarPontoAvulsoCriar();
	}

	@FXML
	public void handlePontoBuscar() throws IOException {
		imdAuth.iniciarPontoBuscar();
	}

	@FXML
	public void handlePontoConfirmar() throws IOException {
		imdAuth.iniciarPontoConfirmar();
	}

	@FXML
	public void handlePontoGestorEditar() throws IOException {
		imdAuth.iniciarPontoGestorEditar();
	}

	@FXML
	public void handlePontoListar() throws IOException {
		imdAuth.iniciarPontoListar();
	}

	@FXML
	public void handlePontoNormalCriar() throws IOException {
		imdAuth.iniciarPontoNormalCriar();
	}

	@FXML
	public void handlePontoUsuarioListar() throws IOException {
		imdAuth.iniciarPontoUsuarioListar();
	}

	@FXML
	public void handleSetorBuscar() throws IOException {
		imdAuth.iniciarSetorBuscar();
	}

	@FXML
	public void handleSetorCriar() throws IOException {
		imdAuth.iniciarSetorCriar();
	}

	@FXML
	public void handleSetorListar() throws IOException {
		imdAuth.iniciarSetorListar();
	}

	@FXML
	public void handleSetorEditar() throws IOException {
		imdAuth.iniciarSetorEditar();
	}

	@FXML
	public void handleTipoJustificativaBuscar() throws IOException {
		imdAuth.iniciarTipoJustificativaBuscar();
	}

	@FXML
	public void handleTipoJustificativaCriar() throws IOException {
		imdAuth.iniciarTipoJustificativaCriar();
	}

	@FXML
	public void handleTipoJustificativaEditar() throws IOException {
		imdAuth.iniciarTipoJustificativaEditar();
	}

	@FXML
	public void handleTipoJustificativaListar() throws IOException {
		imdAuth.iniciarTipoJustificativaListar();
	}

	@FXML
	public void handleUnidadeBuscar() throws IOException {
		imdAuth.iniciarUnidadeBuscar();
	}

	@FXML
	public void handleUnidadeCriar() throws IOException {
		imdAuth.iniciarUnidadeCriar();
	}

	@FXML
	public void handleUnidadeEditar() throws IOException {
		imdAuth.iniciarUnidadeEditar();
	}

	@FXML
	public void handleUnidadeListar() throws IOException {
		imdAuth.iniciarUnidadeListar();
	}

	@FXML
	public void handleUsuarioBuscar() throws IOException {
		imdAuth.iniciarUsuarioBuscar();
	}

	@FXML
	public void handleUsuarioCriarAcesso() throws IOException {
		imdAuth.iniciarUsuarioCriarAcesso();
	}

	@FXML
	public void handleUsuarioCriar() throws IOException {
		imdAuth.iniciarUsuarioCriar();
	}

	@FXML
	public void handleUsuarioEditar() throws IOException {
		imdAuth.iniciarUsuarioEditar();
	}

	@FXML
	public void handleUsuarioEditarAcesso() throws IOException {
		imdAuth.iniciarUsuarioEditarAcesso();
	}

	@FXML
	public void handleUsuarioListar() throws IOException {
		imdAuth.iniciarUsuarioListar();
	}

	@FXML
	public void handleVinculoBuscar() throws IOException {
		imdAuth.iniciarVinculoBuscar();
	}

	@FXML
	public void handleVinculoCriar() throws IOException {
		imdAuth.iniciarVinculoCriar();
	}

	@FXML
	public void handleVinculoEditar() throws IOException {
		imdAuth.iniciarVinculoEditar();
	}

	@FXML
	public void handleVinculoListar() throws IOException {
		imdAuth.iniciarVinculoListar();
	}

	@FXML
	public void handleExit() throws IOException {
		imdAuth.exit();
	}

	@FXML
	public void handleLogout() throws IOException {
		imdAuth.iniciarLogout();
	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}

	@FXML
	public void actionLogin() {
		lblCPF.setVisible(false);
		tfCPF.setVisible(false);
		lblSenha.setVisible(false);
		tfSenha.setVisible(false);
		lblVinculo.setVisible(false);
		cbVinculo.setVisible(false);
		btnCadastrar.setVisible(false);
		txtLogin.setVisible(true);
		btnLogar.setVisible(true);
		lblAindaNaoECadastrado.setVisible(true);
		lblCliqueAqui.setVisible(true);
	}

	@FXML
	public void actionLogar() throws IOException {
		menuAjuda.setDisable(false);
		menuMenu.setDisable(false);
		menuSetor.setDisable(false);
		menuSistema.setDisable(false);
		menuUnidade.setDisable(false);
		menuUsuario.setDisable(false);
		menuItemLogin.setDisable(true);
		menuItemLogout.setDisable(false);

		imdAuth.iniciarTelaPrincipal();
	}

	public void initialize(URL location, ResourceBundle resources) {
		imgPonto.setImage(
				new Image(ImdAuth.class.getResourceAsStream("../../../../main/resources/images/pontoAntigo/logo.png")));
		imdLogo.setImage(new Image(
				ImdAuth.class.getResourceAsStream("../../../../main/resources/images/pontoAntigo/logosmall-old.png")));
		menuItemLogout.setDisable(true);
	}
}
