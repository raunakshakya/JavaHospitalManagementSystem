/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.FlowLayout;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Raunak Shakya
 */
public class VersionPage extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    JLabel lblversion;
    JPanel jPanel;

    public VersionPage() {
        super(LayoutUtils.CURRENT_VERSION_TITLE);

        jPanel = new JPanel();
        lblversion = new JLabel(messages.getString("version.page.title"));
        jPanel.add(lblversion);
        add(jPanel);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setResizable(false);
        setVisible(true);
        setLocation(300, 200);
    }

    public static void main(String args[]) {
        VersionPage versionPage = new VersionPage();
    }

}
