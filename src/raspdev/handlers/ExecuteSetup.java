package raspdev.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import raspdev.ParsConf;

public class ExecuteSetup extends AbstractHandler{

	public ExecuteSetup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		Runtime run = Runtime.getRuntime();
	    File fscript=new File(ParsConf.getConfig()+File.separatorChar+"pyscript"+File.separatorChar);
	    ParsConf pc = new ParsConf();
		pc.generatePars();
		String pathProject = ParsConf.getPathProject();

			    Process pr;
				try {
					pr = run.exec("python automakesetup.py "+pathProject+"config"+File.separatorChar+"raspManifest.xml "+pathProject+File.separatorChar, null,fscript);
					pr.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return null;
	}

}
