package raspdev.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.w3c.dom.Document;

import raspdev.ParsConf;

public class ExecuteKey extends AbstractHandler{

	public ExecuteKey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		Runtime run = Runtime.getRuntime();
	    File fscript=new File(ParsConf.getConfig()+File.separatorChar+"pyscript"+File.separatorChar);
	    ParsConf pars = new ParsConf();
		pars.generatePars();
		Document doc = pars.getDoc();
		String host = doc.getElementsByTagName("host").item(0).getTextContent();

			    Process pr;
				try {
					pr = run.exec("python autokey.py "+host, null,fscript);
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
