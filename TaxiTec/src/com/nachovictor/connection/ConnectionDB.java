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
	private static  Statement st=null;
	

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
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			
		}
	}
	
	
	
	public static boolean getUsers(String username,String password){
		String qs="SELECT * FROM USERS";
		
		boolean flag=false;
		
		ResultSet rs=null;
	
		try {	
			rs=st.executeQuery(qs);
			
			
			
			while(rs.next() && !flag){	
				
				if(username.equals(rs.getString("USERNAME")) && password.equals(rs.getString("PASSWORD"))){
					String type=rs.getString("ACCOUNT_TYPE");
					flag=true;
					if(type.equals("ADMINISTRADOR")){
						
						Main.sc.ACCOUNT_TYPE="ADMINISTRADOR";
						getAdmin(rs.getString("ID"));
						
					}else if(type.equals("PROPIETARIO")){
						Main.sc.ACCOUNT_TYPE="PROPIETARIO";
						getOwner(rs.getString("ID"));
					}else{
						Main.sc.ACCOUNT_TYPE="CHOFER";
						getTaxiDriver(rs.getString("ID"));
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
				Main.sc.setAdmin(rs.getInt("ADMIN_ID"), rs.getString("NAME"), rs.getString("LAST_NAME"));	
				rs.close();
				
			}
			
			//System.out.println(rs.getString("NAME"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void getOwner(String id){
		String qs="SELECT * FROM OWNER WHERE USER_ID="+id;
		ResultSet rs=null;
		
		try {
			rs=st.executeQuery(qs);
			if(rs.next()){
				Main.sc.setPropietario(
						rs.getInt("OWNER_ID"),
						rs.getString("NAME"),
						rs.getString("LAST_NAME"));
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void getTaxiDriver(String id){
		String qs="SELECT * FROM TAXI_DRIVER WHERE USER_ID="+id;
		ResultSet rs=null;
		
		try {
			rs=st.executeQuery(qs);
			
			if(rs.next()){
				Main.sc.setTaxista(rs.getInt("TAXI_DRIVER_ID")
						, rs.getString("NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("SITE"), 
						rs.getString("PHONE_NUMBER"),
						rs.getString("ADDRESS"), 
						rs.getInt("AGE"),
						rs.getInt("ADMIN_OWNER"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static ResultSet getAllOwners(){
		String qs="SELECT * FROM OWNER";
		ResultSet rs=null;
		try {
			rs=st.executeQuery(qs);
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
		
		
	}
	
	public static ResultSet getAllTaxistas(){
		String qs="SELECT * FROM TAXI_DRIVER";
		ResultSet rs=null;
		try {
			rs=st.executeQuery(qs);
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	
	
	
}
