package com.nachovictor.connection;

public class SessionContans {
	
	public static String NAME=null,LAST_NAME=null,SITE=null,PHONE_NUMBER=null,ADDRESS=null;
	public static int PK,COUNT_TAXI=0,AGE=0,ADMIN_OWNER=0;
	
	

	
	
	public static void setAdmin(int PK,String NAME,String LAST_NAME,int COUNT_TAXI){
		SessionContans.PK=PK;
		SessionContans.NAME=NAME;
		SessionContans.LAST_NAME=LAST_NAME;
		SessionContans.COUNT_TAXI=COUNT_TAXI;
		
	}
	
	public static void setTaxista(int PK,String NAME,String LAST_NAME,String SITE,String PHONE_NUMBER,String ADDRESS,int AGE,int ADMIN_OWNER){
		SessionContans.PK=PK;
		SessionContans.NAME=NAME;
		SessionContans.LAST_NAME=LAST_NAME;
		SessionContans.SITE=SITE;
		SessionContans.PHONE_NUMBER=PHONE_NUMBER;
		SessionContans.ADDRESS=ADDRESS;
		SessionContans.AGE=AGE;
		SessionContans.ADMIN_OWNER=ADMIN_OWNER;
	}
	
	
	public static void destroySession(){
		NAME=null;
		LAST_NAME=null;
		SITE=null;
		PHONE_NUMBER=null;
		ADDRESS=null;
		PK=0;
		COUNT_TAXI=0;
		AGE=0;
		ADMIN_OWNER=0;
		//Llamar a el garbage collector
		System.gc();
	}
	
	
	
	

}
