package com.nachovictor.graphics;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nachovictor.connection.SessionContans;

public class WindowTaxista  extends WindowBase{
	private static final long serialVersionUID = 1L;
	private SessionContans sc;
	
	private JLabel nl;
	
	
	
	@Override
	protected void init() {
		mainPanel=new JPanel();
		nl=new JLabel("Hola "+this.sc.NAME);
	}
	
	
	private void setComponents(){
		nl.setBounds(10, 100, 200, 30);
		nl.setFont(new Font("Arial", Font.BOLD, 20));
	}

	@Override
	protected void setWindow() {
		
		
		this.setTitle("Taxista - "+sc.NAME);
		this.setSize(W*2,H+200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}

	@Override
	protected void addComponents() {
		mainPanel.add(nl);
		
		this.add(mainPanel);
		
	}
	
	public void open(){
		init();
		setWindow();
		setComponents();
		addComponents();
	}
	
	public void setSession(SessionContans sc){
		this.sc=sc;
	}

}
