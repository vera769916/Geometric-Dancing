package final_107303001;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController {
	
	@FXML
    private Button btn_start;
	
	@FXML
    private Button btn_exit;
	
	@FXML
	public void onStartPressed() throws IOException {
		Parent game = FXMLLoader.load(getClass().getResource("GeometricDancing.fxml"));
		Scene gameScene = new Scene(game);
		gameScene.getRoot().requestFocus();
		Main.currentStage.setScene(gameScene);
	}
	
	@FXML
	public void onExitPressed( ) {
		Main.currentStage.close();
	}
}