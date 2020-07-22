package server;

import java.io.Serializable;

public class State<T,S> implements Comparable<State<T,S>>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T state;
	private Action<S> action;
	private State<T,S> cameFrom;
	private int cost;
	public State(T state) {
		this.state=state;
		action = null;
		cost = 0;
	}
	public T getState()
	{
		return state;
	}
	public void setAction(Action<S> a) {
		action=a;
		
	}
	
	public Action<S> getAction()
	{
		return action;
	}
	@Override
	public int compareTo(State<T, S> o) {
		return this.cost - o.cost;
	}
	public void setCameFrom(State<T, S> n) {
		cameFrom=n;
		
	}
	public State<T, S> getCameFrom() {
		return cameFrom;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public int hashCode()
	{
		
		return state.hashCode();
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null) {
	        return false;
	    }
		try
		{
			State<T,S> o1 = (State<T,S>)o;
			return state.equals(o1.getState());
		}
		catch(ClassCastException ex)
		{
			return false;
		}
		
	}
	
	@Override
	public String toString()
	{
		if(action!=null)
			return "State :\n" + state.toString() + "\nAction : " + action.toString() + "\nPrice : " + cost;
		return  "State :\n" + state.toString() + "\nAction : " + "null" + "\nPrice : " + cost;
	}	
	
	

}
