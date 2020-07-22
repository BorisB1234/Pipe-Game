package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverInterfaces.ClientHandler;
import serverInterfaces.Server;

public class SimpleServer implements Server {
	private PriorityQueue<ClientsProblem> pq =new PriorityQueue<>();
	private boolean stop;
	private ServerSocket serverSocket;
	private  ClientHandler ch;
	private int port;
	private int numThreads=4;
	private ExecutorService executor;
	public SimpleServer(int port) {
		stop = false;
		this.port=port;
	}

	@Override
	public void start(ClientHandler ch){ //throws UnknownHostException
		
		
		System.out.println("ACCEPTING..");
		
		this.ch = ch;
		
		executor = Executors.newFixedThreadPool(numThreads);  
		
	        
		new Thread(()->{
			try {
				runServer();
			} catch (IOException e) {
				if(e.getMessage().equals("Address already in use: JVM_Bind"))
				{
					System.out.println("Address or port is already in use");
				}
				else
				{
					System.out.println(e.getMessage());
				}
			}
		}).start();
		
	}
	

	public void runServer() throws IOException  {
		Socket aClient = null;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(1000);
		
		while (!stop) {
			try
			{
				aClient = serverSocket.accept();
				pq.add(new ClientsProblem(aClient));
				executor.submit(()->
				{
					ClientsProblem c = pq.poll();
					try
					{
						ch.HandleClient(c.sock.getInputStream(),c.sock.getOutputStream());
					}
					catch (IOException e1)
					{
					
						e1.printStackTrace();
					}
					finally {
						try {
							c.sock.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
			catch (IOException e2) 
			{
				
			}
			
		
		}
	
	}

	@Override
	public void stop() {
		stop=true;
		executor.shutdown();  
        while (!executor.isTerminated()) {
        	
        }  
  
        System.out.println("Closing");
		
	}

}
