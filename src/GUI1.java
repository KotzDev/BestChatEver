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
 * First screen thingy
 */
public class GUI1 extends JPanel {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/
        private Server cliServ;
        //private JCheckBoxMenuItem myCheckBoxes;
        private JButton myServerButton;
        private JButton myClientButton;

        private JTextField clientPortField;
        private JTextField serverPortField;
        private JTextField serverNameField;
        private JTextField clientNameField;
        private JTextField hostIPField;

        private JLabel serverNameLabel;
        private JLabel clientNameLabel;
        private JLabel serverPortLabel;
        private JLabel clientPortLabel;
        private JLabel hostIPLabel;

        private String serverPort;
        private String clientPort;
        private String serverName;
        private String clientName;
        private String hostIP;

        private JTextArea console;
        private JScrollPane consoleScrollPane;

    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI1()
    {
        setPreferredSize(new Dimension(140,400));

        myServerButton = new JButton("START SERVER");
        myServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting Server in GUI1");
                new MyServer(Integer.parseInt(serverPort), serverName).start();
            }
        });
        myClientButton = new JButton("START CLIENT ");
        myClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyClient("130.229.182.247", Integer.parseInt(clientPort), clientName).start();
                System.out.println("Starting Client in GUI1");
            }
        });

        serverPortField = new JTextField(8);
        serverPortField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverPort = serverPortField.getText();
            }
        });
        clientPortField = new JTextField(8);
        clientPortField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientPort = clientPortField.getText();
            }
        });
        serverNameField = new JTextField(8);
        serverNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverName = serverNameField.getText();
            }
        });
        clientNameField = new JTextField(8);
        clientNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientName = clientNameField.getText();
            }
        });


        serverPortLabel = new JLabel();
        serverPortLabel.setText("Incoming Port");
        clientPortLabel = new JLabel();
        clientPortLabel.setText("Outgoing Port");
        serverNameLabel = new JLabel();
        serverNameLabel.setText("Server Name");
        clientNameLabel = new JLabel();
        clientNameLabel.setText("Client Name");
/*
        console = new JTextArea();
        console.setColumns(10);
        console.setRows(10);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setEditable(false);
        consoleScrollPane = new JScrollPane(console);

        try {
            console.read(new InputStreamReader(System.in), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        add(myServerButton);
        add(serverNameLabel);
        add(serverNameField);
        add(serverPortLabel);
        add(serverPortField);

        add(myClientButton);
        add(clientNameLabel);
        add(clientNameField);
        add(clientPortLabel);
        add(clientPortField);

        //add(console);


    }//End of constructor

    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/


    public void checkBoxes()
    {

        //TODO: Det ska vara checkboxes istället för knappar.
    }

}
