package com.nachovictor.connection;

import java.sql.ResultSet;

public class SessionContans {
	
	public String NAME=null,LAST_NAME=null,SITE=null,PHONE_NUMBER=null,ADDRESS=null,ACCOUNT_TYPE=null;
	public  int PK,AGE=0,ADMIN_OWNER=0;
	
	public ResultSet rs=null;
	
	
	public void setAdmin(int PK,String NAME,String LAST_NAME){
		this.PK=PK;
		this.NAME=NAME;
		this.LAST_NAME=LAST_NAME;
		
	}
	
	public  void setTaxista(int PK,String NAME,String LAST_NAME,String SITE,String PHONE_NUMBER,String ADDRESS,int AGE,int ADMIN_OWNER){
		this.PK=PK;
		this.NAME=NAME;
		this.LAST_NAME=LAST_NAME;
		this.SITE=SITE;
		this.PHONE_NUMBER=PHONE_NUMBER;
		this.ADDRESS=ADDRESS;
		this.AGE=AGE;
		this.ADMIN_OWNER=ADMIN_OWNER;
	}
	
	public void setPropietario(int PK,String NAME,String LAST_NAME){
		this.PK=PK;
		this.NAME=NAME;
		this.LAST_NAME=LAST_NAME;
	}
	
	
	public  void destroySession(){
		System.gc();
		NAME=null;
		LAST_NAME=null;
		SITE=null;
		PHONE_NUMBER=null;
		ADDRESS=null;
		PK=0;
		AGE=0;
		ADMIN_OWNER=0;
		ACCOUNT_TYPE=null;
		rs=null;
		//Llamar a el garbage collector
		System.gc();
	}
	
	
	
	
	
	

}
