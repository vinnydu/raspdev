package sdk.manager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import raspdev.ParsConf;

public class RVDList {
	
	private ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	
	public RVDList() {
		try {
			Class.forName("org.sqlite.JDBC");
		    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar+"virtual_devices.db"); 
		    Statement stat = conn.createStatement();
		    
		    ResultSet rs = stat.executeQuery("SELECT * FROM devices"); 
		    while (rs.next()) {
		    	ArrayList<String> row = new ArrayList<String>();
		    	System.out.println("\n---------- DEVICE " + rs.getString("id") + "----------");
		    	System.out.println("id = " + rs.getString("id"));
		    	row.add(rs.getString("id"));
		    	System.out.println("name = " + rs.getString("name"));
		    	row.add(rs.getString("name"));
		    	System.out.println("type = " + rs.getString("type")); 
		    	row.add(rs.getString("type"));
		    	System.out.println("resolution = " + rs.getString("resolution")); 
		    	row.add(rs.getString("resolution"));
		    	System.out.println("path = " + rs.getString("path")); 
		    	row.add(rs.getString("path"));
		    	System.out.println("target = " + rs.getString("target")); 
		    	row.add(rs.getString("target"));
		    	System.out.println("sdcard = " + rs.getString("sdcard"));
		    	row.add(rs.getString("sdcard"));
		    	results.add(row);
		    } 
		
		    rs.close(); 
		    conn.close();
	    
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No device available.");
		} 
	}

	public ArrayList<ArrayList<String>> getResults() {
		return results;
	}

}
