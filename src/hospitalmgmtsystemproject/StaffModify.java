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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class StaffModify extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, lblInsertSNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldf1,
            lblprofTitle, lbldepartment, lblstaffid, lwork, lblworkfrom, lblworkto, lbldoj, lbldf2, lblpost;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtstaffid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtdepartment, txtpost;
    JButton btnSubmit, btnClear, btnModify;
    Checkbox cbm, cbf;
    CheckboxGroup cbmf;

    public StaffModify() {
        super("Modify Staff Information");

        mainTitle = new JLabel("Staff Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertSNo = new JLabel("Insert Staff Identity :");
        lblInsertSNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertSNo.setBounds(40, 70, 160, 25);
        add(lblInsertSNo);

        lblstaffid = new JLabel("Staff ID :");
        lblstaffid.setBounds(40, 100, 100, 25);
        add(lblstaffid);

        txtstaffid = new JTextField(30);
        txtstaffid.setBounds(140, 100, 160, 25);
        add(txtstaffid);

        btnSubmit = new JButton("SEARCH");
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton("CLEAR ALL");
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        btnModify = new JButton("UPDATE STAFF");
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

        lbldf1 = new JLabel("(dd-mm-yyyy)");
        lbldf1.setBounds(780, 310, 100, 25);
        add(lbldf1);

        //Professional Title...
        lblprofTitle = new JLabel("Professional Information");
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(40, 400, 300, 30);
        add(lblprofTitle);

        lbldepartment = new JLabel("Department :");
        lbldepartment.setBounds(60, 450, 100, 25);
        add(lbldepartment);

        txtdepartment = new TextArea();
        txtdepartment.setBounds(200, 450, 200, 130);
        add(txtdepartment);

        lbldoj = new JLabel("Date Of Join :");
        lbldoj.setBounds(60, 600, 100, 25);
        add(lbldoj);

        txtdoj = new JTextField(40);
        txtdoj.setBounds(200, 600, 200, 25);
        add(txtdoj);

        lbldf2 = new JLabel("(dd-mm-yyyy)");
        lbldf2.setBounds(320, 630, 100, 20);
        add(lbldf2);

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

        lblpost = new JLabel("Job Post");
        lblpost.setBounds(540, 530, 80, 25);
        add(lblpost);

        txtpost = new TextArea();
        txtpost.setBounds(660, 530, 200, 100);
        add(txtpost);

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

//    public static void main(String[] args) {
//
//        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        StaffModify staffModify = new StaffModify();
//    }

    public void actionPerformed(ActionEvent ae) {
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            txtfullname.setText("");
            txtcontact.setText("");
            txtstaffid.setText("");
            txtworkfrom.setText("");
            txtworkto.setText("");
            txtaddress.setText("");
            txtdepartment.setText("");
            txtpost.setText("");
            txtdob.setText("");
            txtdoj.setText("");
            cbmf.setSelectedCheckbox(null);

        }
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (!txtstaffid.getText().isEmpty()) {

                    Integer num = Integer.parseInt(txtstaffid.getText().trim());
                    String name, addr, contact, gender, dept, post, workf, workt, dob, doj;

                    stmt = conn.prepareStatement("SELECT * FROM staff_table WHERE staff_id=?");
                    stmt.setInt(1, num);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        name = rs.getString("staff_fullname");
                        addr = rs.getString("staff_address");
                        contact = rs.getString("staff_contact");
                        gender = rs.getString("staff_gender");
                        dept = rs.getString("staff_department");
                        post = rs.getString("staff_post");
                        workf = rs.getString("staff_workshiftfrom");
                        workt = rs.getString("staff_workshiftto");
                        dob = rs.getString("staff_dateofbirth");
                        doj = rs.getString("staff_dateofjoin");

                        if (gender.equals("M")) {
                            cbm.setState(true);
                        } else if (gender.equals("F")) {
                            cbf.setState(true);
                        }

                        txtfullname.setText(name);
                        txtaddress.setText(addr);
                        txtcontact.setText(contact);
                        txtdepartment.setText(dept);
                        txtpost.setText(post);
                        txtworkfrom.setText(workf);
                        txtworkto.setText(workt);
                        txtdob.setText(dob);
                        txtdoj.setText(doj);

                    } else {
                        JOptionPane.showMessageDialog(null, "Staff Record Not Found!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Staff ID......");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in retrieving Staff Data!!!");
            }
        }
    }

    class modify implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Integer num1 = Integer.parseInt(txtstaffid.getText().trim());
                if (num1.equals(null)) {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID...");
                } else {

                    String name1 = txtfullname.getText().trim();
                    String addr1 = txtaddress.getText().trim();
                    String contact1 = txtcontact.getText().trim();
                    String dept1 = txtdepartment.getText().trim();
                    String workf1 = txtworkfrom.getText().trim();
                    String workt1 = txtworkto.getText().trim();
                    String dob1 = txtdob.getText().trim();
                    String datedoj1 = txtdoj.getText().trim();

                    String query = "UPDATE staff_table SET staff_fullname=?, staff_address=?, staff_contact=?, "
                            + "staff_department=?, staff_gender=?, staff_workshiftfrom='" + workf1 + "', "
                            + "staff_workshiftto='" + workt1 + "', staff_dateofbirth='" + dob1 + "', staff_dateofjoin='"
                            + datedoj1 + "' WHERE staff_id=?";

                    String gender1 = "";
                    if (cbm.getState() == true) {
                        gender1 = "M";
                    }
                    if (cbf.getState() == true) {
                        gender1 = "F";
                    }

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, name1);
                    stmt.setString(2, addr1);
                    stmt.setString(3, contact1);
                    stmt.setString(4, dept1);
                    stmt.setString(5, gender1);
                    stmt.setInt(6, num1);

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(new JFrame(), "Data Modified successfully!", "Done!",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error in updating Staff Data......", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            }
        }

    }
