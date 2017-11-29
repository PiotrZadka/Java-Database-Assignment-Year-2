import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Controller {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
            server.createContext("/", new defaultHandler());
            server.createContext("/show", new showHandler());
            server.createContext("/get-json", new GetJsonHandler());
            server.createContext("/insert", new insertHandler());
            // updateHandler
            // deleteHandler

            server.start();
            System.out.println("Server running on port 8005");
            System.out.println("To check the output please type in browser http://localhost:8005/");
        }
        catch(IOException ioe){
            System.err.println("IOException: "+ioe.getMessage());
        }
    }
}