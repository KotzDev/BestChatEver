package Exempel;

import java.io.*;
import java.net.*;

/**
 * Denna kod beskriver de trådar som hanterar
 * anslutningar till Exempel.MultiThreadedServerDemo
 * Varje tråd läser in sin klients meddelanden,
 * konverterar det till versaler och skickar
 * tillbaka det. Tråden avslutar när klienten
 * kopplar ner.
 */
public class EchoThread extends Thread{

    // Socket, lämnas via konstruktorn
    private Socket clientSocket = null;

    // Strömmar för att läsa/skriva till klienten
    private PrintWriter out;
    private BufferedReader in;

    // Meddelandet som konverteras och skickas tillbaka
    private String echo;

    // Konstruktorn sparar socketen lokalt
    public EchoThread(Socket sock){
	clientSocket = sock;
    }

    public void run(){

	// Vi kör tills vi är klara
	boolean done = false;

	// Anslut läs- och skrivströmmarna
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

	// Kommer vi hit gick anslutningen bra.
	// Vi skriver ut IP-nummret från klienten
	System.out.println("Connection Established: " 
			   + clientSocket.getInetAddress());

	// Här läser vi in klientens budskap
	// och konverterar det till versaler
	// Om klienten kopplar ner gör vi det också,
	// och avslutar tråden
	while(!done){
	    try{
		echo = in.readLine();
		if(echo==null){
		    System.out.println("old.Client disconnect!");
		    done = true;
		}else{
		    System.out.println("Recieved: (" 
                            + clientSocket.getInetAddress() 
                            + ") " + echo);
		    out.println(echo.toUpperCase());
		}
	    }catch(IOException e){
		System.out.println("readLine failed: " + e);
		System.exit(1);
	    }
	}

	try{
	    in.close();
	    out.close();
	    clientSocket.close();
	}catch(IOException e){}
    }
}