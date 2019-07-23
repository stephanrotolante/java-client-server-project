import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class MySocketReader extends Thread {
	
	private DataInputStream in; // Initializing the DataInputStream
	private Socket socketRef;
	public MySocketReader(Socket socket) {
		try {
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			socketRef = socket;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		String line = "";		
        // reads message from socket until "Over" is recieved 
        while (!line.equals("Over")) 
        { 
            try
            { 
            		line = in.readUTF();
				System.out.println(line);
            } 
            catch(IOException i) 
            { 
	            	try {
	            		// close connection 
	            		line="Over";
	            		System.out.println("Closing Socket...");
		    			socketRef.close();
		    			in.close();
	            	} catch (IOException e) {
	            	} 
            } 
        }
        try {
        		System.out.println("Closing socket...");
			in.close();
			socketRef.close();
		} catch (IOException i) {
			try {
				socketRef.close();
				in.close();
			} catch (IOException e1) {
			}
		}
	}
}
