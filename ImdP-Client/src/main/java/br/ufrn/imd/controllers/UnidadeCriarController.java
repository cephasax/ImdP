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
     * O construtor é chamado antes do método inicialize().
     */
    public UnidadeCriarController() {
    }

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setImdAuth(ImdAuth imdAuth) {
        this.imdAuth = imdAuth;

    }
}
