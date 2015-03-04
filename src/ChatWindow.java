import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sydney.wojnach on 2015-02-26.
 * This class only creates a JFrame which GUI2 is added to.
 * We also set some properties for the Frame.
 */
public class ChatWindow extends JFrame {

    private GUI2 myGUI2;
    private Thread nytrad;

    /** The ChatWindow constructor: Creates a GUI2 panel with preexisting variables. And sets some variables */
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
} // End of class
