package br.ufrn.imd.controllers;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UnidadeCriarController {

    private ImdAuth imdAuth;
	private String nome;
	
	@FXML
    private TextField tfNomeUnidade;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;
  
    /**
     * O construtor � chamado antes do m�todo inicialize().
     */
    public UnidadeCriarController() {
    }

    /**
     * Inicializa a classe controller. Este m�todo � chamado automaticamente
     *  ap�s o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setImdAuth(ImdAuth imdAuth) {
        this.imdAuth = imdAuth;

    }
}
