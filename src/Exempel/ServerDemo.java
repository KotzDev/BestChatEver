package Exempel;

import java.io.*;
import java.net.*;

/**
 * Detta program startar en server som lyssnar på port 4444
 * och konverterar alla inkommande meddelanden till versaler
 * innan den skickar tillbaks dem till klienten
 * Programmet bryts om klienten kopplar ifrån.
 */
public class ServerDemo{

    // Strömmar för att läsa/skriva
    private PrintWriter out;
    private BufferedReader in;

    // texten som läses in/skickas tillbaka
    private String echo;

    // Sockets till uppkopplingen
    private ServerSocket serverSocket;
    private Socket clientSocket = null;


    public static void main(String[] args){
	ServerDemo servDemo = new ServerDemo();
    }

    public ServerDemo(){

	// Koppla upp serverns socket
	try {
	    serverSocket = new ServerSocket(6868);
	} catch (IOException e) {
	    System.out.println("Could not listen on port: 4444");
	    System.exit(-1);
	}
	
	// Lyssna efter en klient
	try {
	    clientSocket = serverSocket.accept();
	} catch (IOException e) {
	    System.out.println("Accept failed: 4444");
	    System.exit(-1);
	}

	// Anslut till klienten
	try{
	    out = new PrintWriter(
				  clientSocket.getOutputStream(), true);
	}catch(IOException e){
	    System.out.println("getOutputStream failed: " + e);
	    System.exit(1);
	}

	try{
	    in = new BufferedReader(new InputStreamReader(
	            clientSocket.getInputStream()));
	}catch(IOException e){
	    System.out.println("getInputStream failed: " + e);
	    System.exit(1);
	}

	// Kommer vi hit har det gått bra
	// Vi skriver ut IP-adressen till klienten
	System.out.println("Connection Established: " 
			   + clientSocket.getInetAddress());

	// Läs från klienten och skicka tillbaka 
	// medelandet i versaler tills klienten
	// kopplar ner
	while(true){
	    try{
		echo = in.readLine();
		if(echo==null){
		    System.out.println("old.Client disconnect!");
		    System.exit(1);
		}
		System.out.println("Recieved: " + echo);
		//out.println(echo.toUpperCase());
	    }catch(IOException e){
		System.out.println("readLine failed: " + e);
		System.exit(1);
	    }
	}
    }
    
}