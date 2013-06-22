package raspdev;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IProject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import raspdev.wizards.PageOne;

public class ParsForDevice {
	private Document doc = null;
	private String path;
	public ParsForDevice() {
		// TODO Auto-generated constructor stub
		
	}
  
	public void generatePars()
	{
		
		path=new File(PageOne.getConfDir()+"/raspConf.xml").toString();
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
	/**
	 * @param args
	 */
	public Document getDoc() {
		return doc;
	}
}
