package server;

import serverInterfaces.Grader;

public class PipesGrader implements Grader<PipesState, PipesAction> {

	@Override
	public int grade(State<PipesState, PipesAction> s) {
		PipesState p = s.getState();
		int[] startPos = new int[2];
		int[] endPos = new int[2];
		int[] highestFlowPos = new int[2];
		int highestFlow;
		
		p.getPos(startPos, 's');
		highestFlow = p.getHighestFlowPos(highestFlowPos, startPos[0], startPos[1], true, null, true);
		p.getPos(endPos,'g');
		return highestFlow * 2 - (Math.abs(highestFlowPos[0] - endPos[0]) +  Math.abs(highestFlowPos[1] - endPos[1]));
		
	}
	
}
