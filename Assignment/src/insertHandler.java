import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

public class insertHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {
        Gson gson = new Gson();
        //


        HashMap<String, String> post = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        String line = "";
        String request = "";
        while ((line = in.readLine()) != null) {
            request = request + line;
        }
        String[] pairs = request.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            post.put(URLDecoder.decode(pair.split("=")[0], "UTF-8"), URLDecoder.decode(pair.split("=")[1], "UTF-8"));
        }
        StudentDAO dao = new StudentDAO();
        Student student3 = gson.fromJson(post.get("student"), Student.class);
    /*
        student3.setName(post.get("studentNumber"));
        student3.setEmail(post.get("courseTitle"));
        student3.setName(post.get("StartDate"));
        student3.setEmail(post.get("email"));
        student3.setName(post.get("bursary"));
        student3.setEmail(post.get("name"));
        student3.setEmail(post.get("gender"));
        student3.setEmail(post.get("dob"));
        student3.setEmail(post.get("address"));
        student3.setEmail(post.get("postcode"));
        */

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        System.out.println(student3.getName());
        try {
            dao.insertStu(student3);
            he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
            out.write("Success!");
        } catch (SQLException se) {
            he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
            out.write("Error Adding Student");
        }
        out.close();
    }

}
