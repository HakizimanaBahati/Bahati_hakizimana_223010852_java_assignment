

//1. Bahati HAKIZIMANA 		223010852
//2. Olivier HABINGABIRE	223007847
//5. Dieudonne IRADUKUNDA	223015105


package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.util.DB;

public class TeacherPanel extends JPanel implements ActionListener {
    JTextField teacherIdTxt = new JTextField();
    JTextField userIdTxt = new JTextField();
    JTextField depIdTxt = new JTextField();
    JTextField hireDateTxt = new JTextField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public TeacherPanel() {
        setLayout(null);

        // Table columns
        String[] labels = { "Teacher ID", "User ID", "Department ID", "Hire Date" };
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 800, 300);

        int y = 20;
        addField("Teacher ID", teacherIdTxt, y);
        teacherIdTxt.setEditable(false);
        y += 30;
        addField("User ID", userIdTxt, y);
        y += 30;
        addField("Department ID", depIdTxt, y);
        y += 30;
        addField("Hire Date (YYYY-MM-DD)", hireDateTxt, y);

        addButtons();
        add(sp);

        loadTeachers();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    teacherIdTxt.setText(model.getValueAt(row, 0).toString());
                    userIdTxt.setText(model.getValueAt(row, 1).toString());
                    depIdTxt.setText(model.getValueAt(row, 2).toString());
                    hireDateTxt.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void addField(String lbl, JComponent txt, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 150, 25);
        txt.setBounds(180, y, 150, 25);
        add(l);
        add(txt);
    }

    private void addButtons() {
        addBtn.setBounds(400, 20, 100, 30);
        updateBtn.setBounds(400, 60, 100, 30);
        deleteBtn.setBounds(400, 100, 100, 30);
        loadBtn.setBounds(400, 140, 100, 30);

        add(addBtn);
        add(updateBtn);
        add(deleteBtn);
        add(loadBtn);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {
            if (e.getSource() == addBtn) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO teacher(userID, DepartmentID, HireDate) VALUES(?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, Integer.parseInt(userIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(depIdTxt.getText()));
                ps.setString(3, hireDateTxt.getText());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    teacherIdTxt.setText(String.valueOf(keys.getInt(1)));
                }

                JOptionPane.showMessageDialog(this, "Teacher Added!");
                loadTeachers();

            } else if (e.getSource() == updateBtn) {
                if (teacherIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a teacher to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE teacher SET userID=?, DepartmentID=?, HireDate=? WHERE TeacherID=?");
                ps.setInt(1, Integer.parseInt(userIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(depIdTxt.getText()));
                ps.setString(3, hireDateTxt.getText());
                ps.setInt(4, Integer.parseInt(teacherIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Teacher Updated!");
                loadTeachers();

            } else if (e.getSource() == deleteBtn) {
                if (teacherIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a teacher to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM teacher WHERE TeacherID=?");
                ps.setInt(1, Integer.parseInt(teacherIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Teacher Deleted!");
                loadTeachers();

            } else if (e.getSource() == loadBtn) {
                loadTeachers();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadTeachers() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM teacher");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("TeacherID"),
                        rs.getInt("userID"),
                        rs.getInt("DepartmentID"),
                        rs.getString("HireDate")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading teachers: " + ex.getMessage());
        }
    }
}
