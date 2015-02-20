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
        private ClientServer cliServ;
        //private JCheckBoxMenuItem myCheckBoxes;
        private JButton myButton;
        private JButton ButtonClient;
    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI1()
    {
        setPreferredSize(new Dimension(410,410));

        myButton = new JButton("START SERVER");
        add(myButton);
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        System.out.println("Server Click");
        new ClientServer(5000).start();
            }
        });

        ButtonClient = new JButton("START CLIENT");
        add(ButtonClient);
        ButtonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        new ClientDemo("169.254.123.92", 5000).start();
        System.out.println("Client Click");
            }
        });

    }//End of constructor

    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/


    public void checkBoxes()
    {
        /**
         * Vi lägger till detta senare..
         */
    }

}
