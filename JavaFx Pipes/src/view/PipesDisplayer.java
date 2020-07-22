package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PipesDisplayer extends Canvas {

	int[][] pipesData;
	private StringProperty pipeFileName;
	
	public PipesDisplayer()
	{
		pipeFileName = new SimpleStringProperty();
	}
	
	
	public String getPipeFileName() {
		return pipeFileName.get();
	}

	public void setPipeFileName(String pipeFileName) {
		this.pipeFileName.set(pipeFileName);
	}

	public int[][] getPipesData() {
		return pipesData;
	}

	public void setPipesData(int[][] pipesData) {
		this.pipesData = pipesData;
		redraw();
	}
	
	public void redraw()
	{
		if(pipesData!=null)
		{
			double W = getWidth();
			double H = getHeight();
			double w,h;
			w = (W/pipesData[0].length);
			h = (H/pipesData.length);
			Image pipe = null;
			try {
				pipe = new Image(new FileInputStream(pipeFileName.get()));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			GraphicsContext gc = getGraphicsContext2D();

			
			for (int i = 0; i < pipesData.length; i++) {
				for (int j = 0; j < pipesData[i].length; j++) {
					if(pipesData[i][j] == 0)
					{
						gc.drawImage(pipe,j*w,i*h,w,h);
					}
				}
			}
		}
	}
	
}
