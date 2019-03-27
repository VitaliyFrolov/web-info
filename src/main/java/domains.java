import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class domains {
    public String getHostName() {
        String hostName = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    public String getUserHomeGF() {
        return System.getenv("GLASSFISH_HOME");
    }

    public ArrayList findDomain(String path) {
        String pathDomains = path + "/glassfish/domains/";
        ArrayList<String> domains = new ArrayList<String>();
        try {
            String line;
            Process s = Runtime.getRuntime().exec("ls " + pathDomains);
            BufferedReader error = new BufferedReader(new InputStreamReader(s.getErrorStream()));
            while ((line = error.readLine()) != null) {
            }
            error.close();
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while ((line = input.readLine()) != null) {
                domains.add(line);
                //System.out.println(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return domains;
    }
}
