package server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import serverInterfaces.Searchable;
import serverInterfaces.Solution;

public class BreadthFS<T,S> extends CommonSearcher<T,S> {

	
	@Override
	public Solution<S> Search(Searchable<T, S> s) {
		HashSet<State<T,S>> closedSet=new HashSet<State<T,S>>();
        openList = new LinkedList<State<T,S>>();
        State<T,S> state = s.getInitialState();
        
        closedSet.add(state);
        addToOpenList(state);
 
        while (openList.size() != 0)
        {
            state = popOpenList();
            //System.out.println(state.toString());
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
                    addToOpenList(n);
                }
            }
        }
        return null;
	}

	

}
