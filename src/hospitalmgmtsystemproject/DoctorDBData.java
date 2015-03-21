/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmgmtsystemproject;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Raunak Shakya
 */
public class DoctorDBData extends JInternalFrame{

    static Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public DoctorDBData() {
        super("Display Doctor Information");
        
        Container con = getContentPane();
        
        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            String driver = "org.postgresql.Driver";
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "postgres");
            } catch (Exception e) {
                System.out.println(e);
            }

            //  Read data from a table
            stmt = conn.prepareStatement("Select * from doctor_table");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }

            //  Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));

                }

                data.addElement(row);
            }

            // rs.close();
            //stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        //  Create table with database data
        JTable table = new JTable(data, columnNames);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(115);
        table.getColumnModel().getColumn(3).setPreferredWidth(105);
        table.getColumnModel().getColumn(4).setPreferredWidth(95);
        table.getColumnModel().getColumn(5).setPreferredWidth(135);
        table.getColumnModel().getColumn(6).setPreferredWidth(130);
        table.getColumnModel().getColumn(7).setPreferredWidth(130);
        table.getColumnModel().getColumn(8).setPreferredWidth(90);
        table.getColumnModel().getColumn(9).setPreferredWidth(115);
        table.getColumnModel().getColumn(10).setPreferredWidth(115);
        
        table.setPreferredScrollableViewportSize(new Dimension(1300, 500));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        
        JPanel pn=new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        pn.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        pn.add(buttonPanel);

        con.add(pn);
        setSize(900, 400);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(50, 50);
        setLayout(new FlowLayout(FlowLayout.CENTER));

    }

        public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DoctorAdd.class.getName()).log(Level.SEVERE, null, ex);
        }

        DoctorDBData doctorDBData = new DoctorDBData();
    }
}
