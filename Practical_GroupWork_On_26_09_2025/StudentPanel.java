package com.panel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.util.DB;

public class StudentPanel extends JPanel implements ActionListener {
    JTextField studentIdTxt = new JTextField();
    JTextField userIdTxt = new JTextField();
    JTextField depIdTxt = new JTextField();
    JTextField enrollDateTxt = new JTextField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public StudentPanel() {
        setLayout(null);

        // Table columns
        String[] labels = { "StudentID", "UserID", "DepartmentID", "EnrollmentDate" };
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 800, 300);
        add(sp);

        int y = 20;
        addField("Student ID", studentIdTxt, y);
        studentIdTxt.setEditable(false);
        y += 30;
        addField("User ID", userIdTxt, y);
        y += 30;
        addField("Department ID", depIdTxt, y);
        y += 30;
        addField("Enrollment Date (YYYY-MM-DD)", enrollDateTxt, y);

        addButtons();
        loadStudents();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    studentIdTxt.setText(model.getValueAt(row, 0).toString());
                    userIdTxt.setText(model.getValueAt(row, 1).toString());
                    depIdTxt.setText(model.getValueAt(row, 2).toString());
                    enrollDateTxt.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void addField(String label, JTextField field, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 150, 25);
        field.setBounds(180, y, 150, 25);
        add(l);
        add(field);
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
                        "INSERT INTO student (UserID, DepartmentID, EnrollmentDate) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, Integer.parseInt(userIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(depIdTxt.getText()));
                ps.setString(3, enrollDateTxt.getText());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    studentIdTxt.setText(String.valueOf(keys.getInt(1)));
                }

                JOptionPane.showMessageDialog(this, "Student Added!");
                loadStudents();

            } else if (e.getSource() == updateBtn) {
                if (studentIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a student to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE student SET UserID=?, DepartmentID=?, EnrollmentDate=? WHERE StudentID=?");
                ps.setInt(1, Integer.parseInt(userIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(depIdTxt.getText()));
                ps.setString(3, enrollDateTxt.getText());
                ps.setInt(4, Integer.parseInt(studentIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Student Updated!");
                loadStudents();

            } else if (e.getSource() == deleteBtn) {
                if (studentIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a student to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE StudentID=?");
                ps.setInt(1, Integer.parseInt(studentIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Student Deleted!");
                loadStudents();

            } else if (e.getSource() == loadBtn) {
                loadStudents();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadStudents() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM student");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("StudentID"),
                        rs.getInt("UserID"),
                        rs.getInt("DepartmentID"),
                        rs.getString("EnrollmentDate")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }
    }
}
