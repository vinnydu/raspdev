package raspdev.handlers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import deploy.ScpTo;

public class SampleHandlerDep extends AbstractHandler {

	public SampleHandlerDep() {
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
		
		 File fXmlFile = new File("./raspdev/src/raspConf.xml");
	     //parsing scpConf.xml
	     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	 	 DocumentBuilder dBuilder = null;
	 	 Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	 
	 	 doc.getDocumentElement().normalize();
	 	 File filename = new File("./src/dir/"+doc.getElementsByTagName("filename").item(0).getTextContent());

	     String path = filename.getAbsolutePath();
	     int port = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());
	     String user = doc.getElementsByTagName("user").item(0).getTextContent();
	     String host = doc.getElementsByTagName("host").item(0).getTextContent();
	     String hostPath = doc.getElementsByTagName("host-path").item(0).getTextContent();
	     ScpTo.scpCli(path, user +"@"+ host +":"+ hostPath,port);
		
		return null;
	}

}
