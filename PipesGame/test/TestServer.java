package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer {

	public static void runClient(int port){
		
		
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			s=new Socket("127.0.0.1",port);
			s.setSoTimeout(3000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println("s||||g");

			
			out.println("done");
			out.flush();
			String line=in.readLine();
			while(!line.equals("done"))
			{
				System.out.println(line);
				line=in.readLine();
			}
		}catch(SocketTimeoutException e){
			System.out.println("Your Server takes over 3 seconds to answer (-50)");
		}catch(IOException e){
			System.out.println("Your Server ran into some IOException (-50)");
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				System.out.println("Your Server ran into some IOException (-50)");
			}
		}
	}
}
