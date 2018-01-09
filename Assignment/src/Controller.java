import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * This class starts server on port 8000
 */

public class Controller {
    public static void main(String[] args) {
        try {

            //HTTP HANDLERS
            HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
            server.createContext("/", new defaultHandler()); // Default home website
            server.createContext("/showall", new showAllHandler()); // Shows all Students from Database

            // This handler was for testing purposes. Left it in case Tutor would like to have a look.
            // server.createContext("/showone", new showStudentHandler());

            server.createContext("/get-json-all-students", new GetJsonAllStudentsHandler()); // Handles REST post show all students
            server.createContext("/get-json-one-student", new GetJsonOneStudentHandler()); // Handles REST post show one student
            server.createContext("/insert", new insertHandler()); // Handle REST inserting
            server.createContext("/delete",new deleteHandler()); // Handle REST deleting
            server.createContext("/update",new updateHandler()); // Handle REST updating

            // HANDLING CREATE ACCOUNT
            server.createContext("/create",new createHandler());
            server.createContext("/createAccount",new createAccountHandler());

            // HANDLING LOGIN TO DB TO RETRIEVE API KEY
            // login: admin
            // password: admin
            server.createContext("/login",new loginHandler());
            server.createContext("/dbApi",new dbApiHandler());

            // SERVER RUNNING
            server.start();
            System.out.println("Server running on port 8000");
            System.out.println("To check the output please type in browser http://localhost:8000/");
        }
        catch(IOException ioe){
            /**
             * @exception Catches any SQL database error that might occur.
             */
            System.err.println("IOException: "+ioe.getMessage());
        }
    }
}