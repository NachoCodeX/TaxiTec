package com.nachovictor.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nachovictor.main.Main;

public class ConnectionDB {
	
	private final String USER="nacho",PASSWORD="123456",DBNAME="taxitec";
	private static Connection con=null;
	private static Statement st=null;

	public ConnectionDB() {
		
		
		connect();
	}
	
	
	private void connect(){
		try {
			//USAR DRIVER
			Class.forName("org.postgresql.Driver");
			//HACER LA CONEXION A LA BASE DE DATOS
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+DBNAME,USER,PASSWORD);
			
			st=con.createStatement();
			/*
			String qs="SELECT * FROM USERS";
			rs=st.executeQuery(qs);*/
			
			
		/*	while(rs.next()){
				System.out.println("PK: "+rs.getString("ID")+"\nUSERNAME: "+rs.getString("USERNAME")+
						"\nPASSWORD: "+rs.getString("PASSWORD")+"\nACCOUNT_TYPE: "+rs.getString("ACCOUNT_TYPE"));
			}*/
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			
		}
	}
	
	
	
	public static boolean getUsers(String username,String password){
		String qs="SELECT * FROM USERS";
		boolean flag=false;
		//WHERE USERNAME="+username+" AND "+"PASSWORD="+password
		ResultSet rs=null;
	
		try {
			
			rs=st.executeQuery(qs);
			
			
			while(rs.next() && !flag){	
				if(username.equals(rs.getString("USERNAME")) && password.equals(rs.getString("PASSWORD"))){
					flag=true;
					if(rs.getString("ACCOUNT_TYPE").equals("ADMINISTRADOR")){
						getAdmin(rs.getString("ID"));
						
					}else{
						//PASS
					}
					
					return flag;
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	private static void getAdmin(String id){
		System.out.println("ID: "+id);
		String qs="SELECT * FROM ADMIN WHERE USER_ID = "+id;

		ResultSet rs=null;
		
		try {
			
			rs=st.executeQuery(qs);
			if(rs.next()){
				SessionContans.setAdmin(rs.getInt("ADMIN_ID"), rs.getString("NAME"), rs.getString("LAST_NAME"), rs.getInt("COUNT_TAXIS"));
				System.out.println(SessionContans.NAME);
				Main.wl.dispose();
				Main.wa.setVisible(true);
				rs.close();
			}
			
			
			//System.out.println(rs.getString("NAME"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	

	public static void getAllTaxistas(){
		
	}
	
	
	
	
}
