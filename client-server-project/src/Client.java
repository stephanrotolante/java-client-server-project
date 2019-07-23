import java.net.*;
import java.io.*;

public class Client {
	
	
	private Socket socket = null; // Initializing the socket
	private DataInputStream inSys = null; // Initializing the DataInputStream
	private DataInputStream inSoc = null;
	private DataOutputStream out = null; //Initializing the Data Out
	MySocketReader reader = null;
	MySocketWriter writer = null;

	public Client(String address, int PORT) {
		
		// This will try to create a connection to the socket
		try {
			socket = new Socket(address, PORT);
			System.out.println("You have connected.");
			reader = new MySocketReader(socket);
			writer = new MySocketWriter(socket);
			reader.start();
			writer.start();
			

			
		} catch (IOException i){
			System.out.println(i.getMessage());
		}
	}

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 5000);
	}
}