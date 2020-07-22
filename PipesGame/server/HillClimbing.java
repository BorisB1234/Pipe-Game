package server;

import java.util.List;
import java.util.Random;

import serverInterfaces.Grader;
import serverInterfaces.Searchable;
import serverInterfaces.Solution;

public class HillClimbing<T, S> extends CommonSearcher<T, S> {
	long timeToRun=10000;
	Grader<T,S> m_grader;
	
	public HillClimbing(Grader<T,S> g) {
		m_grader = g;
	}
	
	
	@Override
	public Solution<S> Search(Searchable<T, S> s) {
		int grade = 0;
		State<T,S> next=s.getInitialState();
		State<T,S> maxState = null;
		long time0=System.currentTimeMillis();
		while(System.currentTimeMillis()-time0<timeToRun)
		{
			List<State<T,S>> neighbors=s.getAllPossibleStates(next);
			if(Math.random()<0.7)
			{
				if(neighbors.size() > 0)
				{
					grade = m_grader.grade(neighbors.get(0));
					maxState = neighbors.get(0);
				}
				for(State<T,S> temp : neighbors )
				{
					
					int g=m_grader.grade(temp);
					if(g > grade)
					{
						grade = g;
						maxState = temp;
					}
				}
				//System.out.println(maxState);
				maxState.setCameFrom(next);
				evaluatedNodes++;
				next=maxState;
				if(s.isGoalState(next))
					return backTrace(next, s.getInitialState());
				
			}
			else {
				evaluatedNodes++;
				State<T,S> temp=neighbors.get(new Random().nextInt(neighbors.size()));
				temp.setCameFrom(next);
				next=temp;
				if(s.isGoalState(next))
					return backTrace(next, s.getInitialState());
			}
		}
		return null;
	}
	
}

