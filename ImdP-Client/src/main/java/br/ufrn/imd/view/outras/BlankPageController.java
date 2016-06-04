package br.ufrn.imd.view.outras;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlankPageController implements Initializable {
	@FXML
	private ImageView imgPonto;

	private ImdAuth imdAuth;
	
	public void initialize(URL location, ResourceBundle resources) {
		imgPonto.setImage(
				new Image(ImdAuth.class.getResourceAsStream("../../../../main/resources/images/pontoAntigo/logo.png")));

	}

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
