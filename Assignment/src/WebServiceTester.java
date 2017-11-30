import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WebServiceTester {
    static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();
        System.out.println("Students = "+getStudents());

        //postStudent();
        //updateStudent();
        //deleteStudent():
        //getStudent(14056838);

    }

    private static StringBuffer getStudents() {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("http://localhost:8000/get-json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String output;
            while ((output = reader.readLine()) != null) {
                response.append(output);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
}

