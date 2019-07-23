import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MySocketWriter extends Thread {
	
	private DataInputStream in; // Initializing the DataInputStream
	private DataOutputStream out; //Initializing the Data Out
	private Socket socketRef;
	private String username = "";
	
	
	public MySocketWriter(Socket socket) {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(System.in);
			socketRef = socket;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public MySocketWriter(Socket socket, String name) {
		try {
			username = name;
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(System.in);
			socketRef = socket;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		String line = "";		
        // reads message from self until "Over" is sent 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = in.readLine();
                out.writeUTF(username+": " +line);
            } 
            catch(IOException i) 
            { 
	            	try {
	            		// close connection 
	            		line="Over";
	            		System.out.println("Closing Socket...");
		    			socketRef.close();
		    			in.close();
		    			out.close();
	            	} catch (IOException e) {
	            	} 
            } 
        }
        
     
        try {
        		// close connection 
        		System.out.println("Closing Socket...");
			socketRef.close();
			in.close();
			out.close();
		} catch (IOException e) {
			try {
				socketRef.close();
				in.close();
				out.close();
			} catch (IOException e1) {
			}
			
		} 
	}
}