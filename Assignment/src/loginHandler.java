import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * This class handles log-in input form
 * Please type http://localhost:8000/login
 */
public class loginHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        //output HTML form
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        out.write("<html><head></head><body><form method=\"POST\" action=\"/dbApi\">");
        out.write("Username:<input name=\"username\"><br>");
        out.write("Password:<input name=\"password\"><br>");
        out.write("<input type=\"submit\" value=\"Submit\">");
        out.write("</form></body></html>");
        out.close();
    }
}
