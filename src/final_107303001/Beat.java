package final_107303001;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Beat {
	int bcount = 0;
	Circle beat;
	Circle ring;
	
	Beat(int a)
	{
		bcount = a;
		Circle beat = new Circle(30);
		beat.setFill(Color.WHITE);
    	beat.setStrokeWidth(3);
    	beat.setStrokeType(StrokeType.CENTERED);
    	beat.setStroke(Color.BLACK);
		this.beat = beat;
		
		Circle ring = new Circle(100);
		ring.setFill(Color.TRANSPARENT);
    	ring.setStrokeWidth(2);
    	ring.setStrokeType(StrokeType.CENTERED);
    	ring.setStroke(Color.BLACK);
		this.ring = ring;
    }
}
