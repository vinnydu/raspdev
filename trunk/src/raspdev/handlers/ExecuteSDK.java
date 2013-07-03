package raspdev.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import raspdev.ParsConf;

public class ExecuteSDK extends AbstractHandler{

	public ExecuteSDK() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		 Runtime run = Runtime.getRuntime();
		    File fscript=new File(ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar);
			 if (System.getProperty("os.name").startsWith("Windows")) {

				    Process pr;
					try {
						pr = run.exec("raspdev.bat sdk", null,fscript);
						pr.waitFor();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    }
			 else{
				
			    Process pr;
				try {
					pr = run.exec("./raspdev sdk", null,fscript);
					pr.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
		
		return null;
	
	}
}
