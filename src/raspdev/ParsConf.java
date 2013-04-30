package raspdev;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ParsConf {

	private File homedir ;
	private final File fXmlFile ;
	private Document doc = null;
	
	
	
	public ParsConf() {
		// TODO Auto-generated constructor stub
	
		homedir = new File(System.getProperty("user.home"));
		fXmlFile = new File(homedir, "/raspdev/src/raspConf.xml");
	}


	public void generatePars() {
		
		String path = fXmlFile.getAbsolutePath();
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

	public File getHomedir() {
		return homedir;
	}
	public File getfXmlFile() {
		return fXmlFile;
	}
	public Document getDoc() {
		return doc;
	}
	

}
