package br.ufrn.imd.view.outras;

import br.ufrn.imd.main.ImdAuth;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class AutenticandoController {
	@FXML
	private ImageView imgAutenticando;
	@FXML
	private ProgressBar progressbar;

	private ImdAuth imdAuth;

	public void setMainApp(ImdAuth imdAuth) {
		this.imdAuth = imdAuth;

	}
}
