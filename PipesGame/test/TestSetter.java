package test;

import server.MySolver;
import server.PipesGameClientHandler;
import server.SimpleCacheManager;
import server.SimpleServer;
import serverInterfaces.CacheManager;
import serverInterfaces.ClientHandler;
import serverInterfaces.Server;
import serverInterfaces.Solver;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		dt.setServerClass(SimpleServer.class);
		dt.setClientHandlerInterface(ClientHandler.class);
		dt.setClientHandlerClass(PipesGameClientHandler.class);
		dt.setCacheManagerInterface(CacheManager.class);
		dt.setCacheManagerClass(SimpleCacheManager.class);
		dt.setSolverInterface(Solver.class);
		dt.setSolverClass(MySolver.class);
		//dt.setSearchableInterface();
		
		
		
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new SimpleServer(port);
		s.start(new PipesGameClientHandler());
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}

}
