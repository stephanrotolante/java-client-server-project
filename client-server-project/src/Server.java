import java.net.*;
import java.io.*;

public class Server {

	private Socket socket = null;
	private ServerSocket server = null;
	public Server(int PORT) {
		
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server started...");
			
			System.out.println("Waiting for client to connect...");
			while(true) {
				try {
					socket = server.accept();
					System.out.println("You have a new connection");
					MySocketReader reader = new MySocketReader(socket);
					reader.start();
				} catch(IOException i) {
					
				}
			}
			
		} catch (IOException i) {
			
		}
	}

	public static void main(String[] args) {
		Server server = new Server(5000);
		

	}

}