/*
 * Set connection for socket programming
 * manage connection and communicate btn server and client
 */

package model;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Connection{

Socket connection;

InputStream inStream;
DataInputStream inDataStream;
OutputStream outStream;
DataOutputStream outDataStream;

public String values[]= new String[4];


///establishes connection with server
  public void runClient ()  {

    String host;
      try  {

      try {
	        InetAddress here = InetAddress.getLocalHost ();
	        host = here.getHostName ();
	        // host = "140.158.130.26";
	        // get host IP address from User
	        host = JOptionPane.showInputDialog("Server Address ?", null);
	        System.out.println("Server: "+host);
	        connection = new Socket ( host, 8930);
       
        }
      catch (UnknownHostException e) {
        System.out.println( "Failed to create socket to server\n" );
        }

      inStream = connection.getInputStream ();
      inDataStream = new DataInputStream ( inStream );
      
      outStream = connection.getOutputStream ();
      outDataStream = new DataOutputStream ( outStream );

      }  // end try for connection
      
    catch ( IOException except ) {
        System.out.println ( "Network Exception");
        except.printStackTrace ();
      }  // end catch
    

    } // end runClient
  
  // receive data from server
  public String receive_from_Server( ) throws IOException{
	 return inDataStream.readUTF();
	  
  }
  
   // close connection
   public void close_connection() throws IOException{
		connection.close();
	}
   	
    // send msg to server
	public void send_to_Server(int id, int x, int y, int position) throws IOException {
		outDataStream.writeUTF ( id+":"+ x +":"+ y + ":"+position );
		
	}
	
	// pack data 
	public String marshalize(int id, int x, int y, int position){
		String a;
		a=Integer.toString(id)+":"+Integer.toString(x)+":"+Integer.toString(y)+":"+Integer.toString(position);
		return a;
	}
	
	// unpack data
	public void demarshalize(String message){
		StringTokenizer st = new StringTokenizer(message, ":"); 
		int i=0;
		while(st.hasMoreTokens()) { 
					values[i]=st.nextToken();
					// System.out.println(values[i]);
			i++;
				
			} 
	
}
    
}




