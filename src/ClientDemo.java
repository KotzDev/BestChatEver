package Exempel;

import java.io.*;
import java.net.*;

/**
 * Detta program skapar en klient som ansluter till en
 * fördefinierad server. Programmet läser in text från
 * terminalen och skickar till servern, för att sedan 
 * skriva ut serverns svar. Programmet fortsätter tills
 * de avbryts utifrån.
 */
public class ClientDemo extends Thread {

	private int port;
	private String HostIP;
	Socket clientSocket = null;

	PrintWriter out = null;
	BufferedReader in = null;

	BufferedReader stdIn;
	String userInput;

	public ClientDemo(String HostIP, int Port) {
		try {
			this.HostIP = HostIP;
			this.port = Port;


			clientSocket = new Socket(HostIP, this.port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			System.out.println("Client connected to " + this.HostIP + " on port " + this.port);
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
/*	// Socket som ansluter till servern
        Socket clientSocket = null;

	// Strömmar för att läsa från/skriva till servern
        PrintWriter out = null;
        BufferedReader in = null;

	// Ström för att läsa från terminalen
	BufferedReader stdIn;
	String userInput;

	// Ändra nedanstående rad till lämplig serveradress
	String hostAddress = "169.254.123.39";*/

/*	// Anslut till server:
        try {
            clientSocket = new Socket(hostAddress, 6868);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.\n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to host.\n" + e);
            System.exit(1);
        }

	// Kommer vi hit har anslutningen gått bra*/
			//System.out.println("Connection successful!");

			// Anslut stdIn till terminalen
			stdIn = new BufferedReader(new InputStreamReader(System.in));

			// Läs in från terminalen och skicka till servern:
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo: " + in.toString());
			}
			System.out.println("Client out of while");


			// Hit kommer vi troligtvis aldrig,
			// men så här stänger man alla inblandade strömmar
			out.close();
			in.close();
			stdIn.close();
			clientSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}