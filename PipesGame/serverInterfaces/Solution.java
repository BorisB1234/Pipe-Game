package serverInterfaces;

import java.io.Serializable;
import java.util.List;

import server.Action;

public interface Solution<S> extends Serializable {
	List<Action<S>> GetSolution();
}
