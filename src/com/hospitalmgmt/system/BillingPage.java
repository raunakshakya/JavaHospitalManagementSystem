/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Patient;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
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

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    JLabel mainTitle, lblfullname, lblpatientno, lbldoa, lbldod, lblroom, lblamount, txtdod;
    JTextField txtfullname, txtpatientno, txtdoa, txtroom, txtamount;
    JButton btnSearch, btnClear, bback;

    public BillingPage() {
        super(LayoutUtils.PATIENT_BILLING_TITLE);

        //Billing Information...
        mainTitle = new JLabel(messages.getString("patient.billing.information.title"));
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

        btnSearch = new JButton(messages.getString("common.search"));
        btnSearch.setBounds(350, 430, 120, 30);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int patientId = Integer.parseInt(txtpatientno.getText());
                Patient patient = Patient.findById(patientId);
                if (patient == null) {
                    JOptionPane.showMessageDialog(null, "Patient Record Not Found!!!");
                }
                txtfullname.setText(patient.getFullName());
                txtdoa.setText(patient.getDateOfAdmission().toString());
                txtroom.setText(patient.getRoomNumber().toString());
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

                    Long from = new java.util.GregorianCalendar(ynow, mnowF, dnow).getTime().getTime();
                    Long to = new java.util.GregorianCalendar(daddYY, daddMMF, daddd).getTime().getTime();
                    double difference = from - to;

                    Long days = Math.round((difference / (1000 * 60 * 60 * 24)));
                    txtamount.setText(String.valueOf(days * 2000));
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Error in calcuating bill!!!");
                }
            }
        });

        btnClear = new JButton("CLEAR");
        btnClear.setBounds(500, 430, 120, 30);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
            }
        });

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        String dd1 = df.format(cal.getTime());
        txtdod = new JLabel(dd1);
        txtdod.setBounds(680, 175, 200, 25);
        add(txtdod);

        setSize(1000, 540);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(200, 100);
        setLayout(null);
    }

    public void doClearTheTextFields() {
        txtfullname.setText("");
        txtpatientno.setText("");
        txtdoa.setText("");
        txtroom.setText("");
        txtamount.setText("");
    }

}
