package raspdev;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		pro =getCurrentSelectedProject();
		projectDirectory=pro.getLocation().toFile();
	
	}


	public void generatePars() {
		
		//String path = fXmlFile.getAbsolutePath();
	
		path= projectDirectory.getAbsolutePath();
		path =path+"/src"+"/raspConf.xml";
		System.out.println(path);
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

//	public File getfXmlFile() {
//		return fXmlFile;
//	}
//	public void setQemuPath(String textContent) {
//		doc.getElementsByTagName("qemu-path").item(0).setTextContent(textContent);
//		
//	}
//	public void setKernelPath(String textContent) {
//		doc.getElementsByTagName("qemu-kernel").item(0).setTextContent(textContent);
//		
//	}
//	public void setSOPath(String textContent) {
//		doc.getElementsByTagName("raspbian-path").item(0).setTextContent(textContent);
//		
//	}
	public void setEmuValues(String qemuPath, String qemuKernel, String soPath)
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
			xmlOutput.output(docj, new FileWriter(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
