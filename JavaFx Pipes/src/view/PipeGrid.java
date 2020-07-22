package view;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;

public class PipeGrid extends GridPane {
	
	private StringProperty FPipeFileName;
	private StringProperty lPipeFileName;
	private StringProperty startPipeFileName;
	private StringProperty endPipeFileName;
	
	char type;
	
	char[][] pipeBoard;
		
	public char[][] getPipeBoard() {
		return pipeBoard;
	}

	public void setPipeBoard(char[][] pipeBoard) {
		this.pipeBoard = pipeBoard;
	}
	
	public String getEndPipeFileName() {
		return endPipeFileName.get();
	}

	public void setEndPipeFileName(String pipeFileName) {
		this.endPipeFileName.set(pipeFileName);
		
	}
	public String getStartPipeFileName() {
		return startPipeFileName.get();
	}

	public void setStartPipeFileName(String pipeFileName) {
		this.startPipeFileName.set(pipeFileName);
		
	}
	public String getLPipeFileName() {
		return lPipeFileName.get();
	}

	public void setLPipeFileName(String pipeFileName) {
		this.lPipeFileName.set(pipeFileName);
		
	}
	public String getFPipeFileName() {
		return FPipeFileName.get();
	}

	public void setFPipeFileName(String pipeFileName) {
		this.FPipeFileName.set(pipeFileName);
		
	}
	
	
	public PipeGrid() {
		FPipeFileName = new SimpleStringProperty();
		lPipeFileName = new SimpleStringProperty();
		startPipeFileName = new SimpleStringProperty();
		endPipeFileName = new SimpleStringProperty();
		
		
		
	}
	
	public void add(char ch, int columnIndex, int rowIndex) {
		
		TileDisplayer td;
		switch(ch)
		{
		case 'L':
		case 'J':
		case '7':
		case 'F':
			td = GenerateTileDisplayer(ch, columnIndex, rowIndex, getFPipeFileName(), true);
			add(td, columnIndex, rowIndex);
			break;
		case '|':
		case '-':
			td = GenerateTileDisplayer(ch, columnIndex, rowIndex, getLPipeFileName(), true);
			add(td, columnIndex, rowIndex);
			break;
		case 's':
			td = GenerateTileDisplayer(ch, columnIndex, rowIndex, getStartPipeFileName(), false);
			add(td, columnIndex, rowIndex);
			break;
		case 'g':
			td = GenerateTileDisplayer(ch, columnIndex, rowIndex, getEndPipeFileName(), false);
			add(td, columnIndex, rowIndex);
			break;
		}
		// TODO Auto-generated method stub
		
	}
	
	
	

	private TileDisplayer GenerateTileDisplayer(char ch, int columnIndex, int rowIndex, String fileName, boolean action) {
		TileDisplayer td = new  TileDisplayer(ch, fileName, this.getWidth() / (double)pipeBoard[0].length, this.getHeight() / (double)pipeBoard.length);
		if(action)
		{
			td.setOnAction((e)->{
				td.RotatePipe();
				pipeBoard[rowIndex][columnIndex] = td.getPipeType();
				});
		}
		return td;
	}

	public void load() {
		for(int row = 0 ; row < pipeBoard.length; row++)
		{
			for(int col= 0 ; col < pipeBoard[row].length;col++)
			{
				add(pipeBoard[row][col], col, row);
			}
		}
		
	}

	public void SolvePuzzle(ArrayList<String> actions) {
		String[] actionChars;
		int row,col, numRotations;
		for(String action : actions)
		{
			actionChars = action.split(",");
			row = Integer.parseInt(actionChars[0]);
			col = Integer.parseInt(actionChars[1]);
			numRotations = Integer.parseInt(actionChars[2]);
			TileDisplayer td = (TileDisplayer)this.getChildren().get(row*pipeBoard[0].length + col);
			for(int i = 0 ; i < numRotations;i++)
			{
				td.fire();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	



}
