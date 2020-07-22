package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import serverInterfaces.CacheManager;
import serverInterfaces.ClientHandler;
import serverInterfaces.Searchable;
import serverInterfaces.Solution;
import serverInterfaces.Solver;


public class PipesGameClientHandler implements ClientHandler {

	private CacheManager<PipesState,PipesAction> cManager;
	private Solver<PipesState,PipesAction> solver;
	public PipesGameClientHandler() {
		
	}

	@Override
	public void HandleClient(InputStream in, OutputStream out) throws IOException  {
		//long time = System.currentTimeMillis();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		PrintWriter printWriter = new PrintWriter(out);
		cManager = new SimpleCacheManager<PipesState,PipesAction>();
		Searchable<PipesState,PipesAction> pipesProblem;
		solver = new MySolver<PipesState,PipesAction>();
		ArrayList<String> list = new ArrayList<String>();
		char state[][];
		int col;
		String line;
		
		
		while(!(line = bufferedReader.readLine()).equals("done"))
		{
			System.out.println(line);
			list.add(line);
		}
		if(list.isEmpty())
			throw new IOException("Empty buffer");
		col = getListStringSize(list);
		if(col == -1)
			throw new IOException("Not a matrix");
		state = new char[list.size()][col];
		for(int i = 0; i < list.size(); i++)
		{
			state[i] = list.get(i).toCharArray();
		}
		pipesProblem = new PipesBoardProblem(state);
		
		Solution<PipesAction> s = cManager.IsSolutionExists(pipesProblem);
		if (s == null)
		{
			s = solver.Solve(pipesProblem);
			
			if(s == null)
			{
				System.out.println("Not a solvable stage");
				printWriter.write("done");
				printWriter.flush();
				printWriter.close();
				bufferedReader.close();
				return;
			}
			cManager.SaveSolution(pipesProblem, s);
		}
		
		List<Action<PipesAction>> l = s.GetSolution();
		for(Action<PipesAction> a : l)
		{
			printWriter.write(a.getAction().getRow() + "," + a.getAction().getCol() + "," + a.getAction().getChange() + "\n");
		}
		System.out.println("done");
		printWriter.write("done");
		printWriter.flush();
		printWriter.close();
		bufferedReader.close();
		
	}
	private int getListStringSize(ArrayList<String> list) throws IOException
	{
		int size = list.get(0).length();
		for(String s: list)
		{
			
			if(size!=s.length())
			{
				return -1;
			}
		}
		return size;
	}

}
