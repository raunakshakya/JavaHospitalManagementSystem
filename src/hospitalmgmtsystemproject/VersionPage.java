/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmgmtsystemproject;

import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Raunak Shakya
 */
public class VersionPage extends JInternalFrame {

    JLabel lblversion;
    JPanel pn;

    public VersionPage() {
        super("Current Version");

        pn = new JPanel();
        lblversion = new JLabel("Version Page");
        pn.add(lblversion);
        add(pn);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setResizable(false);
        setVisible(true);
        setLocation(300, 200);
    }
    
    public static void main(String args[]){
        VersionPage versionPage = new VersionPage();
    }

}
