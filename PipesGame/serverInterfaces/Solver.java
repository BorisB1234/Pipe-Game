package serverInterfaces;

public interface Solver<T,S> {

	
	 public Solution<S> Solve(Searchable<T,S> s);
}
