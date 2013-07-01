package sdk.manager;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.apache.commons.io.FileUtils;

import raspdev.ParsConf;

public class RVDCreation {
	
	private String actualTarget;
	private String nameDB="", typeDB="", resolutionDB="600x480", 
			pathDB = System.getProperty("user.home") + File.separatorChar + "raspdevDevices" + File.separatorChar, 
			targetDB="", sdcardDB="1GB";
	
	
	private enum ActionOption {
	    c, sdcard, n, name, p, path, r, resolution, t ,target, defCommand;
	}
	
	
	public RVDCreation(String[] args) {
		
		if (args.length > 4 && settingValue(args)) {
			
			int i = 0;
			boolean iSetted = false;
			
		    try {
				Class.forName("org.sqlite.JDBC");
			    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar+"virtual_devices.db"); 
			    Statement stat = conn.createStatement(); 
			    stat.executeUpdate("CREATE TABLE IF NOT EXISTS devices (id UNIQUE NOT NULL, name NOT NULL, type, resolution, path, target, sdcard) ");
			    
			    System.out.println("Creating device...");
			    pathDB = pathDB + nameDB;
			    while (!iSetted) {
			    	try {
			    		stat.executeUpdate("INSERT INTO devices (id, name, type, resolution, path, target, sdcard) VALUES ('"+ i +
			    				"', '"+ nameDB +"', '"+ typeDB +"', '"+ resolutionDB + "', '"+ pathDB +"', '"+ targetDB +"', '"+ sdcardDB +"')");
			    	} catch (SQLException e) {
			    		i++;
			    		continue;
			    	}
			    	iSetted = true;
			    }
			    
			    try {
			    	new File(pathDB).mkdirs();
					FileUtils.copyFile(new File(actualTarget), new File(pathDB + File.separatorChar + nameDB));
				} catch (IOException e) {
					e.printStackTrace();
				}

			    System.out.println("Virtual device correctly created.");
			    conn.close();
		    
		    } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} 
//		else {
//			new Help("help/creation");
//		}
		
	}
	
	
	public boolean settingValue(String[] args) {
	
		boolean done = true;
		String value = null;
		for (int k=1; k<args.length; k=k+2) {
			ActionOption actionOption = null;
			try {
				value = args[k+1];
				actionOption = ActionOption.valueOf(args[k].substring(args[k].lastIndexOf("-")+1));
			} catch (IllegalArgumentException argumentException) {
				actionOption = ActionOption.defCommand;
			}
	
			switch(actionOption) {
			    case c:
			    case sdcard:
			    	sdcardDB = value;
			    break;
			    
			    case n:
			    case name:
			    	nameDB = value;
			    break;
			    
			    case p:
			    case path:
			    	pathDB = value;
			    break;
			        
			    case r:
			    case resolution:
			    	resolutionDB = value;
			    break;
			    
			    case t:
			    case target:
			    	
			    	try {
			    		Class.forName("org.sqlite.JDBC");
			    		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar+"virtual_devices.db"); 
			    		Statement stat = conn.createStatement();
			    		ResultSet rs = stat.executeQuery("SELECT * FROM targets WHERE id = '" + value + "'"); 
					    rs.next();
					    typeDB = rs.getString("type");  //Generate exception if select returns nothing
					    actualTarget = rs.getString("path") + rs.getString("name"); //Target to copy
					    
					    targetDB = value;
					    rs.close(); 
					    conn.close();
					   
			    	} catch (SQLException e){
			    		System.out.println("Target not found.");
			    		done = false;
			    		break;
			    	} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
			    break;
			        
			    default:
			    	done = false;
				break;
			}
		}
		if (nameDB.isEmpty() || targetDB.isEmpty())
			done = false;
		
		return done;
	}
}





