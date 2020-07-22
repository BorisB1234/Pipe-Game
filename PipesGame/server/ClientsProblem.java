package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientsProblem implements Comparable<ClientsProblem> {
	
	 public Socket sock;
	 public ClientsProblem(Socket s) {
		 this.sock=s;
	 }
	
	@Override
	public int compareTo(ClientsProblem o) {
		InputStream i, s;
		
		try {
			i=o.sock.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));
			String line;
			int count=0,count2=0;
			while(!(line = bufferedReader.readLine()).equals("done"))
			{
				count+=line.length();
			}
			s=this.sock.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(s));
			while(!(line = bufferedReader.readLine()).equals("done"))
			{
				count2+=line.length();
			}
			return count-count2;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
