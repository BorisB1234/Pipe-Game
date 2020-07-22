package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import serverInterfaces.CacheManager;
import serverInterfaces.Searchable;
import serverInterfaces.Solution;

public class SimpleCacheManager<T,S> implements CacheManager<T,S> {

	
	public SimpleCacheManager() {
	
		
	}

	@Override
	public Solution<S> IsSolutionExists(Searchable<T,S> searchable) {
		boolean exist = new File(searchable.hashCode() + ".ser").isFile();
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		if(exist)
		{
			try
			{
				fileIn = new FileInputStream(searchable.hashCode() + ".ser");
				in = new ObjectInputStream(fileIn);
				Solution<S> s = (Solution<S>)in.readObject();
				
		        return s;
			}
			catch (IOException e) {
				//System.out.println(e.getMessage());
				return null;
			}
			catch (ClassNotFoundException e)
			{
				//System.out.println(e.getMessage());
				return null;
			}
			finally
			{
				try {
					in.close();
					fileIn.close();
				} catch (IOException e) {
					
				}
			}
		}
		else
			return null;
	}

	@Override
	public void SaveSolution(Searchable<T, S> s, Solution<S> sol) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			
			fileOut = new FileOutputStream(s.hashCode() + ".ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(sol);
		}
		catch (IOException e) {
			//System.out.println(e.getMessage());
		}
		finally {
			try {
				out.close();
				fileOut.close();
			} catch (IOException e) {
				
			}
		}
	}
}
