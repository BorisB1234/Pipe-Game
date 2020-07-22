package server;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String next;
		SimpleServer s = new SimpleServer(6400);
		s.start(new PipesGameClientHandler());
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext())
		{
			next = scanner.next();
			if(next.equals("stop"))
			{
				s.stop();
				break;
			}
		}
		scanner.close();

		
		
	}

}
