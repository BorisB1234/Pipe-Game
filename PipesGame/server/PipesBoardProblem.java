package server;

import java.util.ArrayList;

import serverInterfaces.Searchable;

public class PipesBoardProblem implements Searchable<PipesState,PipesAction> {

	private static final long serialVersionUID = 1L;
	private char initialState[][];
	private enum Direction{DOWN,LEFT,UP,RIGHT};
	private int sRow,sCol;
	public PipesBoardProblem(char initialState[][]) {
		this.initialState = initialState;
		
	}
	
	@Override
	public int hashCode()
	{
		//System.out.println(this.toString());
		return this.toString().hashCode();
	}
	

	@Override
	public State<PipesState,PipesAction> getInitialState() {
		int[] rowCol = new int[2];
		PipesState s = new PipesState(initialState);
		State<PipesState,PipesAction> sReturn = new State<PipesState,PipesAction>(s);
		getStart(sReturn,rowCol);
		sRow = rowCol[0];
		sCol = rowCol[1];
		return sReturn;
	}

	@Override
	public boolean isGoalState(State<PipesState,PipesAction> state) {
		char mat[][] = state.getState().getMat();
		return isGoalMat(mat, sRow, sCol, true, Direction.DOWN,false);
	}
	
	private void getStart(State<PipesState,PipesAction> state, int[] out)
	{
		state.getState().getPos(out,'s');
	}
	
	
	private boolean isGoalMat(char[][] mat,int row, int col, boolean isStart, Direction d, boolean firstSearch)
	{
		if(row < 0 || col < 0 || row >= mat.length || col >= mat[0].length)
			return false;
		if(isStart)
			return isGoalMat(mat,row + 1,col,false,Direction.DOWN,true) || isGoalMat(mat,row,col - 1,false, Direction.LEFT,true) || isGoalMat(mat,row - 1,col,false,Direction.UP,true) || isGoalMat(mat,row,col + 1,false,Direction.RIGHT,true); 
		
		switch(mat[row][col])
		{
		case '-':
			if(d == Direction.RIGHT)
				return isGoalMat(mat,row ,col + 1,false,Direction.RIGHT,false);
			if(d == Direction.LEFT)
				return isGoalMat(mat,row ,col - 1,false,Direction.LEFT,false);
			break;
		case '|':
			if(d == Direction.DOWN)
				return isGoalMat(mat, row + 1 ,col, false,Direction.DOWN,false);
			if(d == Direction.UP)
				return isGoalMat(mat, row - 1 ,col, false,Direction.UP,false);
			break;
		case '7':
			if(d == Direction.UP)
				return isGoalMat(mat, row ,col - 1 , false,Direction.LEFT,false);
			if(d == Direction.RIGHT)
				return isGoalMat(mat, row + 1 ,col, false,Direction.DOWN,false);
			break;
			
		case 'L':
			if(d == Direction.DOWN)
				return isGoalMat(mat, row ,col + 1 , false,Direction.RIGHT,false);
			if(d == Direction.LEFT)
				return isGoalMat(mat, row - 1 ,col, false,Direction.UP,false);
			break;
		case 'J':
			if(d == Direction.RIGHT)
				return isGoalMat(mat, row - 1 ,col , false,Direction.UP,false);
			if(d == Direction.DOWN)
				return isGoalMat(mat, row ,col - 1, false,Direction.LEFT,false);
			break;
			
		case 'F':
			if(d == Direction.LEFT)
				return isGoalMat(mat, row + 1 ,col , false,Direction.DOWN,false);
			if(d == Direction.UP)
				return isGoalMat(mat, row ,col + 1, false,Direction.RIGHT,false);
			break;
		case 'g':
			if(firstSearch)
				return false;
			else
				return true;
			
		default: 
			 return false;
		}
		
			
		
		return false;
	}

	/*@Override
	public ArrayList<State<PipesState,PipesAction>> getAllPossibleStates(State<PipesState,PipesAction> state) {
		State<PipesState,PipesAction> s;
		char mat[][] = state.getState().getMat();
		ArrayList<State<PipesState,PipesAction>> list = new ArrayList<State<PipesState,PipesAction>>();
		for(int row = 0 ; row < mat.length; row++)
		{
			for(int col = 0; col < mat[row].length; col++)
			{
			
				switch(mat[row][col])
				{
				case '-':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'|')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					
					break;
				case '|':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'-')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					
					break;
				case '7':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'J')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					
					break;
				case 'L':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'F')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					
					break;
				case 'J':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'L')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					break;
				case 'F':
					s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,'7')));
					list.add(s);
					s.setAction(new Action<PipesAction>(new PipesAction(row,col,1)));
					break;
				default: 
					 break;
				}
			}
		}
		
		return list;
	}*/
	
	@Override
	public ArrayList<State<PipesState,PipesAction>> getAllPossibleStates(State<PipesState,PipesAction> state) {
		ArrayList<State<PipesState,PipesAction>> list = new ArrayList<State<PipesState,PipesAction>>();
		
		
		
		getPossibleState(state, sRow + 1, sCol, Direction.DOWN, list);
		getPossibleState(state, sRow - 1, sCol, Direction.UP, list);
		getPossibleState(state, sRow, sCol + 1, Direction.RIGHT, list);
		getPossibleState(state, sRow, sCol - 1, Direction.LEFT, list);
		return list;
	}
	
	
	private void getPossibleState(State<PipesState,PipesAction> state, int row , int col, Direction d, ArrayList<State<PipesState,PipesAction>> list)
	{
		char mat[][] = state.getState().getMat();
		
		
		if(row < 0 || col < 0 || row >= mat.length || col >= mat[0].length)
			return;
		switch(mat[row][col])
		{
		case '-':
			if(d==Direction.DOWN || d==Direction.UP) 
			{
				generateState(list,mat,row,col,'|',1);
			}
			if(d==Direction.LEFT)
				getPossibleState(state,row,col-1,d,list);
			if(d==Direction.RIGHT)
				getPossibleState(state,row,col+1,d,list);
			break;
		case '|':
			if(d==Direction.LEFT || d==Direction.RIGHT) {
				generateState(list,mat,row,col,'-',1);
			}
			if(d==Direction.DOWN)
				getPossibleState(state,row+1,col,d,list);
			if(d==Direction.UP)
				getPossibleState(state,row-1,col,d,list);
			break;	
		case '7':
			
			if(d==Direction.LEFT)
			{
				generateState(list,mat,row,col,'F',3);
			}
			if(d==Direction.DOWN)
			{
				generateState(list,mat,row,col,'L',2);
			}
			
			if(d==Direction.RIGHT)
			{
				generateState(list,mat,row,col,'J',1);
				getPossibleState(state,row+1,col,Direction.DOWN,list);
			}
			if(d==Direction.UP)
			{
				generateState(list,mat,row,col,'F',3);
				getPossibleState(state,row,col-1,Direction.LEFT,list);
			}
			break;	
		case 'L':
			if(d==Direction.UP)
			{
				generateState(list,mat,row,col,'F',1);
			}
			
			if(d==Direction.RIGHT) {
				generateState(list,mat,row,col,'7',2);
			}
			if(d==Direction.LEFT)
			{
				generateState(list, mat, row, col, 'F', 1);
				getPossibleState(state,row-1,col,Direction.UP,list);
			}
			if(d==Direction.DOWN)
			{
				generateState(list, mat, row, col, 'J', 3);
				getPossibleState(state,row,col+1,Direction.RIGHT,list);
			}
			break;	
		case 'J':
			if(d==Direction.LEFT || d==Direction.UP)
			{
				generateState(list, mat, row, col, 'F', 2);
			}
			if(d==Direction.RIGHT)
			{
				generateState(list, mat, row, col, '7', 3);
				getPossibleState(state,row-1,col,Direction.UP,list);
			}
			if(d==Direction.DOWN){
				generateState(list, mat, row, col, 'L', 1);
				getPossibleState(state,row,col-1,Direction.LEFT,list);
			}
			break;	
		case 'F':
			if(d==Direction.DOWN)
			{
				generateState(list, mat, row, col, 'J', 2);
			}
			if(d==Direction.RIGHT)
			{
				generateState(list, mat, row, col, '7', 1);
			}
			if(d==Direction.LEFT)
			{
				generateState(list, mat, row, col, 'L', 3);
				getPossibleState(state,row+1,col,Direction.DOWN,list);
			}
			if(d==Direction.UP)
			{
				generateState(list, mat, row, col, '7', 1);
				getPossibleState(state,row,col+1,Direction.RIGHT,list);
			}
			break;	
		}
			
	}
	
	private void generateState(ArrayList<State<PipesState,PipesAction>> list, char[][] mat, int row, int col , char pipeShape, int numActions)
	{
		State<PipesState,PipesAction> s;
		s = new State<PipesState,PipesAction>(new PipesState(generateMat(mat,row,col,pipeShape)));
		list.add(s);
		s.setAction(new Action<PipesAction>(new PipesAction(row,col,numActions)));
		
	}
	
	private char[][] generateMat(char[][] mat, int row, int col, char ch)
	{
		char[][] newMat = new char[mat.length][mat[0].length];
		for(int i = 0 ; i < mat.length;i++)
		{
			for(int j = 0 ; j < mat[0].length; j++)
			{
				newMat[i][j]=mat[i][j];
			}
		}
		newMat[row][col] = ch;
		return newMat;
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for(int i =0;i<initialState.length;i++)
		{
			for(int j = 0;j<initialState[0].length;j++)
			{
				s.append(initialState[i][j] + ",");
			}
			s.append("\n");
		}
		return s.toString();
	}

}
