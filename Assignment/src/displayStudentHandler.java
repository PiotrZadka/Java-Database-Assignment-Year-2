import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;


//TO FIX
public class displayStudentHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        final String head =
                "<html><head></head><body><table>"
                        + "<tr>"
                        + "<th>Name</th><th>Email</th><th>CourseTitle</th>"
                        + "</tr>";
        final String foot =
                "</table></body></html>";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
        StudentDAO dao = new StudentDAO();

        try {
            Student s = dao.getStudent(15438568);
            he.sendResponseHeaders(200, 0);
            out.write(head);
            out.write("<tr><td>" + s.getName() + "</td><td>" + s.getEmail() + "</td><td>" + s.getCourseTitle() + "</td>");
            out.write(foot);
        }
        catch (SQLException se){
            he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
            out.write("There is no student with such ID Number");
        }
        out.close();
    }
}
