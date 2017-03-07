package com.nachovictor.graphics;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nachovictor.connection.SessionContans;

public class WindowAdmin extends WindowBase{
	private static final long serialVersionUID = 1L;
	
	private JLabel lname;
	private JButton btn;
	
	public WindowAdmin() {
		init();
		setWindow();
		addComponents();
	}
	
	@Override
	protected void init() {
		mainPanel=new JPanel(null);
		
		lname=new JLabel("Bienvenido "+SessionContans.NAME);
		lname.setBounds(100, 300, 300, 100);
		lname.setFont(new Font("Arial", Font.BOLD, 25));
		
	}

	@Override
	protected void setWindow() {
		this.setTitle("Administrador- "+SessionContans.NAME);
		this.setSize(W*2,H+200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	
		
	}

	@Override
	protected void addComponents() {
		mainPanel.add(lname);
		
		this.add(mainPanel);
		
	}

}
