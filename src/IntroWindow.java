import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

/**
 * Create a JFrame for the GUI1 panel.
 */
public class IntroWindow extends JFrame {

    private GUI1 myGUI1;

    /** Constructor that instantiates and adds the myGUI1 panel to the IntroWindow JFrame*/
    public IntroWindow()
    {
        myGUI1 = new GUI1();
        add(myGUI1);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//End of constructor

    /** The main method which starts the ChatClient*/
    public static void main(String[] args)
    {
        new IntroWindow();
    }//End of Main
}//End of class