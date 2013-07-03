package raspdev.handlers;

import java.io.File;
import java.io.IOException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import raspdev.ParsConf;


/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExecuteVirtual extends AbstractHandler {


	/**
	 * The constructor.
	 */
	public ExecuteVirtual() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

    Runtime run = Runtime.getRuntime();
    File fscript=new File(ParsConf.getConfig()+File.separatorChar+"tools"+File.separatorChar);
	 if (System.getProperty("os.name").startsWith("Windows")) {

		    Process pr;
			try {
				pr = run.exec("raspdev.bat rvd", null,fscript);
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
			pr = run.exec("./raspdev rvd", null,fscript);
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
