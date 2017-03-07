package com.nachovictor.main;

import com.nachovictor.connection.ConnectionDB;
import com.nachovictor.connection.SessionContans;
import com.nachovictor.graphics.WindowAdmin;
import com.nachovictor.graphics.WindowLogin;

public class Main {
	
	public static WindowLogin wl=new WindowLogin();
	public static ConnectionDB db=new ConnectionDB();
	public static SessionContans sc=new SessionContans();
	public static WindowAdmin wa=new WindowAdmin();
	
	public static void main(String[] args) {
		
		
		
		wl.setVisible(true);
	}

}
