import javax.swing.*;
import java.awt.*;

/**
 * Created by sydney.wojnach on 2015-02-26.
 */
public class GUI2 extends JPanel{

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/

    private JButton sendButton;
    private JButton colorPicker;
    private JTextField sendField;
    private JTextArea myTextArea;
    private JScrollPane myScrollpane;
    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI2()
    {
        setPreferredSize(new Dimension(400,400));
        sendButton = new JButton("SEND");
        colorPicker = new JButton("Pick a color");
        myTextArea = new JTextArea();
        myTextArea.setText("AMMMAAGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD\nsds\n");
        myTextArea.setColumns(30);
        myTextArea.setRows(20);
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);
        myTextArea.setEditable(false);
        myScrollpane = new JScrollPane(myTextArea);
        add(myTextArea);
        //TODO: Lägg till actionlistern på knappen(Hämta från GUI1)
        //TODO: Starta sedan "logger" classen."
        sendField = new JTextField();
        sendField.setColumns(30);
        add(sendField);
        add(colorPicker);
        add(sendButton);

    } // end of constructor


    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/



}
