import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sydney.wojnach on 2015-02-19.
 */
public class MyServer extends Thread
{

     /**----------------------------------------------------------------
     /*                         GLOBAL VARIABLES
     //-----------------------------------------------------------------*/

    // Sockets till uppkopplingen
    private ServerSocket serverSocket;
    private Socket clientSocket = null;
    // Strömmar för att läsa/skriva
    private BufferedReader in;
    private PrintWriter out;
    // Eller
    private ObjectOutputStream output;
    private ObjectInputStream input;
    // MISC
    private String echo;
    private int port = 5000;
    private String name;
    // GUI STUFF
    private ChatWindow myChatWindow;

    /**----------------------------------------------------------------
     /*                         CONSTRUCTORS
     //-----------------------------------------------------------------*/

    // Constructor for server [BETA]
    public MyServer(int port, String NAME)
    {
        name = NAME;
        //Koppla upp servern socket
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            System.out.println("ServerSocket initiated...");
        } catch (IOException e){
            System.out.println("Could not listen on port or currently in use: " + this.port);
            System.exit(-1);
        }
    }

    // Constructor for Client [ALPHA] - not being used ATM.
    public MyServer(String hostAddress, int port)
    {
        try {
            clientSocket = new Socket(hostAddress, 4444);
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
        // Kommer vi hit har anslutningen gått bra
        System.out.println("Connection successful!");
    }

    /**----------------------------------------------------------------
     /*                         METHODS
     //-----------------------------------------------------------------*/

    @Override
    public void run()
    {
        //Lyssna efter klient (när klienten ansluter sätter vi upp Clientsocket)
        try {
            System.out.println("Server Awating incoming connections on: " + this.port);
            clientSocket = serverSocket.accept();
            System.out.println("Server Connection established: " + clientSocket.getInetAddress() + ":"  + this.port);
            //out = new PrintWriter(clientSocket.getOutputStream(), true);
            //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new ObjectOutputStream(clientSocket.getOutputStream()); //Send stuff
            input =  new ObjectInputStream(clientSocket.getInputStream()); //receive stuff
            System.out.println("\n Streams are setup - going to starting chatwindow. \n ");

            myChatWindow = new ChatWindow(name,output,input);
            // IOSTREAMS har blivit flyttade till GUI2
            //ioStream();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server Accept failed: on port " + this.port);
            System.exit(-1);
        }
    }


    public void ioStream()
    {
        while(true)
        {
            try {
                System.out.println("while loop initiated in Server Class");
                echo = in.readLine();
                if (echo == null) {
                    System.out.println("Client has disconnected");
                    System.exit(1);
                }
                System.out.println("Received: " + echo);
                //out.println(echo.toUpperCase());

            } catch(IOException e){
                System.out.println("readLine failed" + e);
                System.exit(1);
            }
        }
    }

    // Wait for connection, then

    private void waitForConnection() throws IOException
    {
        System.out.println("waiting for someone else to connect ... \n");
        clientSocket = serverSocket.accept();
        System.out.println("connected");
    }

    // get stream to send and receive data
    private void setupStreams() throws IOException
    {
        // Send things out
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        output.flush();
        // receive stuff
        input =  new ObjectInputStream(clientSocket.getInputStream());
        System.out.println("\n The streams are not setup \n ");
    }

    //during the chat conversation
    private void whileChatting() throws IOException
    {
        String message = ("You are now connected");
        //TODO: create sendMsg();
        do
        {
            try{
                message = (String) input.readObject();
                System.out.println("\n " + message);
            }catch(ClassNotFoundException classNotFoundException)
            {
                System.out.println("\n idk wtf that user sent!");
            }
        }while(!message.equals("CLIENT - END"));

    }

    // send message to client
    private void sendMsg(String msg)
    {
        try{
            output.writeObject("NAME - " + msg);
            output.flush();
            //showmessage("\nServer - " + msg);
        }catch(IOException ioException)
        {
            System.out.println("can't send that msg");
        }
    }

}
