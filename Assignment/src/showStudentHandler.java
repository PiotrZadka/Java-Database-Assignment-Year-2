import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;


//TO FIX
public class showStudentHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

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
            Student s = dao.getStudent(14056838);
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
