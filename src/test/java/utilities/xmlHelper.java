package utilities;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
public class xmlHelper {
	public static String getData(String tagName) throws Exception {
        File xmlFile = new File(System.getProperty("user.dir") + "\\testdata\\CorporateFormData.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(tagName).item(0).getTextContent();
    }
}

