package br.ufrn.imd.main;

import java.io.IOException;

import br.ufrn.imd.view.outras.AutenticandoController;
import br.ufrn.imd.view.outras.InicioController;
import br.ufrn.imd.view.unidade.UnidadeCriarController;
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
		AnchorPane telaAutenticar = (AnchorPane) loader.load();

		rootLayout.setCenter(telaAutenticar);
		AutenticandoController controller = loader.getController();
		controller.setMainApp(this);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void iniciarUnidadeCriar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ImdAuth.class.getResource("../view/unidade/UnidadeCriar.fxml"));
		AnchorPane telaUnidadeCriar = (AnchorPane) loader.load();

		rootLayout.setCenter(telaUnidadeCriar);
		UnidadeCriarController controller = loader.getController();
		controller.setMainApp(this);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
