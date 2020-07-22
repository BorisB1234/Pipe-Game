package serverInterfaces;

import server.State;

public interface Grader<T,S> {
	int grade(State<T,S> s);
}
