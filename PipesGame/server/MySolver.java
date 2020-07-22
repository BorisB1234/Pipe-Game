package server;

import serverInterfaces.Grader;
import serverInterfaces.Searchable;
import serverInterfaces.Searcher;
import serverInterfaces.Solution;
import serverInterfaces.Solver;

public class MySolver<T,S> implements Solver<T,S> {
	private Searcher<T,S> searcher;
	public MySolver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Solution<S> Solve(Searchable<T,S> s) {
		//System.out.println(s.getInitialState().getState().getClass().getName());
		searcher = new HillClimbing<T,S>((Grader<T, S>) new PipesGrader());
		//searcher = new BestFS<T,S>();
		//searcher = new DFS<T,S>();
		//searcher = new BreadthFS<T,S>();
		
		return searcher.Search(s);
		
	}

}
