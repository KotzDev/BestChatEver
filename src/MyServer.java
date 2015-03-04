import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This program sets up sockets and IOStreams which will be used in ChatWindow
 */
public class MyServer extends Thread
{
    // Sockets till uppkopplingen
    private ServerSocket serverSocket;
    private Socket clientSocket = null;
    // Strömmar för att läsa och skriva
    private ObjectOutputStream output;
    private ObjectInputStream input;
    // MISC
    private int port;
    private String name;
    // GUI STUFF
    private ChatWindow myChatWindow;

    /** This constructor creates a serversocket only */
    public MyServer(int port, String NAME)
    {
        this.name = NAME;
        this.port = port;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("ServerSocket initiated...");
        } catch (IOException e){
            System.out.println("Server could not setup port or it's currently in use: " + this.port);
            System.exit(-1);
        }
    }

    /** The run function below places the incomnig socket which is accepted by serversocket
     *  in clientsocket. We create IOStreams which are then used in the ChatWindow instansation
     */
    @Override
    public void run()
    {
        try {
            System.out.println("Server Awating incoming connections on: " + this.port);
            clientSocket = serverSocket.accept();
            System.out.println("Server Connection established: " + clientSocket.getInetAddress() + ":"  + this.port);
            output = new ObjectOutputStream(clientSocket.getOutputStream()); //used for sending stuff
            input =  new ObjectInputStream(clientSocket.getInputStream()); //used for reciving stuff
            System.out.println("\n Streams are setup - starting chatwindow. /dance \n ");
            myChatWindow = new ChatWindow(this.name,output,input);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server Accept failed on port: " + this.port);
            System.exit(-1);
        }
    }
}
