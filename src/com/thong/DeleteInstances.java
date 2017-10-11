/**
 * 
 */
package com.thong;

import java.sql.CallableStatement; 

import java.sql.Connection; 

import java.sql.DriverManager; 

import java.sql.PreparedStatement; 

import java.sql.ResultSet; 

/**
 * @author Administrator
 *
 */
public class DeleteInstances {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeleteInstances deleteInstances = new DeleteInstances(); 
		deleteInstances.run(); 
	}
	
	private void run() { 
	
	System.out.println("Running"); 

	try 
	{ 
	
	Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance(); 
	String url = "jdbc:db2://bp3-pcadv-856.bp-3.lan:50000/BPMDB";
	String userid = "devdbusr";
	String password = "SohJdmkR";
	Connection con = DriverManager.getConnection(url, userid, password);
	
	PreparedStatement queryStmt = con.prepareStatement(
	"SELECT BPD_INSTANCE_ID FROM DEVDBUSR.LSW_BPD_INSTANCE where BPD_NAME = 'Test BPD' and EXECUTION_STATUS = 1");
	
	CallableStatement deleteStmt = con.prepareCall(
	"{CALL LSW_BPD_INSTANCE_DELETE(?)}"); 
	
	ResultSet rs = queryStmt.executeQuery(); 

	while(rs.next()) 
	{ 

	int bpdId = rs.getInt("BPD_INSTANCE_ID"); 
	System.out.println(
	"Deleting BPD: ID = " + bpdId); deleteStmt.setInt(1, bpdId); deleteStmt.execute(); 
	} 
	} 

	catch (Exception e) { 
		e.printStackTrace(); 
	} 
	
	} 
	

}
