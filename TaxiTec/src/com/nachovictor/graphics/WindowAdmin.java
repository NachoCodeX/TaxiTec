package com.nachovictor.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.nachovictor.connection.ConnectionDB;
import com.nachovictor.connection.SessionContans;
import com.nachovictor.main.Main;

public class WindowAdmin extends WindowBase implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private SessionContans sc;
	private JButton []btn;
	private JPanel menu;
	private JTable table;
	private JScrollPane pane;
	
	public void open(){
		init();
		setWindow();
		addComponents();
	}
	
	public void setSession(SessionContans sc){
		this.sc=sc;
		System.out.println(this.sc.NAME);
	}
	
	@Override
	protected void init() {
		mainPanel=new JPanel(null);
		mainPanel.setBounds(0, 0, W*2, H+200);
		
		
		String []names={"Propietarios","Choferes","Vehiculos","Informacion","Salir"};
		String [] path={"person","chofi","carr","config","exit"};
		menu=new JPanel(new GridLayout(1,names.length));
		
		menu.setBackground(Color.black);
		menu.setBounds(0, 0, W*2, 100);
		
		btn=new JButton[5];
		for (int i = 0; i < names.length; i++) {
			btn[i]=new JButton(names[i]);
			btn[i].setActionCommand(names[i]);
			btn[i].addActionListener(this);
			btn[i].setBackground(new Color(0, 200, 0));
			btn[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(new Font("Arial", Font.BOLD, 15));
			btn[i].setIcon(new ImageIcon(getClass().getResource("/resources/"+path[i]+".png")));
			btn[i].setFocusPainted(false);
			menu.add(btn[i]);
		}
		
		
		
		
	}

	@Override
	protected void setWindow() {
		this.setTitle("Administrador-"+sc.NAME);
		this.setSize(W*2,H+200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	
		
	}

	@Override
	protected void addComponents() {
		mainPanel.add(menu);
		this.add(mainPanel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(":D");
		switch (e.getActionCommand()) {
			case "Salir":{
				Main.wl=new WindowLogin();
				Main.wl.setVisible(true);
				this.setVisible(false);
				Main.sc.destroySession();
				
				break;
			}
			
			case "Propietarios":{
				
				table=new JTable(buildTable(ConnectionDB.getAllOwners()),buildColumnName(ConnectionDB.getAllOwners()));
				
				table.setEnabled(false);
				table.getTableHeader().setReorderingAllowed(false);
				pane=new JScrollPane(table);
				pane.setBounds(0, 100, W*2, 300);
				mainPanel.add(pane);
				effectClick("Propietarios");
				break;
			}
			case "Choferes":{
			
				table=new JTable(buildTable(ConnectionDB.getAllTaxistas()),buildColumnName(ConnectionDB.getAllTaxistas()));
				table.getTableHeader().setReorderingAllowed(false);
				pane=new JScrollPane(table);
				pane.setBounds(0, 100, W*2, 300);
				mainPanel.add(pane);
				effectClick("Choferes");
				break;
			}
			case "Vehiculos":{break;}
			case "Informacion":{break;}
			
			
			
			
			
		}
		
	}
	
	private void effectClick(String name){
		
		for (int i = 0; i < btn.length; i++) {
			
			if(btn[i].getActionCommand().equals(name)){btn[i].setBackground(new Color(0, 150, 0));}
			else {btn[i].setBackground(new Color(0, 200, 0));}
		}
	}
	
	
	private Vector<String> buildColumnName(ResultSet rs){
		try {
			ResultSetMetaData metaData=rs.getMetaData();
			
			Vector<String>columnNames=new Vector<String>();
			
			int column=metaData.getColumnCount();
			
			for (int i = 1; i <column; i++) {
				columnNames.add(metaData.getColumnName(i));
			}
			
			
			
			return columnNames;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Vector<Vector<Object>> buildTable(ResultSet rs){
		try {
			ResultSetMetaData metaData=rs.getMetaData();
			
			
			int column=metaData.getColumnCount();
			
			
			
			Vector<Vector<Object>>data=new Vector<>();
			
			
			while(rs.next()){
				Vector<Object>vector=new Vector<>();
				for (int i = 1; i < column; i++) {
					vector.add(rs.getObject(i));
				}
				data.add(vector);
			}
			
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
