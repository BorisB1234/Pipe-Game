package server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import serverInterfaces.Searchable;
import serverInterfaces.Solution;

public class DFS<T,S> extends CommonSearcher<T, S> {
	
	@Override
	public Solution<S> Search(Searchable<T, S> s) {
		
		HashSet<State<T,S>> closedSet = new HashSet<State<T,S>>();
		Stack <State<T,S>> openStack = new Stack<State<T,S>>();
        State<T,S> state = s.getInitialState();
        
        closedSet.add(state);
        openStack.add(state);
        evaluatedNodes++;
        
        while (openStack.size() != 0)
        {
        //	System.out.println("Stack size " + openStack.size());
            state = openStack.pop();
           // System.out.println(state);
            evaluatedNodes++;
            if(s.isGoalState(state))
				return backTrace(state, s.getInitialState());
            Iterator<State<T,S>> i=s.getAllPossibleStates(state).listIterator();
            while (i.hasNext())
            {
            	State<T,S> n = i.next();
                if (!closedSet.contains(n))
                {
                	n.setCameFrom(state);
                    closedSet.add(n);
                    openStack.add(n);
                    evaluatedNodes++;
                }
            }
        }
        return null;
	}

}
