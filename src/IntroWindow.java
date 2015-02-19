import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

/**
 * Created by sydney.wojnach on 2015-02-19.
 */
public class IntroWindow extends JFrame {

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/

    private GUI1 myGUI1;
    //private ClientServer cliServ;

    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public IntroWindow()
    {
        myGUI1 = new GUI1();
        add(myGUI1);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//End of constructor

    /**-----------------------------------------------------------------------------
     //*                             MAIN
     //-----------------------------------------------------------------------------*/

    public static void main(String[] args)
    {
        new IntroWindow();
    }//End of Main
}//End of class
