/*
 * Main class for Server code
 */

import java.net.*;
import java.io.*;

public class Server{

	ServerSocket listenSocket;
	Socket connection;
	private CarServerThread clients[] = new CarServerThread[50];//array to keep clients
	private int counter;
	OutputStream outStream;
	DataOutputStream outDataStream;
	InputStream inStream;
	DataInputStream inDataStream;
	String message;
	
	boolean listening = true;

	//checks the client
	 private int findClient(int ID)
	   {  for (int i = 0; i < counter; i++)
	         if (clients[i].getID() == ID)
	            return i;
	      return -1;
	   }
	 
	 //checks the client and forwards the message other than sender
	public synchronized void handle(int ID, String input)
	   {  
			int index = findClient(ID);
			if (input.equals(".bye"))
		      { 
				clients[index].send(".bye");
		      
		      }
	        // remove(ID); }
	      else
	         for (int i = 0; i < counter; i++)
	         {
	        	 if(index != i)
	            clients[i].send(input);  
	         }
	   }
	
	//creates socket connection at the specified port
	public void runServer ( )  {
		try  {
			listenSocket = new ServerSocket ( 8930);
           	
			      try {
			        InetAddress here = InetAddress.getLocalHost ();
			        String host = here.getHostName ();
			         System.out.println( "on "+host+", port 8930\n" );
			         System.out.println( "Server Started" );
			        }
			      catch (UnknownHostException e) {
			    	  //System.out.println ( "Problem with local host\n" );
			        }

			}  // end try

			catch ( IOException except)  {
					except.printStackTrace ();
			}  // end catch
		
		
        while (listening)
        {
			try {
        		 clients[counter ] = new CarServerThread(this,listenSocket.accept());
        		 clients[counter ].start();//runs thread
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        	counter++;
        }

        try {
			listenSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  }  // end runServer

// **************

	public static void main ( String [ ] args )  {

		Server server = new Server ();

		server.runServer ();

	}  // end main

} 



