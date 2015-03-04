import java.awt.*;

/**Simple XML parsing stuff, all static for now*/
public class xmlLib {
    public static void main(String[] args){
        showMsg(createXML("hej! såhär ser en XML tagg ut: <tag></tag>", "din mamma", "#RRGGBB"));

    }
    /**Shows the actual message text*/
    public static void showMsg(final String XMLmsgIn){
        int textStart = XMLmsgIn.indexOf("<text");
        String msgText = XMLmsgIn.substring(XMLmsgIn.indexOf("<text"));
        msgText = msgText.substring(msgText.indexOf(">")+1, msgText.indexOf("</text"));
        msgText = recoverXMLchars(msgText);     //puts XML syntax back into the msg text
        String color = findColor(XMLmsgIn);
        //TODO use color here later for real display awesomeness!
        System.out.println(findUser(XMLmsgIn) + " says: " + msgText);


    }
    /**Finds username*/
    public static String findUser(String inMsg){
        String user;
        int sender = inMsg.indexOf("sender");
        int startUser = inMsg.indexOf("=",sender) + 2;
        int endUser = inMsg.indexOf('"', startUser);
        user = inMsg.substring(startUser, endUser);
        return user;
    }
    /**Finds color hex code*/
    public static String findColor(String inText){
        int color = inText.indexOf("color");
        int startCol = inText.indexOf("=", color);
        return inText.substring(startCol+2, startCol+9);
    }


    /**Puts XML syntax back into the msg for display awesomeness*/
    public static String recoverXMLchars(String msgIn){
        msgIn = msgIn.replaceAll("&lt;", "<");
        msgIn = msgIn.replaceAll("&gt;", ">");
        msgIn = msgIn.replaceAll("&amp;", "&");
        msgIn = msgIn.replaceAll("&apos;", "'");
        msgIn = msgIn.replaceAll("&apos;", "'");
        msgIn = msgIn.replaceAll("&quot;", "\"");

        return msgIn;

    }
    /**Checks if the message root tag is ok*/
    public static boolean checkMsg(String inMsg){
        return inMsg.startsWith("<message") && inMsg.endsWith("</message>");
    }

    /**Replaces xml syntax in msg text*/
    public static String replaceXMLchars(String msgIn) {
        msgIn = msgIn.replaceAll("&", "&amp;");
        msgIn = msgIn.replaceAll("<", "&lt;");
        msgIn = msgIn.replaceAll(">", "&gt;");
        msgIn = msgIn.replaceAll("'", "&apos;");
        msgIn = msgIn.replaceAll("\"", "&quot;");
        return msgIn;
    }

    /**Creates the XML formatted string to send*/
    public static String createXML(String msg, String name, String color){ //TODO express color properly?
        msg = replaceXMLchars(msg); //replace possible xml syntax in the message body
        return  "<message sender=" + '"'+ name + '"' + "> <text color=" + '"' + color + '"' + ">" + '"' + msg + '"' + " </text> </message>";
    }
    /**Crates the formatted message, ready to be set to the JEditorPane log*/
    public static String getLogText(String inMsg, String name, String color){
        String logText = "<br/><p>" + name + ":" +
                "<font color=\"" + color + "\">" +
                "<br/>" + inMsg + "</font></p>";
        System.out.println(logText);
        return logText;
    }
    public static String color2HexString(Color color) {
        return "#" + Integer.toHexString(color.getRGB() & 0x00ffffff);
    }


}
