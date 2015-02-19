import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Exempel.ClientDemo;
import com.sun.corba.se.spi.activation.Server;

/**
 * Created by sydney.wojnach on 2015-02-19.
 */
public class GUI1 extends JPanel {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/
        private ClientServer cliServ;   // denna kommer användas senare när man avgör om det är server eller klient.
        //private ServerDemo servDemo;
        //private MultiThreadedServerDemo multiTDemo;
        private JCheckBoxMenuItem myCheckBoxes;
        private JButton myButton;
        private JButton ButtonClient;
    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI1()
    {
                //Nu ska vi bara rita upp allt.
        setPreferredSize(new Dimension(410,410));
        myButton = new JButton("Start/STop");
        add(myButton);
        //myButton.addActionListener(this);
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server Click");
                //Random rand = new Random();
                //int randomNum = rand.nextInt(5000);
                new ClientServer(5000).start();
            }
        });

        ButtonClient = new JButton("Client Connect");
        add(ButtonClient);
        ButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Random rand = new Random();
                //int randomNum = rand.nextInt(5000);
                new ClientDemo("169.254.123.39", 5000).start();
                System.out.println("Client Click");
            }
        });
       // ButtonClient.addActionListener(this);
        //multiTDemo = new MultiThreadedServerDemo();
        //servDemo = new ServerDemo();
        //cliServ = new ClientServer(4444);


    }//End of constructor

    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/


/*    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Server Click");
        //Random rand = new Random();
        //int randomNum = rand.nextInt(5000);
        new ClientServer(5000).start();

    }*/


    public void checkBoxes()
    {
        /**
         * Denna metod kommer användas senare för att avgöra om det handlar om SERVER eller KLIENT
         */
    }

}
