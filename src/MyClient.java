
import java.io.*;
import java.net.*;

/**
 * Detta program skapar en klient som ansluter till en
 * fördefinierad server. Den sätter upp sockets och IOStreams
 * som sedan anges i ChatWindow konstruktorn
 */
public class MyClient extends Thread {

	private int port;
	private String name;
	private String HostIP;
	private Socket clientSocket = null;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ChatWindow myChatWindow;

	/** Constructor which sets IP,port and name - which are later used in the ChatWindow constructor */
	public MyClient(String HostIP, int Port, String NAME)
	{
			this.HostIP = HostIP;
			this.port = Port;
			this.name = NAME;
	}

	@Override
	public void run()
	{
		try {
			clientSocket = new Socket(HostIP, this.port);
			output = new ObjectOutputStream(clientSocket.getOutputStream()); //Stream for Sending stuff
			input =  new ObjectInputStream(clientSocket.getInputStream()); //Stream for receiving stuff
			System.out.println("Client connected to " + this.HostIP + " on port " + this.port);
			myChatWindow = new ChatWindow(this.name, output, input);

		} catch (Exception E) {
			E.printStackTrace();
			System.out.println("Something went wrong in the run method located in MyClient");
		}
	}
}