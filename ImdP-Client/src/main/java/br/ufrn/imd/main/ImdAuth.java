package br.ufrn.imd.main;

import java.io.IOException;

import br.ufrn.imd.controllers.UnidadeCriarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ImdAuth extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/src/main/resources/views/outras/inicio.fxml"));
			//BorderPane root = new BorderPane()
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Mostra a person overview dentro do root layout.
	 */
	public void showUnidade() {
	    try {
	        // Carrega a person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(ImdAuth.class.getResource("views/unidade/CriarUnidade.fxml"));
	        AnchorPane ap = (AnchorPane) loader.load();
	        
	        // Define a person overview no centro do root layout.
	        //rootLayout.setCenter(ap);

	        // Dá ao controlador acesso à the main app.
	        UnidadeCriarController controller = loader.getController();
	        controller.setImdAuth(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
