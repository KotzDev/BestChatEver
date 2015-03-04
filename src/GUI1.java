import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import com.sun.corba.se.spi.activation.Server;

/**
 * The first panel which includes the functionality to instantiate
 * the server or client. You also get to set custom options.
 */
public class GUI1 extends JPanel {


        // The buttons
        private JButton myServerButton;
        private JButton myClientButton;
        // The txtfields
        private JTextField clientPortField;
        private JTextField serverPortField;
        private JTextField serverNameField;
        private JTextField clientNameField;
        private JTextField hostIPField;
        // The labels
        private JLabel serverNameLabel;
        private JLabel clientNameLabel;
        private JLabel serverPortLabel;
        private JLabel clientPortLabel;
        private JLabel hostIPLabel;
        // The Strings
        private String serverPort;
        private String clientPort;
        private String serverName;
        private String clientName;
        private String hostIP;

    /** Constructor for GUI1 JPanel which draws/adds all the functionality on the screen*/
    public GUI1()
    {
        setPreferredSize(new Dimension(140,300));   // Setting prefered size for the panel

        // THE SERVER BUTTON
        myServerButton = new JButton("START SERVER");
        myServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo();
                System.out.println("Starting Server in GUI1, username: " + serverName);
                new MyServer(Integer.parseInt(serverPort), serverName).start();
            }
        });

        // THE CLIENT BUTTON
        myClientButton = new JButton("START CLIENT ");
        myClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo();
                System.out.println("Starting Client in GUI1, username: " + clientName);
                new MyClient(hostIP, Integer.parseInt(clientPort), clientName).start();
            }
        });

        // SETTING FIELDS SIZE
        serverPortField = new JTextField(8);
        clientPortField = new JTextField(8);
        serverNameField = new JTextField(8);
        clientNameField = new JTextField(8);
        hostIPField = new JTextField(8);

        // SETTING THE LABELS
        serverPortLabel = new JLabel();
        serverPortLabel.setText("Incoming Port");
        clientPortLabel = new JLabel();
        clientPortLabel.setText("Outgoing Port");
        serverNameLabel = new JLabel();
        serverNameLabel.setText("Server Name");
        clientNameLabel = new JLabel();
        hostIPLabel = new JLabel("Enter IP");

        // HARDCODING FOR TROUBLESHOOTING TODO: Remove this once we go production.
        clientNameLabel.setText("Client Name");
        clientNameLabel.setText("Client Name");
        serverNameField.setText("serverlol");
        serverPortField.setText("5000");
        clientNameField.setText("clientlol");
        clientPortField.setText("5000");
        hostIPField.setText("169.254.123.249");

        // ADDING THE COMPONENTS TO THE PANEL
        add(myServerButton);
        add(serverNameLabel);
        add(serverNameField);
        add(serverPortLabel);
        add(serverPortField);
        //------------------
        add(myClientButton);
        add(clientNameLabel);
        add(clientNameField);
        add(clientPortLabel);
        add(clientPortField);
        //------------------
        add(hostIPLabel);
        add(hostIPField);
        //------------------


    }//End of constructor

    /**
     *  This method reads from the FIELDS and sets the corresponding STIRNGS.
     *  This method will probably be called by actionlisteners which are placed on the buttons
     */
    private void getInfo(){
        serverPort = serverPortField.getText();
        clientPort = clientPortField.getText();
        serverName = serverNameField.getText();
        clientName = clientNameField.getText();
        hostIP = hostIPField.getText();
    }
}
