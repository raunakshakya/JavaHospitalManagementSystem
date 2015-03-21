/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.system.utils.LayoutUtils;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Raunak Shakya
 */
public class ContentsPage extends JInternalFrame {

    JTextArea txtHelpContents;
    JScrollPane sp;

    public ContentsPage() {
        super(LayoutUtils.HELP_CONTENTS_TITLE);

        txtHelpContents = new JTextArea(10, 30);
        txtHelpContents.setEditable(false);
        txtHelpContents.setLineWrap(true);
        txtHelpContents.setWrapStyleWord(true);
        sp = new JScrollPane(txtHelpContents);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        txtHelpContents.setText("\nThis is a simple desktop application built in JAVA using Swing Components "
                + "and PostGreSQL database. \n\nYou need to open PostGreSQL database server and must have the database and its"
                + " tables created to successfully run this application. Otherwise you will be facing consistent hiccups.");
        add(sp);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(360, 210);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setVisible(true);
        setLocation(300, 160);
    }

}
