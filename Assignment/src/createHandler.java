import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class createHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        //output HTML form
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        out.write("<html><head></head><body><p>Registration for API key</p><form method=\"POST\" action=\"/createAccount\">");
        out.write("Username:<input name=\"username\"><br>");
        out.write("Password:<input name=\"password\"><br>");
        out.write("<input type=\"submit\" value=\"Create\">");
        out.write("</form></body></html>");
        out.close();
    }
}
