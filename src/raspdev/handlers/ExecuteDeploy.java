package raspdev.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;

import raspdev.ParsConf;

import com.jcraft.jsch.JSchException;

import deploy.Jscp;
import deploy.SecureContext;

public class ExecuteDeploy extends AbstractHandler {

	public ExecuteDeploy() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Raspdev",
				"Execution Deploy on Raspberry");

		ParsConf pars = new ParsConf();
		pars.generatePars();
		Document doc = pars.getDoc();
		String user = doc.getElementsByTagName("user").item(0).getTextContent();
		String host = doc.getElementsByTagName("host").item(0).getTextContent();
		String hostPath = doc.getElementsByTagName("host-path").item(0).getTextContent();
		String pathProg = doc.getElementsByTagName("path-project-todeploy").item(0).getTextContent();
		String privateKey = doc.getElementsByTagName("private-key").item(0).getTextContent();

		File filename = new File(pathProg);
		String path = filename.getAbsolutePath();
		SecureContext context = new SecureContext(user,host);

		// set optional security configurations.
		context.setTrustAllHosts(true);

		context.setPrivateKeyFile(new File(privateKey));

		try {
			Jscp.exec(context,path, hostPath, Arrays.asList("logs/log[0-9]*.txt","backups")  );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
