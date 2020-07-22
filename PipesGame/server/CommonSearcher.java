package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import serverInterfaces.Searcher;
import serverInterfaces.Solution;

public abstract class CommonSearcher<T,S> implements Searcher<T, S> {

	protected Queue<State<T,S>> openList;
	protected int evaluatedNodes;
	
	public CommonSearcher() {
		
	}
	protected State<T,S> popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}
	
	public int getEvaluatedNodes()
	{
		return evaluatedNodes;
	}
	
	
	protected void addToOpenList(State<T,S> currentState) {
		evaluatedNodes++;
		openList.add(currentState);
		
	}
	protected boolean openListContains(State<T,S> state) {
		evaluatedNodes++;
		return openList.contains(state);
	}
	
	protected State<T, S> findState(State<T, S> state)
	{
		evaluatedNodes++;
		Iterator<State<T, S>> it = openList.iterator();
		while (it.hasNext()) 
		{
			State<T, S> retState = it.next();
			if(state.equals(retState))
				return retState;
		}
		return null;
	}
	protected void removeOpenList(State<T, S> oldState) 
	{
		evaluatedNodes++;
		openList.remove(oldState);
		
	}
	
	protected Solution<S> backTrace(State<T,S> n, State<T,S> initialState) {
		ArrayList<Action<S>> sol = new ArrayList<Action<S>>();
		
		while(n.getAction()!=null)
		{
			sol.add(n.getAction());
			n=n.getCameFrom();
			
		}
		//System.out.println("Evaluated Nodes " + evaluatedNodes);
		return new CommonSolution<S>(sol);
	}
	
	/*public Action<S> Delta(State<T> s1, State<T> s2)
	{
		return null;
	}*/
	

	
}
