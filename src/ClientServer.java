import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sydney.wojnach on 2015-02-19.
 */
public class ClientServer extends Thread
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
    private String echo; // Printar ut det som vi fångar upp
    private int port;

    /**----------------------------------------------------------------
     /*                         CONSTRUCTORS
     //-----------------------------------------------------------------*/

    // Konstrutkor för old.Server
    public ClientServer(int port){
        //Koppla upp servern socket
        try{
            this.port = port;
            serverSocket = new ServerSocket(this.port);
            System.out.println("A new server up listining on: " + this.port);
        } catch (Exception e){
            System.out.println("Could not listen on port or in use: " + this.port);
            //System.exit(-1);
        }
        System.out.println("ServerSocket initiated...");
        System.out.println("Listening for incoming transmissions...\n");
    }

    @Override
    public void run()
    {
       //Lyssna efter klient (när klienten ansluter sätter vi upp Clientsocket)
        try {
            System.out.println("Server Awating incoming connections on: " + this.port);
            clientSocket = serverSocket.accept();
            //clientSocket = serverSocket.accept();
            // Vi Skriver ut ip-adressen till som är ansluten klienten
            System.out.println("Server Connection established: " + clientSocket.getInetAddress());

            Socket so = null;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            //out = new PrintWriter(so.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ioStream();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server Accept failed: on port " + this.port);
            System.exit(-1);
        }
        //Ansluter till klienten genom att sätta up IOStream
    }

    //Konstruktor för old.Client
    public ClientServer(String hostAddress, int port)
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

    public void ioStream()
    {
        while(true)
        {
            //System.out.println("While loop initiated until client DC's");
            try {
                System.out.println("while loop initiated");
                echo = in.readLine();
                if (echo == null) {
                    System.out.println("Client has disconnected");
                    System.exit(1);
                }
                System.out.println("Received: " + echo);
                //out.flush();
                //out.println(echo.toUpperCase());

            } catch(IOException e){
                System.out.println("readLine failed" + e);
                System.exit(1);
            }
        }
    }


/*

    */
/**----------------------------------------------------------------
     */
/*                         MAIN
     //-----------------------------------------------------------------*//*


    public static void main(String[] args) throws IOException {
        ClientServer soul = new ClientServer(6868);
        soul.ioStream();
    }

*/

}
