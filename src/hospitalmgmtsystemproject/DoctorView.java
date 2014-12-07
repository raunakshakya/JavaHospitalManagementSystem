/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmgmtsystemproject;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Raunak Shakya
 */
public class DoctorView extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    JLabel mainTitle, lblInsertDNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldf1,
            lblprofTitle, lblspecialization, lbldoctorid, lwork, lblworkfrom, lblworkto, lbldoj, lbldf2, lblpatientlist;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtdoctorid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtspecialization, txtpatientlist;
    JButton btnSubmit, btnClear, bback;

    public DoctorView() {
        super("View Doctor Information");

        mainTitle = new JLabel("Doctor Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertDNo = new JLabel("Insert Doctor Identity :");
        lblInsertDNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertDNo.setBounds(40, 70, 160, 25);
        add(lblInsertDNo);

        lbldoctorid = new JLabel("Doctor ID :");
        lbldoctorid.setBounds(40, 100, 100, 25);
        add(lbldoctorid);

        txtdoctorid = new JTextField(30);
        txtdoctorid.setBounds(140, 100, 160, 25);
        add(txtdoctorid);

        btnSubmit = new JButton("SEARCH");
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton("CLEAR ALL");
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        lblsubTitle = new JLabel("Personal Information");
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(40, 150, 200, 30);
        add(lblsubTitle);

        lblfullname = new JLabel("Full Name :");
        lblfullname.setBounds(60, 200, 70, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel("Address :");
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel("Contact :");
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel("Gender :");
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);

        txtgender = new JTextField(50);
        txtgender.setBounds(660, 240, 200, 25);
        txtgender.setEditable(false);
        add(txtgender);

        lbldob = new JLabel("Date of Birth :");
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);

        txtdob = new JTextField(15);
        txtdob.setBounds(660, 280, 200, 25);
        txtdob.setEditable(false);
        add(txtdob);

        lbldf1 = new JLabel("(dd-mm-yyyy)");
        lbldf1.setBounds(780, 310, 100, 25);
        add(lbldf1);

        //Professional Title...
        lblprofTitle = new JLabel("Professional Information");
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(40, 400, 300, 30);
        add(lblprofTitle);

        lblspecialization = new JLabel("Specialization :");
        lblspecialization.setBounds(60, 450, 100, 25);
        add(lblspecialization);

        txtspecialization = new TextArea();
        txtspecialization.setBounds(200, 450, 200, 130);
        txtspecialization.setEditable(false);
        add(txtspecialization);

        lbldoj = new JLabel("Date Of Join :");
        lbldoj.setBounds(60, 600, 100, 25);
        add(lbldoj);

        txtdoj = new JTextField(40);
        txtdoj.setBounds(200, 600, 200, 25);
        txtdoj.setEditable(false);
        add(txtdoj);

        lbldf2 = new JLabel("(dd-mm-yyyy)");
        lbldf2.setBounds(320, 630, 100, 20);
        add(lbldf2);

        lblworkfrom = new JLabel("Shift From :");
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);

        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        txtworkfrom.setEditable(false);
        add(txtworkfrom);

        lblworkto = new JLabel("Shift To :");
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);

        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        txtworkto.setEditable(false);
        add(txtworkto);

        lblpatientlist = new JLabel("Patient List");
        lblpatientlist.setBounds(540, 530, 80, 25);
        add(lblpatientlist);

        txtpatientlist = new TextArea();
        txtpatientlist.setBounds(660, 530, 200, 100);
        add(txtpatientlist);

        //Database Connection...
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "niitktm");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the Database!!!");
        }

        btnClear.addActionListener(new clear());
        btnSubmit.addActionListener(new submit());

        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(230, 130);
        setLayout(null);
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtdoctorid.setText("");
            txtfullname.setText("");
            txtaddress.setText("");
            txtcontact.setText("");
            txtworkfrom.setText("");
            txtworkto.setText("");
            txtspecialization.setText("");
            txtpatientlist.setText("");
            txtdob.setText("");
            txtdoj.setText("");
            txtgender.setText("");
        }
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (!txtdoctorid.getText().trim().equals("")) {
                    Integer num = Integer.parseInt(txtdoctorid.getText());
                    String name, addr, contact, spec, workf, workt, gender, dob, doj;

                    stmt1 = conn.prepareStatement("SELECT * FROM doctor_table WHERE doctor_id=?");
                    stmt1.setInt(1, num);
                    ResultSet rs1 = stmt1.executeQuery();

                    if (rs1.next()) {
                        name = rs1.getString("doctor_fullname");
                        addr = rs1.getString("doctor_address");
                        contact = rs1.getString("doctor_contact");
                        gender = rs1.getString("doctor_gender");
                        dob = rs1.getString("doctor_dateofbirth");
                        doj = rs1.getString("doctor_dateofjoin");
                        spec = rs1.getString("doctor_specialization");
                        workf = rs1.getString("doctor_workshiftfrom");
                        workt = rs1.getString("doctor_workshiftto");

                        txtfullname.setText(name);
                        txtaddress.setText(addr);
                        txtcontact.setText(contact);
                        txtspecialization.setText(spec);
                        txtworkfrom.setText(workf);
                        txtworkto.setText(workt);
                        txtdob.setText(dob);
                        txtdoj.setText(doj);
                        txtgender.setText(gender);

                        stmt2 = conn.prepareStatement("SELECT patient_id, patient_fullname FROM patient_table WHERE patient_doctorid=?");
                        stmt2.setInt(1, num);
                        rs2 = stmt2.executeQuery();
                        ResultSetMetaData rsmt = rs2.getMetaData();
                        int ctr = rsmt.getColumnCount();
                        while (rs2.next()) {
                            for (int i = 1; i <= ctr; i++) {
                                txtpatientlist.append(rs2.getString(i) + " ");
                            }
                            txtpatientlist.append("\n");
                        }
                    } else {
                        txtdoctorid.setText("");
                        txtfullname.setText("");
                        txtaddress.setText("");
                        txtcontact.setText("");
                        txtworkfrom.setText("");
                        txtworkto.setText("");
                        txtspecialization.setText("");
                        txtpatientlist.setText("");
                        JOptionPane.showMessageDialog(null, "Doctor Reocrd Not Found!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Doctor ID!!!");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in retrieving Doctor Data!!!");
            }
        }
    }

}
