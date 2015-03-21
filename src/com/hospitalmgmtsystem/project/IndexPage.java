/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmtsystem.project;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Raunak Shakya
 */
public class IndexPage extends JFrame {

    JButton bpat, bdoc, bbill, breport, bback, bexit;
    JLabel linfo, linfo1, linfo2, linfo3, linfo4;

    //Get the screen size...
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    public IndexPage() {
        super(LayoutUtils.APPLICATION_TITLE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu addMenu = new JMenu(" File ");
        addMenu.setMnemonic('F');
        JMenuItem menuItem = new JMenuItem("  Add New Patient  ");
        menuItem.setMnemonic('P');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                add(new PatientAdd());
            }
        });
        addMenu.add(menuItem);
        menuItem = new JMenuItem("  Add New Doctor  ");
        menuItem.setMnemonic('D');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                add(new DoctorAdd());
            }
        });
        addMenu.add(menuItem);
        menuItem = new JMenuItem("  Add New Staff  ");
        menuItem.setMnemonic('S');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                add(new StaffAdd());
            }
        });
        addMenu.add(menuItem);
        addMenu.addSeparator();
        menuItem = new JMenuItem("  Logout");
        menuItem.setMnemonic('L');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                HospitalMgmtSystemProject hospitalMgmtSystemProject = new HospitalMgmtSystemProject();
            }
        });
        addMenu.add(menuItem);

        menuItem = new JMenuItem("  Exit");
        menuItem.setMnemonic('E');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        addMenu.add(menuItem);

        //Adding View Menu Items...
        JMenu viewMenu = new JMenu("  View Record  ");
        viewMenu.setMnemonic('V');
        menuItem = new JMenuItem("  Specific Patient");
        menuItem.setMnemonic('P');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new PatientView());
            }
        });
        viewMenu.add(menuItem);
        menuItem = new JMenuItem("  Specific Doctor");
        menuItem.setMnemonic('D');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new DoctorView());
            }
        });
        viewMenu.add(menuItem);
        menuItem = new JMenuItem("  Specific Staff");
        menuItem.setMnemonic('S');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new StaffView());
            }
        });
        viewMenu.add(menuItem);

        //Adding Modify Menu Items...
        JMenu modifyMenu = new JMenu("  Modify Record  ");
        modifyMenu.setMnemonic('M');
        menuItem = new JMenuItem("  Current Patient");
        menuItem.setMnemonic('P');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new PatientModify());
            }
        });
        modifyMenu.add(menuItem);
        menuItem = new JMenuItem("  Current Doctor");
        menuItem.setMnemonic('D');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new DoctorModify());
            }
        });
        modifyMenu.add(menuItem);
        menuItem = new JMenuItem("  Current Staff");
        menuItem.setMnemonic('S');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new StaffModify());
            }
        });
        modifyMenu.add(menuItem);

        //Adding Report Menu Item...
        JMenu reportMenu = new JMenu("  Reports  ");
        reportMenu.setMnemonic('R');
        menuItem = new JMenuItem("  Display Patient Report");
        menuItem.setMnemonic('P');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new PatientDBData());
            }
        });
        reportMenu.add(menuItem);
        menuItem = new JMenuItem("  Display Doctor Report");
        menuItem.setMnemonic('D');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new DoctorDBData());
            }
        });
        reportMenu.add(menuItem);
        reportMenu.add(menuItem);
        menuItem = new JMenuItem("  Display Staff Report");
        menuItem.setMnemonic('S');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new StaffDBData());
            }
        });
        reportMenu.add(menuItem);
        
        
        //Adding Billing Menu Item...
        JMenu billingMenu = new JMenu("  Billing  ");
        billingMenu.setMnemonic('B');
        menuItem = new JMenuItem("  Show Billing ");
        menuItem.setMnemonic('B');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new BillingPage());
            }
        });
        billingMenu.add(menuItem);
        
        JMenu helpMenu = new JMenu("  Help  ");
        helpMenu.setMnemonic('H');
        menuItem = new JMenuItem("  Contents");
        menuItem.setMnemonic('C');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                add(new ContentsPage());
            }
        });
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("  License");
        menuItem.setMnemonic('L');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //add(new LicensePage());
                
                JOptionPane.showMessageDialog(null, "This software is licensed to Vayodha Hospital & Research Center...", "Software License", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("  Update");
        menuItem.setMnemonic('U');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //add(new UpdatePage());
                
                JOptionPane.showMessageDialog(null, "No new updates available at present!", "New Updates", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(menuItem);
        helpMenu.addSeparator();
        menuItem = new JMenuItem("  Current Version");
        menuItem.setMnemonic('V');
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //add(new VersionPage());
                JOptionPane.showMessageDialog(null, "Current Version: 1.0\nRelease Date 2014", "Software Version", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(menuItem);

        //Adding all the Menu Items in the MenuBar...
        menuBar.add(addMenu);
        menuBar.add(viewMenu);
        menuBar.add(modifyMenu);
        menuBar.add(reportMenu);
        menuBar.add(billingMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        Container con = getContentPane();
        con.setBackground(Color.GRAY);
        
        //setSize(new Dimension(screen.width/2, screen.height/2));
        //setSize(screen.width, screen.height);
        setSize(1000, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);  
        JLabel bgPicLbl=new JLabel(new ImageIcon("src/resources/images/a.jpg"));
        setContentPane(bgPicLbl);
        setLayout(null);
    }

    public static void main(String args[]) {
        // setup the look and feel properties
        Properties props = new Properties();

        props.put("logoString", "Vayodha");
        props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");

        // set your theme
        AcrylLookAndFeel.setCurrentTheme(props);

        try {
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        IndexPage indexPage = new IndexPage();
    }
}
