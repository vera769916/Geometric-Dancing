package final_107303001;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class GameController implements Initializable, EventHandler<KeyEvent> {
	
	@FXML
    private Pane _field;

    @FXML
    private Circle c1;

    @FXML
    private Circle c2;

    @FXML
    private Label label_cntdwn;

    @FXML
    private Label label_clock;

    @FXML
    private Label label_start;

    @FXML
    private Label label_score;

    @FXML
    private Label label_comment;
    
    Integer cntdwn_flag = 1;
    Integer pause_flag = 0;
    Integer cntdown_num = 3;
    Integer clockTime = 0;
    Timeline cntdown;
    Timeline clock;
    Timeline rotate;
    Timeline beatMap;
    Timeline cmtlbl;
    MediaPlayer bgmPlayer;
    AudioClip sePlayer;
    //AudioClip sePlayer, bgmPlayer; //prevent IDE discarding
    double theta = 0;
    double theta2 = 0;
    Integer cmtStayCount = 0;
    Integer count = 0;
    Integer bcount = 540;
    Integer beatTimeTotal = 540;
    Integer beat_now = 0;
    public static Integer score = 0;
    public static Integer perfectCnt = 0;
    public static Integer goodCnt = 0;
    public static Integer badCnt = 0;
    public static Integer missCnt = 0;
    LinkedList<Beat> _beats = new LinkedList<Beat>();
    ArrayList<Integer> beatTime = new ArrayList<>(Arrays.asList(270, 270, 270, 270, 180, 180, 270, 270, 270, 270, 180, 180, 270, 270, 270, 270, 180, //1
    															90, 90, 270, 270, 270, 270, 90, 270, 180, 180, 180, 180, 180, 180, 270, 270, 270, 270, //2
    															180, 180, 180, 180, 180, 180, 270, 270, 270, 270, 180, 180, 180, 90, 90, 90, 90, 180, 180, 180, 90, 90, 90, 90, 180, //3
    															180, 180, 90, 180, 180, 90, 180, 180, 180, 540, 180, 180, 180, 180, 180, 180, 270, //4
    															270, 270, 270, 180, 180 ,180, 180, 180, 180, 270, 270, 270, 270, 180, 90, 90, 90, 90, 180, 180, 180, 180, 90, 90, 90, 90, 180, //5
    															180, 180, 90, 90, 90, 90, 90, 180, 180, 180, 90, 90, 90, 90, 180, 90, 540, 270, 270, 270, 270, //6
    															180, 90, 90, 270, 270, 270, 270, 180, 90, 90, 270, 270, 270, 270, 180, //7
    															90, 90, 270, 270, 270, 270, 90, 270, 270, 270, 270, 270, 180, 90, 90, 270, //8
    															270, 270, 270, 180, 90, 90, 270, 270, 270, 270, 180, 90, 90, 270, 270, 270, 270, 180, 180, 270, 270, 270, //9
    															180, 180, 270, 270, 270, 270, 180, 270, 180, 180, 180, 270, 270, 270, 270, 270, 270)); //10
    
    @FXML
	public void handle(KeyEvent event) {
    	if (event.getCode() == KeyCode.SPACE) {
    		bgmPlayer.setVolume(0.1);
    		bgmPlayer.play();
    		BgmTimeClock();
    		if (cntdwn_flag == 1) {
    			cntdwn_flag = 0;
    			label_start.setVisible(false);
    			label_cntdwn.setVisible(true);
    			CntdownClock();
    		}
    	}
    	if (event.getCode() == KeyCode.UP) {
    		sePlayer.play(0.075);
    		beat_judge();
    	}
    	if (event.getCode() == KeyCode.DOWN) {
    		sePlayer.play(0.075);
    		beat_judge();
    	}
    	if (event.getCode() == KeyCode.LEFT) {
    		sePlayer.play(0.075);
    		beat_judge();
    	}
    	if (event.getCode() == KeyCode.RIGHT) {
    		sePlayer.play(0.075);
    		beat_judge();
    	}
    }
    
    public void CntdownClock() {
    	cntdown = new Timeline(new KeyFrame(Duration.millis(375), (e) -> { //BPM:160, 60000/160 = 375
    		if (cntdown_num > 0) {
    			cntdown_num--;
    			String S = cntdown_num + "";
    			label_cntdwn.setText(S);
    		} else {
    			label_cntdwn.setVisible(false);
    			cntdown.stop();
    	        rotate.play();
    	        beatMap.play();
    	        cmtlbl.play();
    		}
        }));
        cntdown.setCycleCount(4);
        cntdown.play();
    }
    
    public void BgmTimeClock() {
    	label_clock.setVisible(true);
    	clock = new Timeline(new KeyFrame(Duration.millis(1000), (e) -> {
    		clockTime++;
			String S = "Time: " + clockTime;
			label_clock.setText(S);
			if (clockTime == 83) {
				missCnt = beatTime.size() - perfectCnt - goodCnt - badCnt;
				clock.stop();
				cntdown.stop();
			    rotate.stop();
			    beatMap.stop();
			    cmtlbl.stop();
			    
				Parent over = null;
				try {
					over = FXMLLoader.load(getClass().getResource("gameOver.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene overScene = new Scene(over);
				overScene.getRoot().requestFocus();
				Main.currentStage.setScene(overScene);
			}
        }));
    	clock.setCycleCount(83);
    	clock.play();
    }
    
    public void create_beat(int bcount) {
		theta2 = 2*Math.PI/720 * bcount;
		beat_now++;
		Beat temp = new Beat(bcount);
		_beats.push(temp);
    	_field.getChildren().add(temp.ring);
    	_field.getChildren().add(temp.beat);
    	temp.beat.setLayoutX(c1.getLayoutX() + 300*Math.sin(theta2));
    	temp.beat.setLayoutY(c1.getLayoutY() + 300*Math.cos(theta2));
    	temp.ring.setLayoutX(c1.getLayoutX() + 300*Math.sin(theta2));
    	temp.ring.setLayoutY(c1.getLayoutY() + 300*Math.cos(theta2));
    }
    
    public void beat_judge() {
    	double x1 = c2.getLayoutX();
		double x2 = _beats.getLast().beat.getLayoutX();
		double y1 = c2.getLayoutY();
		double y2 = _beats.getLast().beat.getLayoutY();
		double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		//System.out.printf("bcount = %d\n", _beats.get(0).bcount-540);
		//System.out.printf("x1 y1 = %f %f      x2 y2 = %f %f\n", x1, y1, x2, y2);
		//System.out.printf("d = %f\n\n", d);
		if (d < 16.0) {
			score = score + 500;
			label_comment.setText("Perfect !");
			label_comment.setAlignment(Pos.CENTER);
			label_comment.setVisible(true);
			cmtStayCount = 0;
			String S = "Score: " + score;
			label_score.setText(S);
			perfectCnt++;
		} else if (d < 35.0) {
			score = score + 300;
			label_comment.setText("Good !");
			label_comment.setAlignment(Pos.CENTER);
			label_comment.setVisible(true);
			cmtStayCount = 0;
			String S = "Score: " + score;
			label_score.setText(S);
			goodCnt++;
		} else if (d < 50.0) {
			score = score + 100;
			label_comment.setText("bad !");
			label_comment.setAlignment(Pos.CENTER);
			label_comment.setVisible(true);
			cmtStayCount = 0;
			String S = "Score: " + score;
			label_score.setText(S);
			badCnt++;
		}
    }
    
    public void Metronome() {
    	if(count == 11) {
    		sePlayer.play(0.15);
    	} else if (count == 22) {
    		sePlayer.play(0.15);
    	} else if (count == 33) {
    		sePlayer.play(0.15);
    	} else if (count == 45) {
    		sePlayer.play(0.15);
    		count = 0;
    	}
    }
    
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
    	//System.out.printf("beatTime = %d\n", beatTime.get(beat_now));
    	create_beat(270);
    	create_beat(540);
    	_beats.get(1).ring.setRadius(65);
    	
    	/*initialize values*/
		LineTo Lineto = new LineTo(c2.getLayoutX(), c2.getLayoutY()); 
        MoveTo moveto = new MoveTo(c1.getLayoutX(), c1.getLayoutY()); 
        Path path = new Path(moveto, Lineto); 
        path.setFill(Color.BLACK); 
        path.setStrokeWidth(2); 
        _field.getChildren().add(path);
        
        
        /*timeline for: beat create and delete*/
        beatMap = new Timeline(new KeyFrame(Duration.millis(25),(e)-> { //240000/BPM = one round time = four beats time //1500/60 now
        	if (beat_now.equals(beatTime.size())) { //end
        		
        	} else {
        		Integer beat_n = beatTime.get(beat_now);
        		for(int i=0; i<12; i++) {
        			bcount++;
        			if (bcount.equals(beatTimeTotal + beat_n)) {
                		create_beat(bcount);
                		beatTimeTotal = beatTimeTotal + beat_n;
                	}
        		}
        	}
        	
        	for(var b : _beats) {
    			b.ring.setRadius(b.ring.getRadius() - 1.5555555); //radius 70 decrease in 540 count : 720
    			if(b.ring.getRadius() < 25) {
    				//sePlayer.play(0.075);
    				_beats.remove(b);
    				_field.getChildren().remove(b.ring);
    				_field.getChildren().remove(b.beat);
    			}
    		}
    	}));
        beatMap.setCycleCount(Timeline.INDEFINITE);
        
        
        /*timeline for: 1. circle rotate 2. line rotate*/
        rotate = new Timeline(new KeyFrame(Duration.millis(25),(e)-> { //240000/BPM = one round time = four beats time
        	theta = 2*Math.PI/60 * count;
        	
        	c2.setLayoutX(c1.getLayoutX() + 300*Math.sin(theta));
    		c2.setLayoutY(c1.getLayoutY() + 300*Math.cos(theta));
        	
    		Lineto.setX(c1.getLayoutX() + 300*Math.sin(theta));
    		Lineto.setY(c1.getLayoutY() + 300*Math.cos(theta));
    		
        	count++;
    	}));
        rotate.setCycleCount(Timeline.INDEFINITE);
        
        
        /*timeline for: comment label control*/
        cmtlbl = new Timeline(new KeyFrame(Duration.millis(100),(e)-> {
        	if (cmtStayCount > 5) {
        		label_comment.setVisible(false);
        	} else {
        		cmtStayCount++;
        	}
        }));
        cmtlbl.setCycleCount(Timeline.INDEFINITE);
        
        
        /*AudioClip for soundeffect & bgm*/
        String bgmFileName = "Spin2Win.wav";
        String seFileName = "soft-hitnormal.wav";
        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/src/final_107303001/" + bgmFileName);
        bgmPlayer = new MediaPlayer(m);
        sePlayer = new AudioClip("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/src/final_107303001/" + seFileName);
    }
    
    
}