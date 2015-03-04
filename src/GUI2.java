import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private JLabel namelabel;
    private String name;
    private final static String newline = "\n";
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**-----------------------------------------------------------------------------
     //                           CONSTRUCTOR
     //-----------------------------------------------------------------------------*/

    public GUI2(String name, ObjectOutputStream o, ObjectInputStream i)
    {

        input = i;      //Input = the "input" from the constructor
        output = o;     //Output = the "Output" from the constructor

        setPreferredSize(new Dimension(400,400));
        sendButton = new JButton("SEND");
        colorPicker = new JButton("Pick a color");
        namelabel = new JLabel();
        namelabel.setText(name);
        myTextArea = new JTextArea();
        myTextArea.setText("AMMMAAGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD\nsds\n");
        myTextArea.setColumns(35);
        myTextArea.setRows(20);
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);
        myTextArea.setEditable(false);

        add(new JScrollPane(myTextArea));

        //TODO: Lägg till actionlistern på knappen(Hämta från GUI1)
        //TODO: Starta sedan "logger" classen."
        sendField = new JTextField();
        sendField.setColumns(30);
        sendField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msg = sendField.getText();
                sendField.setText("");

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
        add(namelabel);
        add(sendField);
        add(colorPicker);
        add(sendButton);

    } // end of constructor


    /**-----------------------------------------------------------------------------
     //                           METHODS
     //-----------------------------------------------------------------------------*/



        private void whileChatting()throws IOException
        {
            String message = "You are now connected";
            sendMsg(message);
            do
            {
                try
                {
                    message = (String) input.readObject();
                    sendMsg("\n" + message);
                }catch(ClassNotFoundException classNotFoundException)
                {
                    System.out.println("\n idk wtf that user sent!");
                }
            }while(!message.equals("END"));
        }

        private void sendMsg(String msg)
        {
            try
            {
                output.writeObject(name +" - " +msg);
                output.flush();
                //Showmessage("\n"+name+" - " + msg);
            }catch(IOException ioException)
            {
                System.out.println("can't send that message");
            }
        }
}
