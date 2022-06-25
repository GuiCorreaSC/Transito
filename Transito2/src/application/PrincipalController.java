package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PrincipalController {

	@FXML
	TabPane pane;

	@FXML
	public void abreCadastroRodovia() {
		try {
			boolean aberta = false;
			for (Tab tb : pane.getTabs()) {
				if (tb.getText().equals("Cadastro de Rodovias")) {
					aberta = true;
					pane.getSelectionModel().select(tb);
				}
			}
			
			if (!aberta) {
				Tab tab = new Tab("Cadastro de Rodovias");
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource("CadRodovia.fxml")));
				pane.getSelectionModel().select(tab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
