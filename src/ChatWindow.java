import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sydney.wojnach on 2015-02-26.
 * This class only creates a JFrame which GUI2 is added to.
 * We also set some properties for the Frame.
 */
public class ChatWindow extends JFrame implements WindowListener {

    private GUI2 myGUI2;
    private Thread nytrad;


    /** The ChatWindow constructor: Creates a GUI2 panel with preexisting variables. And sets some variables */
    public ChatWindow(String name, final ObjectOutputStream output, final ObjectInputStream input)
    {
        myGUI2 = new GUI2(name, output, input);
        nytrad = new Thread(myGUI2);
        nytrad.start();
        add(myGUI2);
        pack();
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add WindowListener to disconnect when user quits
        addWindowListener (
                new WindowAdapter()
                {
                    // disconnect from server and exit application

                    public void windowClosing ( WindowEvent event )
                    {
                        System.out.println("DC");
                        myGUI2.sendMsg(xmlLib.makeDiscMsg());
                        try {
                            output.close();
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            //System.out.println("Bang bang");
                        }
                        //System.exit( 0 );
                    } // end method windowClosing
                }); // end anonymous inner class

    }  // End of Constructor


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        myGUI2.sendMsg(xmlLib.makeDiscMsg());
        myGUI2.sendMsg("jag drar peace");
        System.out.println("ja dc:ar");
        try {
            myGUI2.output.close();
            myGUI2.input.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

        myGUI2.sendMsg(xmlLib.makeDiscMsg());
        myGUI2.sendMsg("jag drar peace");
        System.out.println("ja dc:ar");
        try {
            myGUI2.output.close();
            myGUI2.input.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

        myGUI2.sendMsg(xmlLib.makeDiscMsg());
        myGUI2.sendMsg("jag drar peace");
        System.out.println("ja dc:ar");
        try {
            myGUI2.output.close();
            myGUI2.input.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

} // End of class