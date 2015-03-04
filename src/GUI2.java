import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The actual chat window
 */
public class GUI2 extends JPanel implements Runnable{

    /**-----------------------------------------------------------------------------
     /*                           GLOBAL VARIABLES
     //-----------------------------------------------------------------------------*/

    private JButton sendButton;
    private JButton colorPicker;
    private JTextField sendField;
    private JScrollPane myScrollpane;
    private String msg;
    private JLabel namelabel;
    private String name;
    private final static String newline = "\n";
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String chatColor = "#000000";
    private JEditorPane chatLog;
    private String chatLogText = "<p>CHAT TAEM IS NAO<p>";


    /**Constructor*/
    public GUI2(String name, ObjectOutputStream o, ObjectInputStream i)
    {

        input = i;      //Input = the "input" from the constructor (//receive stuff)
        output = o;     //Output = the "Output" from the constructor (//Send stuff)

        setPreferredSize(new Dimension(450,450));
        sendButton = new JButton("SEND");
        colorPicker = new JButton("Pick a color");
        namelabel = new JLabel(name);
        this.name = name;
        chatLog = new JEditorPane();
        chatLog.setPreferredSize(new Dimension(350, 350));
        chatLog.setContentType("text/html");
        chatLog.setAutoscrolls(true);
        add(new JScrollPane(chatLog));
        chatLog.setText(chatLogText);

        sendField = new JTextField();
        sendField.setColumns(30);
        sendField.addActionListener(new ActionListener() {  //enter
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg(sendField.getText());
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //click
//                sendMsg(sendField.getText()); //actual
                //test only
                recieveMsg(xmlLib.createXML(sendField.getText(),"testzor",chatColor));
            }
        });
        colorPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatColor = xmlLib.color2HexString(JColorChooser.showDialog(null,"Pick a color", null));
            }
        });
        add(namelabel);
        add(sendField);
        add(colorPicker);
        add(sendButton);
    }
    private void whileChatting()throws IOException {
        String message = "You are now connected";
        sendMsg(message);
        do
        {
            try
            {
                message = (String) input.readObject();
                if(message != null) {
                    System.out.println("We just receieved: " + message);
                    sendMsg("\n" + message);
                }
            }catch(ClassNotFoundException classNotFoundException)
            {
                System.out.println("\n idk wtf that user sent!");
            }
        }while(!message.equals("END"));
    }

    private void sendMsg(String inMsg)
    {
        try
        {
            sendField.setText("");  //reset chat field
            String msgToSend = xmlLib.createXML(inMsg, this.name, this.chatColor);
            addMsgToLog(inMsg, name, chatColor);
            output.writeObject(msgToSend);
            output.flush(); //TODO check this!
        }catch(IOException ioException)
        {
            System.out.println("can't send that message");
        }
    }
    private void recieveMsg(String inXMLMsg){
        if (!xmlLib.checkMsg(inXMLMsg)){
            addMsgToLog("Broken message recieved!", "[SYSTEM]","#000000");


        }
        else {
            //do usual stuff
            addMsgToLog(xmlLib.getMsg(inXMLMsg),xmlLib.findUser(inXMLMsg), xmlLib.findColor(inXMLMsg));

        }


    }
    private void addMsgToLog(String inMsg, String user, String inColor){
        chatLogText += "<br>" + xmlLib.getLogText(inMsg, user, inColor);
        chatLog.setText(chatLogText);
    }


    @Override
    public void run() {


        /*try {
            this.whileChatting();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
