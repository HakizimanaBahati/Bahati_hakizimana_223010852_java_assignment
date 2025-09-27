package com.panel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.util.DB;

public class MarkPanel extends JPanel implements ActionListener {
    JTextField markIdTxt = new JTextField();
    JTextField studentIdTxt = new JTextField();
    JTextField courseIdTxt = new JTextField();
    JTextField teacherIdTxt = new JTextField();
    JTextField marksObtainedTxt = new JTextField();
    JTextField gradeTxt = new JTextField();
    JTextField examDateTxt = new JTextField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public MarkPanel() {
        setLayout(null);

        // Table columns
        String[] columns = { "MarkID", "StudentID", "CourseID", "TeacherID", "MarksObtained", "Grade", "ExamDate" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 250, 1000, 300);
        add(sp);

        int y = 20;
        addField("Mark ID", markIdTxt, y);
        markIdTxt.setEditable(false);
        y += 30;
        addField("Student ID", studentIdTxt, y);
        y += 30;
        addField("Course ID", courseIdTxt, y);
        y += 30;
        addField("Teacher ID", teacherIdTxt, y);
        y += 30;
        addField("Marks Obtained", marksObtainedTxt, y);
        y += 30;
        addField("Grade", gradeTxt, y);
        y += 30;
        addField("Exam Date (YYYY-MM-DD)", examDateTxt, y);

        addButtons();
        loadMarks();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    markIdTxt.setText(model.getValueAt(row, 0).toString());
                    studentIdTxt.setText(model.getValueAt(row, 1).toString());
                    courseIdTxt.setText(model.getValueAt(row, 2).toString());
                    teacherIdTxt.setText(model.getValueAt(row, 3).toString());
                    marksObtainedTxt.setText(model.getValueAt(row, 4).toString());
                    gradeTxt.setText(model.getValueAt(row, 5).toString());
                    examDateTxt.setText(model.getValueAt(row, 6).toString());
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
        addBtn.setBounds(450, 20, 100, 30);
        updateBtn.setBounds(450, 60, 100, 30);
        deleteBtn.setBounds(450, 100, 100, 30);
        loadBtn.setBounds(450, 140, 100, 30);

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
                        "INSERT INTO marks (StudentID, CourseID, TeacherID, MarksObtained, Grade, ExamDate) VALUES (?, ?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, Integer.parseInt(studentIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(courseIdTxt.getText()));
                ps.setInt(3, Integer.parseInt(teacherIdTxt.getText()));
                ps.setDouble(4, Double.parseDouble(marksObtainedTxt.getText()));
                ps.setString(5, gradeTxt.getText());
                ps.setString(6, examDateTxt.getText());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    markIdTxt.setText(String.valueOf(keys.getInt(1)));
                }

                JOptionPane.showMessageDialog(this, "Mark Added!");
                loadMarks();

            } else if (e.getSource() == updateBtn) {
                if (markIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a mark to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE marks SET StudentID=?, CourseID=?, TeacherID=?, MarksObtained=?, Grade=?, ExamDate=? WHERE MarkID=?");
                ps.setInt(1, Integer.parseInt(studentIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(courseIdTxt.getText()));
                ps.setInt(3, Integer.parseInt(teacherIdTxt.getText()));
                ps.setDouble(4, Double.parseDouble(marksObtainedTxt.getText()));
                ps.setString(5, gradeTxt.getText());
                ps.setString(6, examDateTxt.getText());
                ps.setInt(7, Integer.parseInt(markIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Mark Updated!");
                loadMarks();

            } else if (e.getSource() == deleteBtn) {
                if (markIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a mark to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM marks WHERE MarkID=?");
                ps.setInt(1, Integer.parseInt(markIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Mark Deleted!");
                loadMarks();

            } else if (e.getSource() == loadBtn) {
                loadMarks();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadMarks() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM marks");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("MarkID"),
                        rs.getInt("StudentID"),
                        rs.getInt("CourseID"),
                        rs.getInt("TeacherID"),
                        rs.getDouble("MarksObtained"),
                        rs.getString("Grade"),
                        rs.getString("ExamDate")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading marks: " + ex.getMessage());
        }
    }
}
