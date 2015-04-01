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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Raunak Shakya
 */
public class ContentsPage extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    JTextArea helpContents;
    JScrollPane jScrollPane;

    public ContentsPage() {
        super(LayoutUtils.HELP_CONTENTS_TITLE);

        helpContents = new JTextArea(10, 30);
        helpContents.setEditable(false);
        helpContents.setLineWrap(true);
        helpContents.setWrapStyleWord(true);
        jScrollPane = new JScrollPane(helpContents);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        helpContents.setText(messages.getString("contents.page.descripton"));
        add(jScrollPane);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(360, 210);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setVisible(true);
        setLocation(300, 160);
    }

}
