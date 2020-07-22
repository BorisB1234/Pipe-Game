package serverInterfaces;
import java.io.Serializable;
import java.util.ArrayList;

import server.State;


public interface Searchable<T,S> extends Serializable {
	State<T,S> getInitialState();
	boolean isGoalState(State<T,S> state);
	ArrayList<State<T,S>> getAllPossibleStates(State<T,S> s);
	
	
}
