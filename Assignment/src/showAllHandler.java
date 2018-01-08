import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles displaying all students from the database in nice HTML format.
 * Please type http://localhost:8000/showall
 */
public class showAllHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        final String head =
                "<html><head></head><body><table>"
                        + "<tr>"
                        + "<th>Name</th><th>StudentNumber</th><th>DOB</th><th>Address</th><th>Postcode</th><th>Student Number</th><th>Course Title</th><th>Start Date</th><th>Bursary</th><th>Email</th>"
                        + "</tr>";
        final String foot =
                "</table></body></html>";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        StudentDAO dao = new StudentDAO();
        ArrayList<Student> students = new ArrayList<>();
        students = dao.getAllStudents();

        he.sendResponseHeaders(200, 0);
        out.write(head);

        for (Student s : students) {
            out.write("<tr><td>" + s.getName() + "</td><td>" + s.getEmail() + "</td><td>" + s.getDob()
                    + "</td><td>" + s.getAddress() + "</td><td>" + s.getPostcode() + "</td><td>" + s.getStudentNumber()
                    + "</td><td>" + s.getCourseTitle() + "</td><td>" + s.getStartDate() + "</td><td>" + s.getBursary()
                    + "</td><td>" + s.getEmail());
        }
        out.write(foot);
        out.close();
    }
}