package com.loaon;



	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.*;

	public class AmountRequestedCalculator implements ActionListener {
		JFrame frame;
		JLabel amountLabel = new JLabel("Amount requested");
		JLabel durationLabel = new JLabel("Duration(year)");
		JLabel totalLabel = new JLabel("Total to return");

		JTextField amountField = new JTextField();
		JTextField durationField = new JTextField();
		JTextField totalField = new JTextField();

		JButton calculateBtn = new JButton("Calculate");


		AmountRequestedCalculator() {
			createWindow();
			setLocationAndSize();
			addComponentsToFrame();
			addActionEvent();
		}

		private void createWindow() {
			frame = new JFrame();
			frame.setTitle("===Amount_requestedCalculator===");
			frame.setBounds(100, 100, 400, 300);
			frame.getContentPane().setLayout(null);
			frame.getContentPane().setBackground(Color.green);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
		}

		private void setLocationAndSize() {
			amountLabel.setBounds(30, 30, 150, 30);
			amountField.setBounds(200, 30, 150, 30);

			durationLabel.setBounds(30, 80, 150, 30);
			durationField.setBounds(200, 80, 150, 30);

			totalLabel.setBounds(30, 130, 150, 30);
			totalField.setBounds(200, 130, 150, 30);
			totalField.setEditable(false);

			calculateBtn.setBounds(120, 190, 150, 40);
		}

		private void addComponentsToFrame() {
			frame.add(amountLabel);
			frame.add(durationLabel);
			frame.add(totalLabel);

			frame.add(amountField);
			frame.add(durationField);
			frame.add(totalField);

			frame.add(calculateBtn);
		}

		private void addActionEvent() {
			calculateBtn.addActionListener(this);
		}


		public void actionPerformed(ActionEvent e) {
			try {
				double amount = Double.parseDouble(amountField.getText());
				int years = Integer.parseInt(durationField.getText());


				double interestRate = 0.006;
				double total = amount + (amount * interestRate * years);

				totalField.setText(String.format("%.0f", total));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
			}
		}

		public static void main(String[] args) {
			new AmountRequestedCalculator();
		}


	}


