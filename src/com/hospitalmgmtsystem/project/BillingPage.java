/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmtsystem.project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class BillingPage extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, lblfullname, lblpatientno, lbldoa, lbldod, lblroom, lblamount, txtdod;
    JTextField txtfullname, txtpatientno, txtdoa, txtroom, txtamount;
    JButton btnSearch, btnClear, bback;

    public BillingPage() {
        super("Patient Billing Information");
        
        //Billing Information...
        mainTitle = new JLabel("Billing Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(360, 25, 300, 30);
        add(mainTitle);

        lblfullname = new JLabel("Patient Name :");
        lblfullname.setBounds(100, 100, 150, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(240, 100, 200, 25);
        txtfullname.setEditable(false);
        add(txtfullname);

        lblpatientno = new JLabel("Patient No. :");
        lblpatientno.setBounds(550, 100, 160, 25);
        add(lblpatientno);

        txtpatientno = new JTextField(30);
        txtpatientno.setBounds(680, 100, 200, 25);
        add(txtpatientno);

        lbldoa = new JLabel("Date of Admission :");
        lbldoa.setBounds(100, 175, 150, 25);
        add(lbldoa);

        txtdoa = new JTextField(20);
        txtdoa.setBounds(240, 175, 200, 25);
        txtdoa.setEditable(false);
        add(txtdoa);

        lbldod = new JLabel("Date of Discharge :");
        lbldod.setBounds(550, 175, 200, 25);
        add(lbldod);

        lblroom = new JLabel("Room No. :");
        lblroom.setBounds(100, 250, 150, 25);
        add(lblroom);

        txtroom = new JTextField(20);
        txtroom.setBounds(240, 250, 200, 25);
        txtroom.setEditable(false);
        add(txtroom);

        lblamount = new JLabel("Total Amount :");
        lblamount.setBounds(100, 325, 85, 25);
        add(lblamount);

        txtamount = new JTextField(20);
        txtamount.setBounds(240, 325, 200, 25);
        txtamount.setEditable(false);
        add(txtamount);

        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(350, 430, 120, 30);
        add(btnSearch);

        btnClear = new JButton("CLEAR");
        btnClear.setBounds(500, 430, 120, 30);
        add(btnClear);

        //Database Connection...
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "postgres");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem connecting to the Database!!!");
        }

        btnClear.addActionListener(new clear());
        btnSearch.addActionListener(new submit());

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            String dd1 = df.format(cal.getTime());

            txtdod = new JLabel(dd1);
            txtdod.setBounds(680, 175, 200, 25);
            add(txtdod);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something's wrong!!!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        
        setSize(1000, 540);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(200, 100);
        setLayout(null);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtfullname.setText("");
            txtpatientno.setText("");
            txtdoa.setText("");
            txtroom.setText("");
            txtamount.setText("");
        }
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                int patientno = Integer.parseInt(txtpatientno.getText());
                int no;
                String name, roomno, doa;

                stmt = conn.prepareStatement("SELECT * FROM patient_table WHERE patient_id=?");
                stmt.setInt(1, patientno);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    no = rs.getInt("patient_id");
                    name = rs.getString("patient_fullname");
                    doa = rs.getString("patient_doa");
                    roomno = rs.getString("patient_roomid");

                    txtfullname.setText(name);
                    txtdoa.setText(doa);
                    txtroom.setText(roomno);
                }else{
                    JOptionPane.showMessageDialog(null, "Patient Record Not Found!!!");
                }

                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    df.setLenient(false);

                    int dnow = cal.get(Calendar.DAY_OF_MONTH);
                    int mnow = cal.get(Calendar.MONTH);
                    int ynow = cal.get(Calendar.YEAR);
                    int mnowF = mnow + 1;
                    
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(df.parse(txtdoa.getText()));
                    
                    int daddd = cal1.get(Calendar.DAY_OF_MONTH);
                    int daddMM = cal1.get(Calendar.MONTH);
                    int daddYY = cal1.get(Calendar.YEAR);
                    int daddMMF = daddMM + 1;

                    long from = new java.util.GregorianCalendar(ynow, mnowF, dnow).getTime().getTime();
                    long to = new java.util.GregorianCalendar(daddYY, daddMMF, daddd).getTime().getTime();
                    double difference = from - to;

                    long days = Math.round((difference / (1000 * 60 * 60 * 24)));
                    long bill = 0;

                    int m = 2000;
                    bill = days * m;

                    //Final Bill
                    String FinalBill = (new Long(bill)).toString();
                    txtamount.setText(FinalBill);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error in calcuating bill!!!");
                }

            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "There's an error. Sorry for the inconvenience!");
            }
        }
    }
    
}
