package raspdev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ParsConf {

	//private final File fXmlFile ;
	private Document doc = null;
	private IProject pro;
	private File projectDirectory;
	private String path;
	public ParsConf()  {
		// TODO Auto-generated constructor stub
		//fXmlFile = new File("raspdev/src/raspConf.xml");
		// Get the currently selected file from the editor
		
		if(getCurrentSelectedProject()!=null){
		pro=getCurrentSelectedProject();
		projectDirectory=pro.getLocation().toFile();}
		else {pro=null;
		     projectDirectory=null;
		}
	
	}


	public void generatePars() {
		
		//String path = fXmlFile.getAbsolutePath();
	    if(projectDirectory!=null){
		path= projectDirectory.getAbsolutePath();
		path =path+"/config"+"/raspConf.xml";
		System.out.println(path);}
	    else  path=new File(System.getProperty("user.home"),"/raspdevSDK/raspConf.xml").toString();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(path);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
	}

	public void setEmuValues(String qemuPath, String qemuKernel, String soPath, String portForWard)
	{
		XMLOutputter xmlOutput = new XMLOutputter();
		SAXBuilder builder = new SAXBuilder();
		
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			
			org.jdom.Document docj = (org.jdom.Document) builder.build(path);
			Element rootNode = docj.getRootElement();
			rootNode.getChild("qemu-path").setText(qemuPath);
			rootNode.getChild("qemu-kernel").setText(qemuKernel);
			rootNode.getChild("raspbian-path").setText(soPath);
			rootNode.getChild("port").setText(portForWard);
			xmlOutput.output(docj, new FileWriter(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setHostValues(String user,String host, String hostPath, String projectDeploy, String privateKey){
		
		XMLOutputter xmlOutput = new XMLOutputter();
		SAXBuilder builder = new SAXBuilder();
		
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			
			org.jdom.Document docj = (org.jdom.Document) builder.build(path);
			Element rootNode = docj.getRootElement();
			rootNode.getChild("user").setText(user);
			rootNode.getChild("host").setText(host);
			rootNode.getChild("host-path").setText(hostPath);
			rootNode.getChild("path-project-todeploy").setText(projectDeploy);
			rootNode.getChild("private-key").setText(privateKey);
			xmlOutput.output(docj, new FileWriter(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void setFrameBuffer(String frameBufferWidth, String frameBufferHeight){
		
		 try {
		      String line;
		      Process p = Runtime.getRuntime().exec("");
		      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		      while ((line = input.readLine()) != null) {
		        System.out.println(line);
		      }
		      input.close();
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		
	}
	
	public Document getDoc() {
		return doc;
	}
	public static IProject getCurrentSelectedProject() {
	    IProject project = null;
	    ISelectionService selectionService = 
	        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();

	    ISelection selection = selectionService.getSelection();

	    if(selection instanceof IStructuredSelection) {
	        Object element = ((IStructuredSelection)selection).getFirstElement();

	        if (element instanceof IResource) {
	            project= ((IResource)element).getProject();
	        } 
	     
	    }
	    return project;
	}

}
