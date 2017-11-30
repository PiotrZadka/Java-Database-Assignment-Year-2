import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

public class deleteHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        Gson gson = new Gson();
        HashMap<String, String> deleteResult = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        String line = "";
        String request = "";
        while ((line = in.readLine()) != null) {
            request = request + line;
        }
        String[] pairs = request.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            deleteResult.put(URLDecoder.decode(pair.split("=")[0], "UTF-8"), URLDecoder.decode(pair.split("=")[1], "UTF-8"));
        }
        StudentDAO dao = new StudentDAO();
        Student student4 = gson.fromJson(deleteResult.get("student"), Student.class);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        try {
            dao.deleteStu(student4);
            he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
            out.write("Student Deleted!");
        } catch (SQLException se) {
            he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
            out.write("Error Deleting Student");
        }
        out.close();
    }
}
