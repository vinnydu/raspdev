package raspdev;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.jcraft.jsch.JSchException;

import deploy.ScpTo;

public class App {
	public static void main(String[] args) throws IOException, JSchException, ParserConfigurationException, SAXException {

     File fXmlFile = new File("./src/raspConf.xml");
     //parsing scpConf.xml
     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
 	 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
 	 Document doc = dBuilder.parse(fXmlFile);
 	 doc.getDocumentElement().normalize();
 	 File filename = new File("./src/dir/"+doc.getElementsByTagName("filename").item(0).getTextContent());
	 System.out.println("----------------------------");

     String path = filename.getAbsolutePath();
     ScpTo.scpCli(path, doc.getElementsByTagName("user").item(0).getTextContent()+"@"+doc.getElementsByTagName("host").item(0).getTextContent()+":"+doc.getElementsByTagName("hostpath").item(0).getTextContent());
  
	}

}
