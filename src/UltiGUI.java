import javax.swing.*;
//import java.awt.FlowLayout;

/**
 * Created by Lubomir on 2015-03-04.
 */
public class UltiGUI extends JFrame {
    private JPanel rootPanel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public UltiGUI(){
        super("Bezt Chat Ev0r");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
