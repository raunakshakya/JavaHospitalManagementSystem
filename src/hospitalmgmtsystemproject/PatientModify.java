/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmgmtsystemproject;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
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
public class PatientModify extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, subTitle, submedTitle, lblInsertPNo, lblPatNo, lblfullname, lbladdress, lblcontact,
            lblMedTitle, lblbloodgroup, lbldob, lblhistory, lblcurrentproblem, lblpatientno, lblroomno,
            lbldoa, lblgender, lbldf1, lbldf2, lbldoctor;
    JTextField txtpatno, txtfullname, txtcontact, txtdob, txtroomno, txtdoa, txtdoctor;
    TextArea txtaddress, txthistory, txtcurrentproblem;
    JButton btnSubmit, btnClear, btnModify;
    Choice chbg;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;

    public PatientModify() {
        super("Modify Patient Information");

        //Main Title
        mainTitle = new JLabel("Modify Patient Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        //Get the Patient Number...
        lblInsertPNo = new JLabel("Insert Patient Number");
        lblInsertPNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertPNo.setBounds(40, 70, 160, 25);
        add(lblInsertPNo);

        lblPatNo = new JLabel("Patient No.:");
        lblPatNo.setBounds(40, 100, 150, 25);
        add(lblPatNo);

        txtpatno = new JTextField(30);
        txtpatno.setBounds(140, 100, 160, 25);
        add(txtpatno);

        btnSubmit = new JButton("SEARCH");
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton("CLEAR ALL");
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        btnModify = new JButton("UPDATE PATIENT");
        btnModify.setBounds(540, 98, 140, 30);
        add(btnModify);

        //Personal Information Title...
        subTitle = new JLabel("Personal Information");
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
        subTitle.setBounds(40, 160, 200, 30);
        add(subTitle);

        lblfullname = new JLabel("Full Name :");
        lblfullname.setBounds(60, 200, 80, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        add(txtfullname);

        lbladdress = new JLabel("Address :");
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 90);
        add(txtaddress);

        lblcontact = new JLabel("Contact :");
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(680, 200, 200, 25);
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
        txtdob.setBounds(680, 280, 200, 25);
        add(txtdob);

        lbldf1 = new JLabel("(dd-mm-yyyy)");
        lbldf1.setBounds(800, 310, 100, 25);
        add(lbldf1);

        //Medical Information Title...
        submedTitle = new JLabel("Medical Information");
        submedTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submedTitle.setBounds(40, 360, 200, 15);
        add(submedTitle);

        lblbloodgroup = new JLabel("Blood Group :");
        lblbloodgroup.setBounds(60, 400, 80, 25);
        add(lblbloodgroup);

        chbg = new Choice();
        chbg.setBounds(200, 400, 80, 25);
        chbg.addItem("A -ve");
        chbg.addItem("A +ve");
        chbg.addItem("B -ve");
        chbg.addItem("B +ve");
        chbg.addItem("AB -ve");
        chbg.addItem("AB +ve");
        chbg.addItem("O +ve");
        chbg.addItem("O -ve");
        add(chbg);

        lblhistory = new JLabel("History :");
        lblhistory.setBounds(60, 440, 80, 25);
        add(lblhistory);

        txthistory = new TextArea();
        txthistory.setBounds(200, 440, 200, 100);
        add(txthistory);

        lblroomno = new JLabel("Room No.:");
        lblroomno.setBounds(60, 560, 80, 25);
        add(lblroomno);

        txtroomno = new JTextField(30);
        txtroomno.setBounds(200, 560, 200, 25);
        add(txtroomno);

        lbldoa = new JLabel("Date Of Admission :");
        lbldoa.setBounds(540, 400, 120, 25);
        add(lbldoa);

        txtdoa = new JTextField(40);
        txtdoa.setBounds(680, 400, 125, 20);
        add(txtdoa);

        lbldf2 = new JLabel("(dd-mm-yyyy)");
        lbldf2.setBounds(810, 400, 100, 20);
        add(lbldf2);

        lblcurrentproblem = new JLabel("Current Problem :");
        lblcurrentproblem.setBounds(540, 440, 100, 25);
        add(lblcurrentproblem);

        txtcurrentproblem = new TextArea();
        txtcurrentproblem.setBounds(680, 440, 200, 100);
        add(txtcurrentproblem);

        lbldoctor = new JLabel("Attending Doctor :");
        lbldoctor.setBounds(540, 560, 100, 25);
        add(lbldoctor);

        txtdoctor = new JTextField(100);
        txtdoctor.setBounds(680, 560, 200, 25);
        add(txtdoctor);

        //Database Connection...
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "niitktm");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry, check your database connection!!!");
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

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(PatientModify.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        PatientModify patientModify = new PatientModify();
//    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtfullname.setText("");
            txtcontact.setText("");
            txtdob.setText("");
            txtaddress.setText("");
            txthistory.setText("");
            txtcurrentproblem.setText("");
            txtcontact.setText("");
            txtroomno.setText("");
            txtdoa.setText("");
            txtdoctor.setText("");
            txtpatno.setText("");
            cbmf.setSelectedCheckbox(null);
        }
    }

    class modify implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Integer num1 = Integer.parseInt(txtpatno.getText());
                if (num1.equals(null)) {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID...");
                } else {

                    String name1 = txtfullname.getText().trim();
                    String addr1 = txtaddress.getText().trim();
                    String contact1 = txtcontact.getText().trim();
                    String blgr1 = chbg.getSelectedItem().trim();
                    String hist1 = txthistory.getText().trim();
                    String dob1 = txtdob.getText().trim();
                    String current1 = txtcurrentproblem.getText().trim();
                    String roomno1 = txtroomno.getText().trim();
                    String dateadd1 = txtdoa.getText().trim();
                    String gender1 = "";
                    if (cbm.getState() == true) {
                        gender1 = "M";
                    }
                    if (cbf.getState() == true) {
                        gender1 = "F";
                    }

                    int docid = 0;
                    String docname = txtdoctor.getText();
                    if (!docname.isEmpty()) {
                        stmt = conn.prepareStatement("SELECT doctor_id FROM doctor_table WHERE doctor_fullname=?");
                        stmt.setString(1, docname);
                        rs = stmt.executeQuery();
                        if (rs.next()) {
                            docid = rs.getInt("doctor_id");
                        }
                    }
                    
                    String query = "UPDATE patient_table SET patient_fullname=?, patient_address=?, patient_contact=?, "
                            + "patient_history=?, patient_bloodgroup=?, patient_currentproblem=?, "
                            + "patient_roomid=?, patient_gender=?, patient_doctorid=?, patient_dateofbirth='"+dob1
                            + "', patient_doa='"+dateadd1+"' WHERE patient_id=?";

                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, name1);
                    stmt.setString(2, addr1);
                    stmt.setString(3, contact1);
                    stmt.setString(4, hist1);
                    stmt.setString(5, blgr1);
                    stmt.setString(6, current1);
                    stmt.setString(7, roomno1);
                    stmt.setString(8, gender1);
                    stmt.setInt(9, docid);
                    stmt.setInt(10, num1);
         
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Patient Data Modified successfully!", "Update Success!", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in updating Patient Data", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if(!txtpatno.getText().isEmpty()){
                Integer num = Integer.parseInt(txtpatno.getText());
                String name, addr, contact, blgr, hist, dob, current, roomno, dateadd, gender, docname = null;

                stmt = conn.prepareStatement("SELECT * FROM patient_table WHERE patient_id=?");
                stmt.setInt(1, num);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    name = rs.getString("patient_fullname");
                    addr = rs.getString("patient_address");
                    contact = rs.getString("patient_contact");
                    hist = rs.getString("patient_history");
                    dob = rs.getString("patient_dateofbirth");
                    current = rs.getString("patient_currentproblem");
                    blgr = rs.getString("patient_bloodgroup");
                    roomno = rs.getString("patient_roomid");
                    dateadd = rs.getString("patient_doa");
                    gender = rs.getString("patient_gender");

                    int docid = rs.getInt("patient_doctorid");
                    stmt = conn.prepareStatement("SELECT * FROM doctor_table WHERE doctor_id=?");
                    stmt.setInt(1, docid);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        docname = rs.getString("doctor_fullname");
                    }

                    txtfullname.setText(name);
                    txtcontact.setText(contact);
                    txtdob.setText(dob);
                    txtaddress.setText(addr);
                    txthistory.setText(hist);
                    txtcurrentproblem.setText(current);
                    chbg.select(blgr);
                    txtroomno.setText(roomno);
                    txtdoa.setText(dateadd);
                    txtdoctor.setText(docname);
                    if (gender.equals("M")) {
                        cbm.setState(true);
                    }
                    if (gender.equals("F")) {
                        cbf.setState(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Patient Record Not Found!!!");
                }
                
                }else{
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID......");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in retrieving Patient Information!!!");
            }
        }
    }
}
