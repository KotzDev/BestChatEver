import java.io.*;
import java.net.*;

/**
 * Detta program skapar en klient som ansluter till en
 * fördefinierad server. Programmet läser in text från
 * terminalen och skickar till servern, för att sedan 
 * skriva ut serverns svar. Programmet fortsätter tills
 * de avbryts utifrån.
 */
public class MyClient extends Thread {


	/**-----------------------------------------------------------------------------
	 /*                           GLOBAL VARIABLES
	 //-----------------------------------------------------------------------------*/


	private int port;
	private String HostIP;
	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	BufferedReader stdIn;
	String userInput;
	private ChatWindow myChatWindow;


	/**-----------------------------------------------------------------------------
	 //                           CONSTRUCTOR
	 //-----------------------------------------------------------------------------*/


	public MyClient(String HostIP, int Port, String NAME) {
		try {
			this.HostIP = HostIP;
			this.port = Port;

			clientSocket = new Socket(HostIP, this.port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			System.out.println("Client connected to " + this.HostIP + " on port " + this.port);
			myChatWindow = new ChatWindow(NAME);

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	/**-----------------------------------------------------------------------------
	 //                           METHODS
	 //-----------------------------------------------------------------------------*/


	@Override
	public void run() {
		try {


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