package serverInterfaces;

public interface CacheManager<T,S> {

	Solution<S> IsSolutionExists(Searchable<T,S> s);
	void SaveSolution(Searchable<T,S> searchable, Solution<S> sol);
}
