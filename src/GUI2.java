import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The actual chat window used by both client and server
 */
public class GUI2 extends JPanel implements Runnable{

    // THE BUTTONS
    private JButton sendButton;
    private JButton colorPicker;
    private JButton disconnectButton;
    // STUFF USED IN GUI2
    private JTextField sendField;
    private JEditorPane chatLog;
    private Color tempColor = Color.black; // Setting default color to black in casse we don't choose any color from the color picker.
    private JLabel namelabel;
    private String chatColor = "#000000";
    private String name;
    private String chatLogText = "<html><body><p>Chat Time!<p>";
    //IOSTREAMS
    private ObjectInputStream input;
    private ObjectOutputStream output;


    /**The GUI2 Constructor*/
    public GUI2(String name, ObjectOutputStream o, ObjectInputStream i)
    {
        input = i;      //Input = the "input" from the constructor (//receive stuff)
        output = o;     //Output = the "Output" from the constructor (//Send stuff)
        setPreferredSize(new Dimension(450,450));
        this.name = name;
        namelabel = new JLabel(name);
        chatLog = new JEditorPane();
        chatLog.setPreferredSize(new Dimension(440, 350));
        chatLog.setContentType("text/html");
        chatLog.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(chatLog);
        scrollPane.setAutoscrolls(true);
        add(scrollPane);
        chatLog.setText(chatLogText);


        sendButton = new JButton("SEND");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //click
                sendMsg(sendField.getText());
            }
        });

        disconnectButton = new JButton("Disconnect");
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg("has disconnected... GLHF");
                try {
                    output.close();
                    input.close();
                    //System.exit(-1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        colorPicker = new JButton("Pick a color");
        colorPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempColor = JColorChooser.showDialog(null, "Pick a color", Color.black);
                if (tempColor != null)  //make sure that cancel works properly
                    chatColor=xmlLib.color2HexString(tempColor);
            }


        });

        sendField = new JTextField();
        sendField.setColumns(30);
        sendField.addActionListener(new ActionListener() {  //enter
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg(sendField.getText());
            }
        });

        add(namelabel);
        add(sendField);
        add(colorPicker);
        add(sendButton);
        add(disconnectButton);
    }
    private void whileChatting()throws IOException {
        String message = "You are now connected";
        sendMsg(message);
        do
        {
            try
            {
                message = (String) input.readObject();
                recieveMsg(message);
            }catch(ClassNotFoundException classNotFoundException)
            {
                System.out.println("\n msg from the other dude/dudette is unreadable");
            }
        }while(!message.equals(null));
    }

    private void sendMsg(String inMsg)
    {
        try
        {
            sendField.setText("");  //reset chat field
            String msgToSend = xmlLib.createXML(inMsg, this.name, this.chatColor);
            addMsgToLog(inMsg, name, chatColor);
            output.writeObject(msgToSend);
            output.flush();
        }catch(IOException ioException)
        {
            System.out.println("can't send that message");
        }
    }
    private void recieveMsg(String inXMLMsg){
        if (!xmlLib.checkMsg(inXMLMsg)){    //check for broken msg
            addMsgToLog("Broken message recieved! =(", "[SYSTEM]","#000000");

        }
        else {
            addMsgToLog(xmlLib.getMsg(inXMLMsg),xmlLib.findUser(inXMLMsg), xmlLib.findColor(inXMLMsg));
        }


    }
    private void addMsgToLog(String inMsg, String user, String inColor){
        chatLogText += "<br>" + xmlLib.getLogText(inMsg, user, inColor);

        chatLog.setText(chatLogText+"</body></html>");
        //System.out.println(chatLogText);

        //chatLog.setCaret(chatLog.);   //scroll bs
    }


    @Override
    public void run() {


        try {
            this.whileChatting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
