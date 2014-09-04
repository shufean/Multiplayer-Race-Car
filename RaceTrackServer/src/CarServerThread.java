/*
 * Threads for running the server
 */

import java.net.*;
import java.io.*;

public class CarServerThread extends Thread {
    private Socket socket = null;
    private int              ID        = -1;
    private Server      callback    = null;
    OutputStream outStream;
	DataOutputStream outDataStream;
	InputStream inStream;
	DataInputStream inDataStream;
	
//passes the socket and the server object to the thread
    public CarServerThread(Server _server,Socket socket) {
    	super("CarServerThread");
    	this.socket = socket;
    	this.callback = _server;
    	 this.ID     = socket.getPort();
    	
    }
    //sends the message
    public void send(String msg)
    {   try
        {  outDataStream.writeUTF(msg);
        outDataStream.flush();
        }
        catch(IOException ioe)
        {  
        	//System.out.println(ID + " ERROR sending: " + ioe.getMessage());
          
        }
    }
   // gets the id of the client
    public int getID()
    {  return ID;
    }
    
    //runs thread to receive and send the message
    public void run() {

	try {
		outStream = socket.getOutputStream ();
		outDataStream = new DataOutputStream ( outStream );
		inStream = socket.getInputStream ();
		inDataStream = new DataInputStream ( inStream );

		
	    String inputLine, outputLine;
	    
	    while ((inputLine=inDataStream.readUTF())!=null) {
		//System.out.println(inputLine);
		 callback.handle(ID, inputLine); 
	    }
		
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}


