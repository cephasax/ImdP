package br.ufrn.imd.main;

import java.io.IOException;

import br.ufrn.imd.controllers.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ImdAuth extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Biometric Auth IMD");
		showMainView();
	}
	
	private void showMainView() throws IOException{
		//Carrega a tela principal
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ImdAuth.class.getResource("../view/outras/Inicio.fxml"));
        rootLayout = (AnchorPane) loader.load();
              
        //Mostra a cena
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        InicioController inicioController = loader.getController();
        inicioController.setMainApp(this);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
	

