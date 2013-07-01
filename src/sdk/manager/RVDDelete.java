package sdk.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

import raspdev.ParsConf;

public class RVDDelete {
	
	public RVDDelete(String[] args) {
		
		if (args.length == 3 && (args[1].equals("-i") || args[1].equals("--id"))) {
			try {
				Class.forName("org.sqlite.JDBC");
			    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+"/tools/virtual_devices.db"); 
			    Statement stat = conn.createStatement();
				
			    ResultSet rs = stat.executeQuery("SELECT * FROM devices WHERE id = '" + args[2] + "'"); 
			    rs.next();
			    rs.getString("id");  //Generate exception if select returns nothing
			    System.out.println("Deleting device.");

			    try {
					FileUtils.deleteDirectory(new File(rs.getString("path")));
				} catch (IOException e) {
					e.printStackTrace();
				}
	
			    stat.executeUpdate("DELETE FROM devices WHERE id = '" + args[2] +"'");
			    System.out.println("Device deleted.");
			
			    rs.close();
			    conn.close();
		    
		    } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("No device with id: " + args[2]);
			}
		} 
//		else {
//			new Help("help/delete");
//		}
	}

}