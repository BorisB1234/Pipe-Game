package server;


import java.util.ArrayList;
import java.util.List;

import serverInterfaces.Solution;

public class PipesSolution implements Solution<PipesAction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Action<PipesAction>> list;
	
	public PipesSolution() {
		
	}

	@Override
	public List<Action<PipesAction>> GetSolution() {
		return list;
	}

}
