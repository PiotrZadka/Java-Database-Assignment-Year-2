import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class WebServiceTester {
    static Gson gson;

    public static void main(String[] args) {
        gson = new Gson();
        System.out.println("All Students = "+getStudents());


        try {
            System.out.println("Student = "+getStudent(14056838)); // Show in JSON or as String? Currently shows HTML string
            //postStudent(); //works
            //deleteStudent(); //works
            //updateStudent(); //works
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuffer getStudents() {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("http://localhost:8000/showall");
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

    //TO FIX
    private static StringBuffer getStudent(int studentId) {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("http://localhost:8000/showone");
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

    private static void postStudent() throws IOException {
        String urlParameters = "student={'studentNumber':123456,'courseTitle':'TEST','StartDate':'10-10-1010','email':'test@email.com','bursary':1000.0,'name':'Test','gender':'T','dob':'10-10-1010','address':'TEST','postcode':'TES TES'}";
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


    private static void updateStudent() throws IOException {
        String urlParameters = "student={'studentNumber':15684574,'courseTitle':'Software Engineering','StartDate':'01-09-2015','email':'jack@email.com','bursary':2560.0,'name':'Jack','gender':'M','dob':'21-02-1994','address':'Gorton','postcode':'GR2 3DS'}";
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


    private static void deleteStudent() throws IOException {
        String urlParameters = "ID=15684574";
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

