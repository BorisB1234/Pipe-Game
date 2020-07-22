package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TileDisplayer extends Button {
	
	char pipeType = ' ';
	
	private String pipeFileName;
	private double width;
	private double height;
	
	
	

	public TileDisplayer(char pipeType, String pipeFileName, double width, double height) {
		
	
		this.pipeType = pipeType;
		this.pipeFileName = pipeFileName;
		this.width = width;
		this.height = height;
		
		redraw();
	}
	

	void RotatePipe() {
		
		switch(pipeType)
		{
		case '7':
			pipeType = 'J';
			break;
		case 'F':
			pipeType = '7';
			break;
		case 'J':
			pipeType = 'L';
			break;
		case 'L':
			pipeType = 'F';
			break;
		case '|':
			pipeType = '-';
			break;
		case '-':
			pipeType = '|';
			break;
			default:
				return;
		}	
		Rotate(90,30);
		
	}
	private void Rotate(int degrees, int duration) {
		if(duration != 0)
		{
			RotateTransition rt = new RotateTransition(Duration.millis(duration), getGraphic());
			
			rt.setByAngle(degrees);
			
			rt.setInterpolator(Interpolator.LINEAR);
			rt.play();
		}
		else
		{
			getGraphic().setRotate(degrees);
		}
		
		
	}


	public char getPipeType() {
		return pipeType;
	}

	public void redraw()
	{		
		Image pipe = null;
		ImageView iv;
		try {
			pipe = new Image(new FileInputStream(pipeFileName));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		iv = new ImageView(pipe);
		if(width != 0)
			iv.setFitWidth(width- 16);
		else
			iv.setFitWidth(240);
		if(height!=0)
			iv.setFitHeight(height);
		else
			iv.setFitHeight(256);
		setGraphic(iv);
		
		switch(pipeType)
		{
		case 'L':	
			Rotate(270,0);
			break;
		case 'J':
			Rotate(180,0);
			break;
		case '7':
			Rotate(90,0);
			break;
		case '-':
			Rotate(90,10);
			break;
			
		}	
		
	}
	
	



	
}
