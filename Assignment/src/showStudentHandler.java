import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


//TO FIX
public class showStudentHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        HashMap<String, String> insertResult = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
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

        final String head =
                "<html><head></head><body><table>"
                        + "<tr>"
                        + "<th>Student ID</th><th>Name</th><th>Email</th><th>DOB</th><th>Address</th><th>Postcode</th><th>Course Title</th><th>Start Date</th><th>Bursary</th>"
                        + "</tr>";
        final String foot =
                "</table></body></html>";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        StudentDAO dao = new StudentDAO();

        try {
            Student s = dao.getStudent(studentID);
            he.sendResponseHeaders(200, 0);
            out.write(head);
            out.write("<tr><td>"
                    + s.getStudentNumber() + "</td><td>"
                    + s.getName() + "</td><td>"
                    + s.getEmail() + "</td><td>"
                    + s.getDob() + "</td><td>"
                    + s.getAddress() + "</td><td>"
                    + s.getPostcode() + "</td><td>"
                    + s.getCourseTitle() + "</td><td>"
                    + s.getStartDate() + "</td><td>"
                    + s.getBursary() + "</td><td>"
            );
            out.write(foot);
        }
        catch (SQLException se){
            he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
            out.write("There is no student with such ID Number");
        }
        out.close();
    }
}
