import com.google.gson.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;


public class worker {
    private String HostName;
    private String HomeGF;
    private ArrayList<String> domains = new ArrayList<String>();
    private HashMap<String, HashMap<String, String>> myHashMap = new HashMap<String, HashMap<String, String>>();

    private void getData() throws IOException, ParserConfigurationException {

        domains SystemWork = new domains();
        XmlParserDom xmlConf = new XmlParserDom();

        //получаем имя хоста
        this.HostName = SystemWork.getHostName();
        //System.out.println(HostName);

        //получаем дерикторию GF
        this.HomeGF = SystemWork.getUserHomeGF();
        //System.out.println(HomeGF);

        //получаем список доменов
        this.domains = SystemWork.findDomain(HomeGF);
        //System.out.println(domains);
        for (String tmp : domains) {
            myHashMap.put(tmp, (HashMap<String, String>) xmlConf.getAppPort(tmp, HomeGF));
        }
        //System.out.println(myHashMap);
    }

    public String getJsonSystem() throws IOException, ParserConfigurationException {
        getData();
        JsonObject host = new JsonObject();
        host.addProperty("Host", HostName);
        host.addProperty("Home", HomeGF);
        String jsonHost = host.toString();
        //System.out.println(host);
        return jsonHost;
    }

    public String getJsonData() throws IOException, ParserConfigurationException {
        getData();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaMap = gsonBuilder.toJson(myHashMap);
        //System.out.println(jsonFromJavaMap);
        return jsonFromJavaMap;
    }
}
