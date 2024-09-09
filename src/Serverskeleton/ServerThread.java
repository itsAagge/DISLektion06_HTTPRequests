package Serverskeleton;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
	Socket connSocket;
	common c;
	
	public ServerThread(Socket connSocket,common c) {
		this.connSocket = connSocket;
		this.c=c; // Til Web-server opgaven skal denne ikke anvendes
	}
	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());
			
			// Do the work and the communication with the client here	
			// The following two lines are only an example
			
			String clientSentence = inFromClient.readLine();
			String[] message = clientSentence.split(" ");
			String thingToLoad = message[1];
			System.out.println(thingToLoad);

			outToClient.writeBytes("HTTP 200 OK\n");
			outToClient.writeBytes("Content-Type: " + c.contentType(thingToLoad) + "\n");
			outToClient.writeBytes("Connection: Close\n");
			outToClient.writeBytes("\n");
			outToClient.write(c.read("src/Serverskeleton/myWEB" + thingToLoad));
		
		} catch (IOException e) {
			e.printStackTrace();
		}		
		// do the work here
	}
}
