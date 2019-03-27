import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XmlParserDom {

    private static ArrayList<portApp> portApps = new ArrayList<portApp>();
    private static ArrayList<portAdmin> portAdmins = new ArrayList<portAdmin>();

    private void parserDom(String domain, String path, Integer port) {

        try {
            File inputFile = new File(path + "/glassfish/domains/" + domain + "/config/domain.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("network-listeners");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList domNameList = eElement.getElementsByTagName("network-listener");
                    //portAPP
                    if (port == 0) {
                        Node node1 = domNameList.item(0);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element dom = (Element) node1;
                            String ports = dom.getAttribute("port");
                            if (ports.equals("${HTTP_LISTENER_PORT}")) {

                            } else portApps.add(new portApp(ports));
                        }
                    }
                    //portAdm
                    if (port == 1) {
                        Node node1 = domNameList.item(2);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element dom = (Element) node1;
                            String ports = dom.getAttribute("port");
                            if (ports.equals("${ASADMIN_LISTENER_PORT}")) {

                            } else portAdmins.add(new portAdmin(ports));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map getAppPort(String domain, String path) {
        portApps.clear();
        portAdmins.clear();
        HashMap<String, String> myMap = new HashMap<String, String>();
        parserDom(domain, path, 0);
        for (portApp tmp : portApps) {
            myMap.put("PortAPP", tmp.getPorts());
        }
        parserDom(domain, path, 1);
        for (portAdmin tmp : portAdmins) {
            myMap.put("PortAdmin", tmp.getPort());
        }
        return myMap;
    }
}
