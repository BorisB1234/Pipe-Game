package serverInterfaces;

public interface Searcher<T,S> {
	public Solution<S> Search(Searchable<T,S> s);
}
