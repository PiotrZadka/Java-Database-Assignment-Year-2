import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * This class retrieves all students in REST client in JSON format via POST method
 */
public class GetJsonAllStudentsHandler implements HttpHandler {
    Gson gson = new Gson();
    ArrayList<Student> allStudents = new ArrayList<>();
    StudentDAO dao = new StudentDAO();

    public void handle(HttpExchange t) throws IOException {

        allStudents = dao.getAllStudents();
        String response = gson.toJson(allStudents);

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
