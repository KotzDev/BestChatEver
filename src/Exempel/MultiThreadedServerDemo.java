package Exempel;

import java.io.*;
import java.net.*;

/**
 * Detta program startar en server som lyssnar på port 4444
 * och konverterar alla inkommande meddelanden till versaler
 * innan den skickar tillbaks dem till klienten
 * 
 * En ny tråd startas för varje anslutande klient
 * och programmet körs till det stängs av utifrån
 */
public class MultiThreadedServerDemo{

    // Serverns socket
    private ServerSocket serverSocket;

    public static void main(String[] args){
	MultiThreadedServerDemo servDemo = new MultiThreadedServerDemo();
    }

    public MultiThreadedServerDemo(){

	// Starta serverns socket
	try {
	    serverSocket = new ServerSocket(4444);
	} catch (IOException e) {
	    System.out.println("Could not listen on port: 4444");
	    System.exit(-1);
	}
	
	// Lyssna efter klienter.
        // Varje gång en klient ansluter atartas en
	// ny tråd av typen 'Exempel.EchoThread', som sedan
	// behandlar resten av kommunikationen
	// Ekotrådarna tar klientsocketen som argument
	// för att veta vem som har anslutit sig
	while(true){
	    Socket clientSocket = null;
	    try {
		clientSocket = serverSocket.accept();
	    } catch (IOException e) {
		System.out.println("Accept failed: 4444");
		System.exit(-1);
	    }
	    Thread thr = new EchoThread(clientSocket);
	    thr.start();
	}
    }
    
}