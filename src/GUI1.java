import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Exempel.MyClient;
import com.sun.corba.se.spi.activation.Server;

/**
 * Created by sydney.wojnach on 2015-02-19.
 */
public class GUI1 extends JPanel {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/
        private Server cliServ;
        //private JCheckBoxMenuItem myCheckBoxes;
        private JButton myButton;
        private JButton ButtonClient;
        private JTextField portField;
        private JLabel portlabel;
    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI1()
    {
        //TODO: Måste lägga till Jtxtfields för port, ip, osv.
        setPreferredSize(new Dimension(200,400));

        myButton = new JButton("START SERVER");
        portField = new JTextField(5);
        portlabel = new JLabel();
        portlabel.setText("Ange server port");
        add(portlabel);
        add(portField);
        add(myButton);
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server Click");
                new MyServer(5000).start();
            }
        });

        ButtonClient = new JButton("START CLIENT");
        add(ButtonClient);
        ButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        new MyClient("169.254.123.59", 5000).start();
        System.out.println("Client Click");
            }
        });

    }//End of constructor

    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/


    public void checkBoxes()
    {

        //TODO: Det ska vara checkboxes istället för knappar.
    }

}
