package com.form;

import java.awt.BorderLayout;
import com.panel.*;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class SMIS extends JFrame {
	JTabbedPane tabs= new JTabbedPane();
	public SMIS (String role,int userId) {
		setTitle("School Management System");
		setSize(900,600);
		setLayout(new BorderLayout());
		if(role.equalsIgnoreCase("admin")) {
			tabs.add("Users", new UserPanel());
			tabs.add("Teachers", new TeacherPanel());
			tabs.add("Courses", new CoursePanel());
			tabs.add("Students", new StudentPanel());
			tabs.add("Marks", new MarkPanel());
			tabs.add("Department", new DepartmentPanel());



		}else if(role.equalsIgnoreCase("Teacher")){
			tabs.add("Courses", new CoursePanel());
			tabs.add("Marks", new MarkPanel());

		} else if(role.equalsIgnoreCase("Students")){
			tabs.add(" My Marks", new MarkPanel());

		} 
		add(tabs, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


	}

}
