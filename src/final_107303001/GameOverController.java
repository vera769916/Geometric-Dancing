package final_107303001;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameOverController implements Initializable, EventHandler<KeyEvent> {

    @FXML
    private Label label_score;

    @FXML
    private Label label_perfect;

    @FXML
    private Label label_good;

    @FXML
    private Label label_bad;

    @FXML
    private Label label_miss;

    @FXML
    public void handle(KeyEvent event) {
    	if (event.getCode() == KeyCode.SPACE) {
    		Parent menu = null;
			try {
				menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene menuScene = new Scene(menu);
    		menuScene.getRoot().requestFocus();
    		Main.currentStage.setScene(menuScene);
    	}
    }
    
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
    	String S =  "Score: " + GameController.score;
    	label_score.setText(S);
    	
    	S = "Perfect: " + GameController.perfectCnt;
    	label_perfect.setText(S);
    	
    	S = "Good: " + GameController.goodCnt;
    	label_good.setText(S);
    	
    	S = "Bad: " + GameController.badCnt;
    	label_bad.setText(S);
    	
    	S = "Miss: " + GameController.missCnt;
    	label_miss.setText(S);
    }
}