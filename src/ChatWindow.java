import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sydney.wojnach on 2015-02-26.
 */
public class ChatWindow extends JFrame {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/

    private GUI2 myGUI2;
    private Thread nytrad;

    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public ChatWindow(String name, ObjectOutputStream output, ObjectInputStream input)
    {

        myGUI2 = new GUI2(name, output, input);
        nytrad = new Thread(myGUI2);
        nytrad.start();
        add(myGUI2);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  // End of Constructor



}
