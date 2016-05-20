package br.ufrn.imd.controllers;

import br.ufrn.imd.main.ImdAuth;
import javafx.application.Preloader;
import javafx.stage.Stage;

public class InicioController extends Preloader {
	
	private ImdAuth imdAuth;
	
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	public void setImdAuth(ImdAuth imdAuth) {
        this.imdAuth = imdAuth;
    }
}
