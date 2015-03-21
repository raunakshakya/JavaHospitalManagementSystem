/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmgmtsystemproject;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class DoctorModify extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, lblInsertDNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldf1,
            lblprofTitle, lblspecialization, lbldoctorid, lwork, lblworkfrom, lblworkto, lbldoj, lbldf2;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtdoctorid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtspecialization;
    JButton btnSubmit, btnClear, btnModify;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;

    public DoctorModify() {
        super("Modify Doctor Information");
        
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

        btnModify = new JButton("UPDATE DOCTOR");
        btnModify.setBounds(540, 98, 150, 30);
        add(btnModify);

        lblsubTitle = new JLabel("Personal Information");
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(40, 150, 200, 30);
        add(lblsubTitle);

        lblfullname = new JLabel("Full Name :");
        lblfullname.setBounds(60, 200, 70, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        add(txtfullname);

        lbladdress = new JLabel("Address :");
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        add(txtaddress);

        lblcontact = new JLabel("Contact :");
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
        add(txtcontact);

        lblgender = new JLabel("Gender :");
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);

        cbmf = new CheckboxGroup();
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
        cbm.setBounds(680, 240, 60, 25);
        add(cbm);
        cbf.setBounds(740, 240, 60, 25);
        add(cbf);

        lbldob = new JLabel("Date of Birth :");
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);

        txtdob = new JTextField(15);
        txtdob.setBounds(660, 280, 200, 25);
        add(txtdob);

        lbldf1 = new JLabel("(yyyy-mm-dd)");
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
        add(txtspecialization);

        lblworkfrom = new JLabel("Shift From :");
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);

        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        add(txtworkfrom);

        lblworkto = new JLabel("Shift To :");
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);

        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        add(txtworkto);

        lbldoj = new JLabel("Date Of Join :");
        lbldoj.setBounds(540, 530, 100, 25);
        add(lbldoj);

        txtdoj = new JTextField(40);
        txtdoj.setBounds(660, 530, 200, 25);
        add(txtdoj);

        lbldf2 = new JLabel("(yyyy-mm-dd)");
        lbldf2.setBounds(780, 560, 100, 20);
        add(lbldf2);

        //Database Connection...
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "postgres");
        } catch (Exception e) {
            System.out.println(e);
        }

        btnClear.addActionListener(new clear());
        btnSubmit.addActionListener(new submit());
        btnModify.addActionListener(new modify());
        
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(230, 130);
        setLayout(null);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtfullname.setText("");
            txtcontact.setText("");
            txtdoctorid.setText("");
            txtworkfrom.setText("");
            txtworkto.setText("");
            txtaddress.setText("");
            txtspecialization.setText("");
            txtdob.setText("");
            txtdoj.setText("");
            cbmf.setSelectedCheckbox(null);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DoctorModify.class.getName()).log(Level.SEVERE, null, ex);
        }

        DoctorModify doctorModify = new DoctorModify();
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (!txtdoctorid.getText().isEmpty()) {
                    Integer num = null;
                    try {
                        num = Integer.parseInt(txtdoctorid.getText().trim());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Doctor ID......");
                        txtdoctorid.setText("");
                    }

                    String name, addr, contact, gender, spec, workf, workt, dob, doj;

                    if (!txtdoctorid.getText().trim().equals("")) {
                        stmt = conn.prepareStatement("SELECT * FROM doctor_table WHERE doctor_id=?");
                        stmt.setInt(1, num);
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            name = rs.getString("doctor_fullname");
                            addr = rs.getString("doctor_address");
                            contact = rs.getString("doctor_contact");
                            gender = rs.getString("doctor_gender");
                            spec = rs.getString("doctor_specialization");
                            workf = rs.getString("doctor_workshiftfrom");
                            workt = rs.getString("doctor_workshiftto");
                            dob = rs.getString("doctor_dateofbirth");
                            doj = rs.getString("doctor_dateofjoin");

                            if (gender.equals("M")) {
                                cbm.setState(true);
                            }
                            if (gender.equals("F")) {
                                cbf.setState(true);
                            }

                            txtfullname.setText(name);
                            txtaddress.setText(addr);
                            txtcontact.setText(contact);
                            txtspecialization.setText(spec);
                            txtworkfrom.setText(workf);
                            txtworkto.setText(workt);
                            txtdob.setText(dob);
                            txtdoj.setText(doj);

                        } else {
                            JOptionPane.showMessageDialog(null, "Patient Record Not Found!!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter the Doctor ID!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID......");
                }
            } catch (SQLException sq) {

            }
        }
    }

    class modify implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Integer num1 = Integer.parseInt(txtdoctorid.getText().trim());
                if (num1.equals(null)) {
                    JOptionPane.showMessageDialog(null, "First Enter the Doctor ID...");
                } else {

                    String name1 = txtfullname.getText().trim();
                    String addr1 = txtaddress.getText().trim();
                    String contact1 = txtcontact.getText().trim();
                    String spec1 = txtspecialization.getText().trim();
                    String workf1 = txtworkfrom.getText().trim();
                    String workt1 = txtworkto.getText().trim();
                    String dob1 = txtdob.getText().trim();
                    String datedoj1 = txtdoj.getText().trim();

                    String query = "UPDATE doctor_table SET doctor_fullname=?, doctor_address=?, doctor_contact=?, "
                            + "doctor_specialization=?, doctor_gender=?, doctor_workshiftfrom='" + workf1 + "', "
                            + "doctor_workshiftto='" + workt1 + "', doctor_dateofbirth='" + dob1 + "', doctor_dateofjoin='"
                            + datedoj1 + "' WHERE doctor_id=?";

                    String gender1 = "";
                    if (cbm.getState() == true) {
                        gender1 = "M";
                    }
                    if (cbf.getState() == true) {
                        gender1 = "F";
                    }

                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, name1);
                    stmt.setString(2, addr1);
                    stmt.setString(3, contact1);
                    stmt.setString(4, spec1);
                    stmt.setString(5, gender1);
                    stmt.setInt(6, num1);

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(new JFrame(), "Data Modified successfully!", "Done!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error in updating Doctor Data......", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
