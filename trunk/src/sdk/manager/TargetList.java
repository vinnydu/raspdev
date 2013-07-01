package sdk.manager;

//import java.io.File;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import raspdev.ParsConf;

public class TargetList {
	
	private ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	
	public TargetList() {
		try {
			Class.forName("org.sqlite.JDBC");
		    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+"/tools/virtual_devices.db"); 
		    Statement stat = conn.createStatement();
		    
//		    stat.executeUpdate("CREATE TABLE IF NOT EXISTS targets (id UNIQUE NOT NULL, name NOT NULL, type, kernel, path) ");
//		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('4', '2012-08-08-wheezy-armel.img', 'armel', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"armel2012-08" + File.separatorChar + "')");
//		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('5', '2012-10-28-wheezy-raspbian.img', 'raspbian', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"raspbian2012-10" + File.separatorChar + "')");
//		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('6', '2013-05-29-wheezy-armel.img', 'armel', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"armel2013-05" + File.separatorChar + "')");
//		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('7', '2013-15-25-wheezy-raspbian.img', 'raspbian', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"raspbian2013-05" + File.separatorChar + "')");
//		   
		    
		    ResultSet rs = stat.executeQuery("SELECT * FROM targets"); 
		    while (rs.next()) {
		    	ArrayList<String> row = new ArrayList<String>();
		    	System.out.println("\n---------- TARGET " + rs.getString("id") + "----------");
		    	System.out.println("id = " + rs.getString("id"));
		    	row.add(rs.getString("id"));
		    	System.out.println("name = " + rs.getString("name"));
		    	row.add(rs.getString("name"));
		     	System.out.println("type = " + rs.getString("type"));
		     	row.add(rs.getString("type"));
		    	System.out.println("kernel = " + rs.getString("kernel")); 
		    	row.add(rs.getString("kernel"));
		    	System.out.println("path = " + rs.getString("path"));
		    	row.add(rs.getString("path"));
		    	results.add(row);
		    } 
		
		    rs.close(); 
		    conn.close();
	    
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No target available.");
		} 
	}
	
	public ArrayList<ArrayList<String>> getResults() {
		return results;
	}

}
