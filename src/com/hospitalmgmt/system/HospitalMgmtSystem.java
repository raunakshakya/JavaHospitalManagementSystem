/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.MessageUtils;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Raunak Shakya
 */
public class HospitalMgmtSystem implements ActionListener {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;
    
    //Declaration of Java Swing Components...
    static JFrame frame;
    private static JPanel panel1, panel2, panel3;
    private final JButton loginBtn, cancelBtn;
    private final JLabel loginFormTitle, usernameLbl, passwordLbl;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public HospitalMgmtSystem() {

        //Defining the frame properties...
        frame = new JFrame(messages.getString("application.title"));
        frame.setSize(LayoutUtils.LOGIN_WINDOW_WIDTH, LayoutUtils.LOGIN_WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //First Panel...
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(Color.DARK_GRAY);
        loginFormTitle = new JLabel(messages.getString("application.name"));
        Font titleFont = new Font("Comic Sans MS", Font.BOLD, 30);
        loginFormTitle.setFont(titleFont);
        loginFormTitle.setForeground(Color.WHITE);
        panel1.add(loginFormTitle); //Add Title to panel1

        //Second Panel...
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 2, 0, 10));
        panel2.setBackground(Color.GRAY);

        //User Name Label & TextField...
        Font usernameLblFont = new Font("Arial", Font.BOLD, 20);
        usernameLbl = new JLabel("      User Name: ");
        usernameLbl.setFont(usernameLblFont);
        usernameLbl.setForeground(Color.WHITE);
        usernameField = new JTextField(30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));

        //Password Label & Field...
        Font passwordLblFont = new Font("Arial", Font.BOLD, 20);
        passwordLbl = new JLabel("        Password: ");
        passwordLbl.setFont(passwordLblFont);
        passwordLbl.setForeground(Color.WHITE);
        passwordField = new JPasswordField(30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        //Add components to panel2...
        panel2.add(usernameLbl);
        panel2.add(usernameField);
        panel2.add(passwordLbl);
        panel2.add(passwordField);

        //Third Panel...
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel3.setBackground(Color.GRAY);

        //Login Button...
        loginBtn = new JButton(messages.getString("common.login"));//, new ImageIcon(getClass().getResource("/images/key.gif")));
        loginBtn.setPreferredSize(new Dimension(100, 30));
        loginBtn.addActionListener(this);

        //Login Cancel Button...
        cancelBtn = new JButton(messages.getString("common.cancel"));//, new ImageIcon(getClass().getResource("/images/Keys.gif")));
        cancelBtn.setPreferredSize(new Dimension(100, 30));
        cancelBtn.addActionListener(this);

        //Add Login & Cancel buttons to panel3...
        panel3.add(loginBtn);
        panel3.add(cancelBtn);

        //Fourth Panel...
        JLabel footerLabel = new JLabel(messages.getString("footer.title"));
        Font footerFont = new Font("Comic Sans MS", Font.BOLD, 16);
        footerLabel.setFont(footerFont);
        footerLabel.setForeground(Color.WHITE);
        panel3.add(footerLabel);

        //Using a container to add all the panels...
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // setup the look and feel properties
        Properties props = new Properties();
        props.put("logoString", "Vayodha");
        props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");

        // set your theme
        AcrylLookAndFeel.setCurrentTheme(props);

        try {
            UIManager.setLookAndFeel(LayoutUtils.JTATTOO_APPLICATION_THEME);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HospitalMgmtSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        HospitalMgmtSystem hospitalMgmtSystemProject = new HospitalMgmtSystem();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == loginBtn) {
            String loginName, loginPassword;
            loginName = usernameField.getText().trim();
            loginPassword = passwordField.getText().trim();

            try {
                Class.forName(DBConnectionUtils.DB_DRIVER);
                conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
                stmt = conn.prepareStatement("SELECT username, password FROM admin_table");
                rs = stmt.executeQuery();

                int flag = 0;
                while (rs.next()) {
                    if (loginName.equals(rs.getString("username")) && loginPassword.equals(rs.getString("password"))) {
                        flag = 1;
                        usernameField.setText("");
                        passwordField.setText("");
                        frame.setVisible(false);
                        frame.dispose();
                        IndexPage indexPage = new IndexPage();
                    }
                }
                if (flag != 1) {
                    usernameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(null, "User Name and Password Invalid", "Login Failure", JOptionPane.WARNING_MESSAGE);
                }
            } catch (ClassNotFoundException | SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error in Logging in!");
            }
        } else if (ae.getSource() == cancelBtn) {
            System.exit(0);
        }
    }
}
