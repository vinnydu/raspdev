package sdk.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import raspdev.ParsConf;

public class RVDStarter {

	
	public RVDStarter (String[] args) {
		
		if (args.length == 3 && (args[1].equals("-i") || args[1].equals("--id"))) {
			try {
				Class.forName("org.sqlite.JDBC");
			    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+"/tools/virtual_devices.db"); 
			    Statement stat = conn.createStatement();
				
			    ResultSet rs = stat.executeQuery("SELECT * FROM devices WHERE id = '" + args[2] + "'"); 
			    rs.next();
			    rs.getString("id");  //Generate exception if select returns nothing
			    
			    String qemuPath = null;
			    String[] arguments = null;
			    
				if (System.getProperty("os.name").startsWith("Windows")) {
			        // includes: Windows Vista, Windows XP, Windows 7, Windows 8
					System.out.println("Launching Qemu on Windows system.");
					//qemuPath = "..\\qemu\\qemu-1.5.0-win32\\qemu-system-arm.exe";
					qemuPath = ParsConf.getConfig()+"\\qemu\\qemu-1.5.0-win32\\qemu-system-arm.exe";
			    	arguments = new String[] {
			    			"cmd",
			    			"/c",
			    			"start",
			    			qemuPath, 
			    			"-kernel", 
			    			//".." + File.separatorChar + "kernels" + File.separatorChar + "kernel-qemu",
			    			ParsConf.getConfig()+ File.separatorChar + "kernels" + File.separatorChar + "kernel-qemu",
			    			"-cpu",
			    			"arm1176",
			    			"-m",
			    			"256",
			    			"-M", 
			    			"versatilepb",
			    			"-no-reboot",
			    			"-serial",
			    			"stdio",
			    			"-append",
			    			"root=/dev/sda2 panic=1 console=ttyS0",
			    			"-hda",
			    			rs.getString("path") + File.separatorChar + rs.getString("name"),
			    			"-redir",
			    			"tcp:2222::22"
			   		};
					
			    } else if(System.getProperty("os.name").startsWith("Linux")){
			    	// includes: Linux 64bit
			    	System.out.println("Launching Qemu on Linux system.");
			    	//qemuPath = "../qemu/qemu-1.4.1-Linux64/arm-softmmu/qemu-system-arm";
			    	qemuPath = ParsConf.getConfig()+"/qemu/qemu-1.4.1-Linux64/arm-softmmu/qemu-system-arm";
			    	arguments = new String[] {
		    				qemuPath, 
		    				"-kernel", 
		    				//".." + File.separatorChar + "kernels" + File.separatorChar + "kernel-qemu",
		    				ParsConf.getConfig()+"/kernels/kernel-qemu",
		    				"-cpu",
		    				"arm1176",
		    				"-m",
		    				"256",
		    				"-M", 
		    				"versatilepb",
		    				"-no-reboot",
		    				"-serial",
		    				"stdio",
		    				"-append",
		    				"root=/dev/sda2 panic=1",
		    				"-hda",
		    				rs.getString("path") + File.separatorChar + rs.getString("name"),
		    				"-redir",
		    				"tcp:2222::22"
		    		};
			    	
			    } else if(System.getProperty("os.name").startsWith("Mac OS X")){
			    	// includes: All Mac OS
			    	System.out.println("Launching Qemu on Mac OS system.");
			    	qemuPath = "qemu-system-arm";
			    	
			    	arguments = new String[] {
		    				qemuPath, 
		    				"-kernel", 
		    				//".." + File.separatorChar + "kernels" + File.separatorChar + "kernel-qemu",
		    				ParsConf.getConfig()+"/kernels/kernel-qemu",
		    				"-cpu",
		    				"arm1176",
		    				"-m",
		    				"256",
		    				"-M", 
		    				"versatilepb",
		    				"-no-reboot",
		    				"-serial",
		    				"stdio",
		    				"-append",
		    				"root=/dev/sda2 panic=1",
		    				"-hda",
		    				rs.getString("path") + File.separatorChar + rs.getString("name"),
		    				"-redir",
		    				"tcp:2222::22"
		    		};
			    }
				
				Runtime.getRuntime().exec(arguments);
				
				System.out.println("Device " + rs.getString("name") + " started.");
			
			    rs.close();
			    conn.close();
			} catch (IOException e) {
				e.printStackTrace();
		    } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("No device with id: " + args[2]);
			}
		}
//		else {
//			new Help("help/start");
//		}
//		
	}
	
}
