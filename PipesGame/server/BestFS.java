package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import serverInterfaces.Searchable;
import serverInterfaces.Solution;

public class BestFS<T,S> extends CommonSearcher<T,S> {

	
	@Override
	public Solution<S> Search(Searchable<T,S> s) 
	{
		openList = new PriorityQueue<State<T,S>>();
		boolean inClosedSet;
		boolean inOpenList;
		HashMap<State<T,S>,Integer> closedSet=new HashMap<State<T,S>,Integer>();
		HashSet<State<T,S>> openHashSet = new HashSet<State<T,S>>();
		State<T,S> oldState = null;
		int costOldState = 0;
		
		addToOpenList(s.getInitialState());
		openHashSet.add(s.getInitialState());
		while(openList.size()>0)
		{
			
			State<T,S> n = popOpenList();// dequeue
			//System.out.println(n);
			closedSet.put(n,(Integer)n.getCost());
			if(s.isGoalState(n))
				return backTrace(n, s.getInitialState());
			// private method, back traces through the parents
			ArrayList<State<T,S>> successors=s.getAllPossibleStates(n);//however it is implemented
			for(State<T,S> state: successors)
			{
				
				state.setCost(n.getCost() + 1);
				inClosedSet = closedSet.containsKey(state);
				inOpenList = openHashSet.contains(state);
				if(!inClosedSet && !inOpenList)
				{
					state.setCameFrom(n);
					openList.offer(state);
					openHashSet.add(state);
					evaluatedNodes++;
								
				} 
				else
				{

					if(inClosedSet)
						costOldState = closedSet.get(state);
					else if(inOpenList)
					{
						oldState = findState(state);
						costOldState = oldState.getCost();
					}
					
					if(state.getCost() < costOldState)
					{
						if(!inOpenList)
							addToOpenList(state);
						else
						{
							removeOpenList(oldState);
							addToOpenList(state);
						}
					}	
					
				}
				
			}
		
		}
		return null;
	}


	/*
	private State<T, S> findStateSpecific(State<T, S> state, HashSet<State<T,S>> closedSet) {
		State<T,S> s = findState(state);
		if(s != null)
			return s;
		Iterator<State<T, S>> it = closedSet.iterator();
		while (it.hasNext()) 
		{
			State<T, S> retState = it.next();
			if(state.equals(retState))
				return retState;
		}
		return null;
		}
		*/
		
	

	
	
}
