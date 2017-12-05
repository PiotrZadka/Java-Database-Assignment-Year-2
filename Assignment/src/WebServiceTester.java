import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class WebServiceTester {
    static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();
        System.out.println("Students = "+getStudents());


        try {
            postStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //getStudent(14056838);
        //updateStudent();
        //deleteStudent():

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

    private static StringBuffer getStudent(int studentId) {
        StringBuffer response = new StringBuffer();
        response = null;
        //put your code here...
        return response;
    }

    private static void postStudent() throws IOException {
        String urlParameters = "Name=Test&Gender=M&DOB=20-20-2020&Address=Manchester&Postcode=TES TES&StudentNumber=00000000&CourseTitle=TEST&StartDate=20-20-2020&Bursary=0000&Email=test@email.com";
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
        System.out.println("responseCode = " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                response += line;
            }
        }
        System.out.println("response = " + response);
        System.out.println("Insert Done!!");
    }


    private static void updateStudent() throws IOException {
        //TODO
    }


    private static void deleteStudent() throws IOException {
        //TODO
    }

}

