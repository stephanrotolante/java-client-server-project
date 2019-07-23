import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	private String username;
	private String IP_ADDRESS;
	private Socket socket = null; // Initializing the socket
	private DataInputStream inSys = null; // Initializing the DataInputStream
	private DataInputStream inSoc = null;
	private DataOutputStream out = null; //Initializing the Data Out
	MySocketReader reader = null;
	MySocketWriter writer = null;

	public Client(String address, int PORT, String name) {
		
		// This will try to create a connection to the socket
		try {
			username = name;
			socket = new Socket(address, PORT);
			System.out.println("You have connected.");
			reader = new MySocketReader(socket);
			writer = new MySocketWriter(socket,username);
			reader.start();
			writer.start();
			

			
		} catch (IOException i){
			System.out.println(i.getMessage());
		}
	}

	public static void main(String[] args) {
		String inputName, inputIP;
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter username: ");
		inputName = userInput.nextLine();
		System.out.println("Enter the IP Address to the server: ");
		inputIP = userInput.nextLine();
		Client client = new Client(inputIP, 5000,inputName);
	}
}