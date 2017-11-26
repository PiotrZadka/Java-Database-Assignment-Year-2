import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


//THIS IS DEFAULT http://localhost:8005/ MESSAGE
public class defaultHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        String response = "Please type /show in bar above";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
