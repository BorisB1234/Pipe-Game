package server;

import java.util.List;

import serverInterfaces.Solution;

public class CommonSolution<S> implements Solution<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Action<S>> sol;
	
	public CommonSolution(List<Action<S>> sol) {
		this.sol=sol;
	}
	
	
	
	@Override
	public List<Action<S>> GetSolution() {
		return sol;
	}

	
	

}
