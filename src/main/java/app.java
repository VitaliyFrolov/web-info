import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;

public class app extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        worker work = new worker();
        PrintWriter writer = resp.getWriter();
        try {
            writer.println(work.getJsonSystem());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            writer.println(work.getJsonData());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
