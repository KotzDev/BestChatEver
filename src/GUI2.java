import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private String msg;
    private JLabel name;
    private final static String newline = "\n";

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
        sendField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msg = sendField.getText();
                myTextArea.append(msg + newline);

                //TODO: Nu ska den skicka till XMLlib
                //TODO: sen får vi tillbaka något från XMLLib som lagras
                System.out.println("Storing msg");
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            //TODO: skicka msg från sendfield till server.
                System.out.println("sending msg to server");
            }
        });
        add(sendField);
        add(colorPicker);
        add(sendButton);

    } // end of constructor


    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/



}
