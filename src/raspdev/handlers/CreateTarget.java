package raspdev.handlers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import raspdev.ParsConf;

public class CreateTarget extends AbstractHandler{

	public CreateTarget() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Raspdev",
				"Targets created");
		try {
			Class.forName("org.sqlite.JDBC");
		    Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar+"virtual_devices.db"); 
		    Statement stat = conn.createStatement();
		    stat.executeUpdate("CREATE TABLE IF NOT EXISTS targets (id UNIQUE NOT NULL, name NOT NULL, type, kernel, path) ");
		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('4', '2012-08-08-wheezy-armel.img', 'armel', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"armel2012-08" + File.separatorChar + "')");
		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('5', '2012-10-28-wheezy-raspbian.img', 'raspbian', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"raspbian2012-10" + File.separatorChar + "')");
		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('6', '2013-05-29-wheezy-armel.img', 'armel', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"armel2013-05" + File.separatorChar + "')");
		    stat.executeUpdate("INSERT INTO targets (id, name, type, kernel, path) VALUES ('7', '2013-05-25-wheezy-raspbian.img', 'raspbian', 'kernel-qemu', '"+ ParsConf.getConfig()+ File.separatorChar +"system-images"+ File.separatorChar +"raspbian2013-05" + File.separatorChar + "')");
		    conn.close();
	    
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No target available.");
		} 
		    
		    return null;
	}

}
