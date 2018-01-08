import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *  This class tests CRUD methods used by REST client
 *  All operations include API &key in the parameter.
 */
public class WebServiceTester {
    static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();

        // List all students in JSON format
        System.out.println("All Students = "+getStudents());

        try {
            // List one student with ID 14056838
            System.out.println("Student = "+getStudent(14056838));

            // Create TEST student with following information
            //student={'studentNumber':123456,'courseTitle':'TEST','StartDate':'10-10-1010','email':'test@email.com','bursary':1000.0,'name':'Test','gender':'T','dob':'10-10-1010','address':'TEST','postcode':'TES TES'}";
            postStudent();

            // Delete student with following ID 123456 (Created above)
            deleteStudent();

            // Update student with ID 15684574 with following information (Change name Josh back to Jack*) *Jack was named Josh in DatabaseTester
            updateStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method return HTTP response for retrieving all students
     * @return  all students in JSON format
     */
    private static StringBuffer getStudents() {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("http://localhost:8000/get-json-all-students");
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

    /**
     * This method return HTTP response for retrieving one student
     * @param studentId Student's ID number
     * @return one student in JSON format
     * @throws IOException SQL errors
     */
    private static String getStudent(int studentId) throws IOException {

        String urlParameters = "StudentNumber="+studentId+"&key=123";
        URL url;
        url = new URL("http://localhost:8000/get-json-one-student");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(urlParameters);

        writer.flush();
        writer.close();
        os.close();

        String response = "";
        String line;
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) { response += line;
            }
        }
        return response;

    }

    /**
     * This method creates new TEST student for testing purposes.
     * @throws IOException SQL errors
     */
    private static void postStudent() throws IOException {
        String urlParameters = "student={'studentNumber':123456,'courseTitle':'TEST','StartDate':'10-10-1010','email':'test@email.com','bursary':1000.0,'name':'Test','gender':'T','dob':'10-10-1010','address':'TEST','postcode':'TES TES'}&key=123";
        URL url;
        url = new URL("http://localhost:8000/insert");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(urlParameters);

        writer.flush();
        writer.close();
        os.close();

        String response = "";
        String line;
        int responseCode = conn.getResponseCode();
        System.out.println("\nresponseCode = " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                response += line;
            }
        }
        System.out.println("response = " + response);
        System.out.println("Student Inserted!");
    }


    /**
     * This method updates specific student. Changes name from Josh to Jack
     * @throws IOException SQL errors
     */
    private static void updateStudent() throws IOException {
        String urlParameters = "student={'studentNumber':15684574,'courseTitle':'Software Engineering','StartDate':'01-09-2015','email':'jack@email.com','bursary':2560.0,'name':'Jack','gender':'M','dob':'21-02-1994','address':'Gorton','postcode':'GR2 3DS'}&key=123";
        URL url;
        url = new URL("http://localhost:8000/update");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(urlParameters);

        writer.flush();
        writer.close();
        os.close();

        String response = "";
        String line;
        int responseCode = conn.getResponseCode();
        System.out.println("\nresponseCode = " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                response += line;
            }
        }
        System.out.println("response = " + response);
        System.out.println("Student Updated!");
    }

    /**
     * This method deletes specific user (Test user)
     * @throws IOException SQL errors
     */
    private static void deleteStudent() throws IOException {
        String urlParameters = "ID=123456&key=123";
        URL url;
        url = new URL("http://localhost:8000/delete");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(urlParameters);

        writer.flush();
        writer.close();
        os.close();

        String response = "";
        String line;
        int responseCode = conn.getResponseCode();
        System.out.println("\nresponseCode = " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                response += line;
            }
        }
        System.out.println("response = " + response);
        System.out.println("Student Deleted!");
    }

}

