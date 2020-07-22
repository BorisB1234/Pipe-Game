package server;

import java.io.Serializable;

public class PipesAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int change;
	
	public PipesAction() {
		
	}
	public PipesAction(int row, int col, int change) {
		this.row=row;
		this.col=col;
		this.change=change;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	@Override
	public String toString()
	{
		return row + "," + col +"," + change; 
	}
}
