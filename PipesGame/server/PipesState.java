package server;



public class PipesState {
	private char mat[][];
	private enum Direction{DOWN,LEFT,UP,RIGHT};
	
	public PipesState(char state[][]) {
		mat = state;
	}
	public char[][] getMat()
	{
		return mat;
	}
	
	@Override
	public int hashCode()
	{
		String s = this.toString();
		return s.hashCode();
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null) {
	        return false;
	    }
		try
		{
			PipesState o1 = (PipesState)o;
			for(int row = 0; row < mat.length;row++)
			{
				for(int col = 0;col<mat[0].length;col++)
				{
					if(mat[row][col]!=o1.mat[row][col])
						return false;
				}
			}
			
		}
		catch(ClassCastException ex)
		{
			return false;
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			return false;
		}
		return true;
	}
	
	
	
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for(int i =0;i<mat.length;i++)
		{
			for(int j = 0;j<mat[0].length;j++)
			{
				s.append(mat[i][j] + ",");
			}
			s.append("\n");
		}
		return s.toString();
	}
	public void getPos(int[] out ,char c) {
		
		int sRow = 0;
		int sCol = 0;
		for(int row = 0 ; row < mat.length; row++)
		{
			for(int col = 0; col < mat[row].length; col++)
			{
				if(mat[row][col] == c)
				{
					sRow = row;
					sCol = col;
				}
			}
		}
		out[0] = sRow;
		out[1] = sCol;
		
	}
	
	
	
	public int getHighestFlowPos(int[] out,int row, int col, boolean isStart, Direction d, boolean firstSearch)
	{
		int max=0, currentMax = 0;
		int[] currentOut = new int[2];
		if(row < 0 || col < 0 || row >= mat.length || col >= mat[0].length)
		{
			return 0;
		}
		currentOut[0] = row;
		currentOut[1] = col;
		if(isStart)
		{
			max = getHighestFlowPos(currentOut,row + 1,col,false,Direction.DOWN,true);
			if(currentMax < max)
			{
				currentMax = max;
				out[0] = currentOut[0];
				out[1] = currentOut[1];
			}
			max = getHighestFlowPos(currentOut,row,col - 1,false, Direction.LEFT,true);
			if(currentMax < max)
			{
				currentMax = max;
				out[0] = currentOut[0];
				out[1] = currentOut[1];
			}
			
			max = getHighestFlowPos(currentOut,row - 1,col,false,Direction.UP,true);
			if(currentMax < max)
			{
				currentMax = max;
				out[0] = currentOut[0];
				out[1] = currentOut[1];
			}
			max = getHighestFlowPos(currentOut,row,col + 1,false,Direction.RIGHT,true);
			if(currentMax < max)
			{
				currentMax = max;
				out[0] = currentOut[0];
				out[1] = currentOut[1];
			}
		}
		else
		{
			switch(mat[row][col])
			{
			case '-':
				if(d == Direction.RIGHT)
					return getHighestFlowPos(currentOut,row ,col + 1,false,Direction.RIGHT,false)+1;
				if(d == Direction.LEFT)
					return getHighestFlowPos(currentOut,row ,col - 1,false,Direction.LEFT,false)+1;
				
				break;
			case '|':
				if(d == Direction.DOWN)
					return getHighestFlowPos(currentOut, row + 1 ,col, false,Direction.DOWN,false)+1;
				if(d == Direction.UP)
					return getHighestFlowPos(currentOut, row - 1 ,col, false,Direction.UP,false)+1;
				
				break;
			case '7':
				if(d == Direction.UP)
					return getHighestFlowPos(currentOut, row ,col - 1 , false,Direction.LEFT,false)+1;
				if(d == Direction.RIGHT)
					return getHighestFlowPos(currentOut, row + 1 ,col, false,Direction.DOWN,false)+1;
				
				break;
			case 'L':
				if(d == Direction.DOWN)
					return getHighestFlowPos(currentOut, row ,col + 1 , false,Direction.RIGHT,false)+1;
				if(d == Direction.LEFT)
					return getHighestFlowPos(currentOut, row - 1 ,col, false,Direction.UP,false)+1;
				break;
			case 'J':
				if(d == Direction.RIGHT)
					return getHighestFlowPos(currentOut, row - 1 ,col , false,Direction.UP,false)+1;
				if(d == Direction.DOWN)
					return getHighestFlowPos(currentOut, row ,col - 1, false,Direction.LEFT,false)+1;
				break;
			case 'F':
				if(d == Direction.LEFT)
					return getHighestFlowPos(currentOut, row + 1 ,col , false,Direction.DOWN,false)+1;
				if(d == Direction.UP)
					return getHighestFlowPos(currentOut, row ,col + 1, false,Direction.RIGHT,false)+1;
				break;
			case 'g':
				if(firstSearch)
					return 0;
			default: 
				 return 0;
			}
		}
		return currentMax;
			
		
		
	}
	

}
