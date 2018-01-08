import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles retrieving one student in REST Client via POST method in JSON format
 */
public class GetJsonOneStudentHandler implements HttpHandler {
    Gson gson = new Gson();
    Student student = new Student();
    StudentDAO dao = new StudentDAO();

    public void handle(HttpExchange t) throws IOException {
        HashMap<String, String> insertResult = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(t.getRequestBody()));
        String line = "";
        String request = "";
        while ((line = in.readLine()) != null) {
            request = request + line;
        }
        String[] pairs = request.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            insertResult.put(URLDecoder.decode(pair.split("=")[0], "UTF-8"), URLDecoder.decode(pair.split("=")[1], "UTF-8"));
        }
        int studentID = Integer.valueOf(insertResult.get("StudentNumber"));
        int apiKey = Integer.valueOf(insertResult.get("key"));


        try {
            if (dao.checkApiKey(apiKey)) {
                try {
                    student = dao.getStudent(studentID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String response = gson.toJson(student);
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            else
            {
                t.sendResponseHeaders(403, 0); //HTTP 403 (FORBIDDEN ACCESS)
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(t.getResponseBody()));
                out.write("Invalid key!");
                out.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
