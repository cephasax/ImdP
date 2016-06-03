package br.ufrn.imd.main;

import java.io.IOException;

import br.ufrn.imd.view.cargo.*;
import br.ufrn.imd.view.justificativaFalta.*;
import br.ufrn.imd.view.maquina.*;
import br.ufrn.imd.view.outras.*;
import br.ufrn.imd.view.ponto.*;
import br.ufrn.imd.view.setor.*;
import br.ufrn.imd.view.tipoJustificativa.*;
import br.ufrn.imd.view.unidade.*;
import br.ufrn.imd.view.usuario.*;
import br.ufrn.imd.view.vinculo.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ImdAuth extends Application {

	private Stage primaryStage;
	public BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Biometric Auth IMD");
		showMainView();

	}

	public void showMainView() throws IOException {
		// Carrega a tela principal
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/outras/Inicio.fxml"));
		rootLayout = (BorderPane) loader.load();

		// Mostra a cena
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

		InicioController controller = loader.getController();
		controller.setMainApp(this);

	}

	public void iniciarAutenticacao() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/outras/Autenticando.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		AutenticandoController controller = loader.getController();
		controller.setMainApp(this);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void iniciarCargoBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/cargo/CargoBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		CargoBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarCargoCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/cargo/CargoCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		CargoCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarCargoEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/cargo/CargoEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		CargoEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarCargoListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/cargo/CargoListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		CargoListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarJustificativaBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}
	
	public void iniciarJustificativaGestorEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaGestorEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaGestorEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarJustificativaCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarJustificativaListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarJustificativaUsuarioEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaUsuarioEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaUsuarioEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarJustificativaUsuarioListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/justificativaFalta/JustificativaUsuarioListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		JustificativaUsuarioListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarMaquinaBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/maquina/MaquinaBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		MaquinaBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarMaquinaCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/maquina/MaquinaCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		MaquinaCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarMaquinaEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/maquina/MaquinaEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		MaquinaEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarMaquinaListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/maquina/MaquinaListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		MaquinaListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoAvulsoCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoAvulsoCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoAvulsoCriarController controller = loader.getController();
		controller.setMainApp(this);
	}
	
	public void iniciarPontoConfirmar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoConfirmar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoConfirmarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoGestorEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoGestorEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoGestorEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoNormalCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoNormalCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoNormalCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoUsuarioListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoUsuarioListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoUsuarioListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarPontoListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/ponto/PontoListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		PontoListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarSetorListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/setor/SetorListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		SetorListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarSetorBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/setor/SetorBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		SetorBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarSetorCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/setor/SetorCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		SetorCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarSetorEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/setor/SetorEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		SetorEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarTipoJustificativaListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/tipoJustificativa/TipoJustificativaListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		TipoJustificativaListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarTipoJustificativaBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/tipoJustificativa/TipoJustificativaBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		TipoJustificativaBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarTipoJustificativaCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/tipoJustificativa/TipoJustificativaCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		TipoJustificativaCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarTipoJustificativaEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/tipoJustificativa/TipoJustificativaEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		TipoJustificativaEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUnidadeBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/unidade/UnidadeBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UnidadeBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUnidadeListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/unidade/UnidadeListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UnidadeListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUnidadeCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/unidade/UnidadeCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UnidadeCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUnidadeEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/unidade/UnidadeEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UnidadeEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioCriarAcesso() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioCriarAcesso.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioCriarAcessoController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioEditarAcesso() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioEditarAcesso.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioEditarAcessoController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarUsuarioListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/usuario/UsuarioListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		UsuarioListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarVinculoBuscar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/vinculo/VinculoBuscar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		VinculoBuscarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarVinculoCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/vinculo/VinculoCriar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		VinculoCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarVinculoEditar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/vinculo/VinculoEditar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		VinculoEditarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarVinculoListar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/vinculo/VinculoListar.fxml"));
		AnchorPane newScreen = (AnchorPane) loader.load();

		rootLayout.setCenter(newScreen);
		VinculoListarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void iniciarTelaPrincipal() throws IOException {
		// Carrega a tela principal
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/outras/Inicio.fxml"));
		rootLayout = (BorderPane) loader.load();

		// Mostra a cena
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		getPrimaryStage().show();

		InicioController controller = loader.getController();
		controller.setMainApp(this);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
