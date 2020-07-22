package server;

import java.io.Serializable;

public class Action<S> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private S action;
	public Action(S action) {
		this.action=action;
	}
	public S getAction()
	{
		return action;
	}
	
	public String toString()
	{
		return action.toString();
	}
}
