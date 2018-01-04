import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

public class dbApiHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        HashMap<String,String> post = new HashMap<String,String>();
        //read the request body
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        String line = "";
        String request = "";
        while((line = in.readLine()) != null) {
            request = request + line;
        }
        //individual key=value pairs are delimited by ampersands. Tokenize.
        String[] pairs = request.split("&");
        for(int i=0;i<pairs.length;i++) {
            //each key=value pair is separated by an equals, and both halves require URL decoding.
            String pair = pairs[i];
            post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
        }
        StudentDAO dao = new StudentDAO();
        String username = post.get("username");
        String password = post.get("password");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

        try{
            if(dao.checkLoginCredentials(username,password)){
                //retrieve api key for that user
                String api = dao.retrieveApiKey("admin");
                out.write("Api key for username "+username+" is "+api);
                he.sendResponseHeaders(200,0);
            }
            else{
                out.write("Credentials Invalid");
                he.sendResponseHeaders(200,0);
            }
        }
        catch (SQLException se) {
            he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
        }
        out.close();
    }
}
