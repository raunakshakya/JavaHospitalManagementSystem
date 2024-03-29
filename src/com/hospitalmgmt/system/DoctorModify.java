/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Doctor;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;
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

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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
        super(LayoutUtils.MODIFY_DOCTOR_TITLE);

        mainTitle = new JLabel(messages.getString("label.doctor.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertDNo = new JLabel(messages.getString("label.insert.doctor.id"));
        lblInsertDNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertDNo.setBounds(40, 70, 160, 25);
        add(lblInsertDNo);

        lbldoctorid = new JLabel(messages.getString("label.doctor.id"));
        lbldoctorid.setBounds(40, 100, 100, 25);
        add(lbldoctorid);

        txtdoctorid = new JTextField(30);
        txtdoctorid.setBounds(140, 100, 160, 25);
        add(txtdoctorid);

        btnSubmit = new JButton(messages.getString("common.search"));
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton(messages.getString("common.clear.all"));
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
            }
        });

        btnModify = new JButton(messages.getString("update.doctor.label"));
        btnModify.setBounds(540, 98, 150, 30);
        add(btnModify);

        lblsubTitle = new JLabel(messages.getString("personal.information.title"));
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 150, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(lblsubTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, LayoutUtils.LABEL_LEFT_Y_COORDINATE, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        add(txtaddress);

        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
        add(txtcontact);

        lblgender = new JLabel(messages.getString("label.gender"));
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);

        cbmf = new CheckboxGroup();
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
        cbm.setBounds(680, 240, 60, 25);
        add(cbm);
        cbf.setBounds(740, 240, 60, 25);
        add(cbf);

        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);

        txtdob = new JTextField(15);
        txtdob.setBounds(660, 280, 200, 25);
        add(txtdob);

        lbldf1 = new JLabel(messages.getString("label.date.format"));
        lbldf1.setBounds(780, 310, 100, 25);
        add(lbldf1);

        //Professional Title...
        lblprofTitle = new JLabel(messages.getString("professional.information.title"));
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(40, 400, 300, 30);
        add(lblprofTitle);

        lblspecialization = new JLabel(messages.getString("label.specialization"));
        lblspecialization.setBounds(60, 450, 100, 25);
        add(lblspecialization);

        txtspecialization = new TextArea();
        txtspecialization.setBounds(200, 450, 200, 130);
        add(txtspecialization);

        lblworkfrom = new JLabel(messages.getString("label.shift.from"));
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);

        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        add(txtworkfrom);

        lblworkto = new JLabel(messages.getString("label.shift.to"));
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);

        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        add(txtworkto);

        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(540, 530, 100, 25);
        add(lbldoj);

        txtdoj = new JTextField(40);
        txtdoj.setBounds(660, 530, 200, 25);
        add(txtdoj);

        lbldf2 = new JLabel(messages.getString("label.date.format"));
        lbldf2.setBounds(780, 560, 100, 20);
        add(lbldf2);

        btnSubmit.addActionListener(new submit());
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer doctorId = Integer.parseInt(txtdoctorid.getText().trim());
                if (doctorId == null) {
                    JOptionPane.showMessageDialog(null, "First Enter the Doctor ID...");
                }

                String fullName = txtfullname.getText().trim();
                String address = txtaddress.getText().trim();
                String contact = txtcontact.getText().trim();
                String specialization = txtspecialization.getText().trim();
                //String status = status.getText().trim();
                String shiftFrom = txtworkfrom.getText().trim();
                String shiftTo = txtworkto.getText().trim();
                String dateOfBirth = txtdob.getText().trim();
                String dateOfJoin = txtdoj.getText().trim();
                String gender = (cbm.getState() == true) ? "Male" : ((cbf.getState() == true) ? "Female" : null);

                HashMap doctorDto = new HashMap();
                doctorDto.put("fullName", fullName);
                doctorDto.put("address", address);
                doctorDto.put("contact", contact);
                //doctorDto.put("status", status);
                doctorDto.put("gender", gender);
                doctorDto.put("dateOfBirth", dateOfBirth);
                doctorDto.put("dateOfJoin", dateOfJoin);
                doctorDto.put("specialization", specialization);
                doctorDto.put("shiftFrom", shiftFrom);
                doctorDto.put("shiftTo", shiftTo);

                Doctor.update(doctorId, doctorDto);

                JOptionPane.showMessageDialog(new JFrame(), "Data Modified successfully!", "Done!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
        setLayout(null);
    }

    public void doClearTheTextFields() {
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

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(LayoutUtils.JTATTOO_APPLICATION_THEME);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DoctorModify.class.getName()).log(Level.SEVERE, null, ex);
        }
        DoctorModify doctorModify = new DoctorModify();
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!txtdoctorid.getText().isEmpty()) {
                Integer doctorId = Integer.parseInt(txtdoctorid.getText().trim());
                Doctor doctor = Doctor.findById(doctorId);
                if (doctor == null) {
                    JOptionPane.showMessageDialog(null, "Doctor Record Not Found!!!");
                }
                String fullName = doctor.getFullName();
                String address = doctor.getAddress();
                String contact = doctor.getContact();
                String gender = doctor.getGender().getName();
                String specialization = doctor.getSpecialization();
                String shiftFrom = doctor.getShiftFrom().toString();
                String shiftTo = doctor.getShiftTo().toString();
                String dateOfBirth = doctor.getDateOfBirth().toString();
                String dateOfJoin = doctor.getDateOfJoin().toString();
                switch (gender) {
                    case "Male":
                        cbm.setState(true);
                        break;
                    case "Female":
                        cbf.setState(true);
                        break;
                    default:
                        throw new RuntimeException("Incorrect gender");
                }

                txtfullname.setText(fullName);
                txtaddress.setText(address);
                txtcontact.setText(contact);
                txtspecialization.setText(specialization);
                txtworkfrom.setText(shiftFrom);
                txtworkto.setText(shiftTo);
                txtdob.setText(dateOfBirth);
                txtdoj.setText(dateOfJoin);
            } else {
                JOptionPane.showMessageDialog(null, "First Enter the Patient ID......");
            }
        }
    }

}
