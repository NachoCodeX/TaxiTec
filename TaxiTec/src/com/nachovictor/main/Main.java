package com.nachovictor.main;

import com.nachovictor.connection.ConnectionDB;
import com.nachovictor.connection.SessionContans;
import com.nachovictor.graphics.WindowAdmin;
import com.nachovictor.graphics.WindowLogin;
import com.nachovictor.graphics.WindowTaxista;

public class Main {
	
	public static WindowLogin wl=new WindowLogin();
	public static ConnectionDB db=new ConnectionDB();
	public static SessionContans sc;
	public static WindowAdmin wa=new WindowAdmin();
	public static WindowTaxista wt=new WindowTaxista();
	
	public static void main(String[] args) {	
		sc=new SessionContans();
		wl.setVisible(true);
	}

}
