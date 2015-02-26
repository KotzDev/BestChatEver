import javax.swing.*;

/**
 * Created by sydney.wojnach on 2015-02-26.
 */
public class ChatWindow extends JFrame {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/

    private GUI2 myGUI2;


    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public ChatWindow(String name)
    {
        myGUI2 = new GUI2(name);
        add(myGUI2);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  // End of Constructor



}
