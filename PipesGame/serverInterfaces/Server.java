package serverInterfaces;

import java.io.IOException;

public interface Server {

	public void start(ClientHandler ch);
	public void stop();
	public void runServer()throws IOException;
}
