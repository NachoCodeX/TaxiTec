package com.nachovictor.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nachovictor.connection.ConnectionDB;
import com.nachovictor.main.Main;

public class WindowLogin extends WindowBase implements ActionListener,FocusListener,WindowListener{
	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	private JLabel title;
	private JButton loginbtn,exitbtn,info;
	
	
	
	public WindowLogin() {
		init();
		setWindow();
		addComponents();
	}
	
	private Image getImage(String res){

		
		try {
			Image img = ImageIO.read(getClass().getResource("resources/"+res));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	protected void init() {
		//DB
		
		
		mainPanel=new JPanel(null);
		mainPanel.setBounds(0, 0, W, H);
		
		//titulo
		
		title=new JLabel("TAXI TEC");
		
		title.setBounds((W/2-150), 0, 350, 100);
		title.setFont(new Font("Arial", Font.BOLD, 70));
		//title.setForeground(new Color(0,200,0));
		
		//Username field
		username=new JTextField("Usuario");
		
		username.setBounds((W/2-200/2),(H/2-150/2), 200, 40);
		username.setForeground(Color.GRAY);
		username.setFont(new Font("Arial", Font.BOLD, 30));
		username.setToolTipText("User");
		username.setName("username");
		username.addFocusListener(this);
		
		
		//Passwrod field
		password=new JPasswordField("Password");
		password.setBounds( ((W/2)-200/2), ((H/2)-15), 200, 40);
		password.setFont(new Font("Arial", Font.BOLD, 30));
		password.setToolTipText("Password");
		password.setName("password");
		password.addFocusListener(this);
		//Login button
		loginbtn=new JButton("Iniciar sesión");
		loginbtn.setBounds((W/2-100),(H/2)+40, 200, 40);
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setBackground(new Color(0, 200, 0));
		loginbtn.addActionListener(this);
		loginbtn.setToolTipText("Login");
		loginbtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		loginbtn.setIcon(new ImageIcon(getClass().getResource("/resources/rsz_log.png")));
		loginbtn.setBorderPainted(false);
		loginbtn.setFocusPainted(false);
		/* Exit Button
		exitbtn=new JButton("Salir");
		exitbtn.setBounds((W/2-100),(H/2)+100, 200, 40);
		exitbtn.setForeground(Color.WHITE);
		exitbtn.setBackground(new Color(200, 0, 0));
		exitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitbtn.setToolTipText("Exit");
		exitbtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));*/
		
		
		
		this.addWindowListener(this);
	}

	@Override
	protected void setWindow() {
		this.setUndecorated(true);
		this.setLayout(null);
		this.setTitle("TAXI TEC");
		this.setSize(W,H);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	@Override
	protected void addComponents() {
		// TODO Auto-generated method stub
		mainPanel.add(title);
		mainPanel.add(username);
		mainPanel.add(password);
		mainPanel.add(loginbtn);
		//mainPanel.add(exitbtn);
		this.add(mainPanel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!ConnectionDB.getUsers(username.getText(),getDecrypt())){
			username.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
			password.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
			JOptionPane.showMessageDialog(null, "Datos incorrectos!","LOGIN",JOptionPane.ERROR_MESSAGE);
			
		}else{
			username.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			
			//System.out.println(Main.sc.NAME);
			if(Main.sc.ACCOUNT_TYPE.equals("ADMINISTRADOR")){
				Main.wa.setSession(Main.sc);
				Main.wa.open();
				Main.wl.setVisible(false);
				Main.wa.setVisible(true);
			}else if(Main.sc.ACCOUNT_TYPE.equals("PROPIETARIO")){
				
				
			}else{
				System.out.println(Main.sc.NAME);
				Main.wt.setSession(Main.sc);
				Main.wt.open();
				Main.wl.setVisible(false);
				Main.wt.setVisible(true);
				
			}
			
			
			
			
		}
		
	}


	@Override
	public void focusGained(FocusEvent e) {
		
		if(e.getComponent().getName().equals("username") && username.getText().equals("Usuario")){
			username.setText("");
			username.setForeground(Color.BLACK);
		}
		
		if(e.getComponent().getName().equals("password") && getDecrypt().equals("Password")){
			password.setText("");
	
		}
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		
		if(e.getComponent().getName().equals("username") && username.getText().equals("")){
			username.setText("Usuario");
			username.setForeground(Color.GRAY);
		}
		
		if(e.getComponent().getName().equals("password") && getDecrypt().equals("")){
			password.setText("Password");
		}
		
	
	}
	
	private String getDecrypt(){
		char []pass=password.getPassword();
		
		String  passtr=new String(pass);
		return passtr;
	}


	@Override
	public void windowOpened(WindowEvent e) {
		loginbtn.requestFocus();
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	

}
