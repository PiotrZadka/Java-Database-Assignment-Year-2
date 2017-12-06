import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    Connection dbConnection = null;
    Statement s = null;

    public Connection getDBConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String dbURL = "jdbc:sqlite:studentdb.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            s = dbConnection.createStatement();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public void closeConnection(){
        try{
            if(s!=null){
                s.close();
            }
            if(dbConnection!=null){
                dbConnection.close();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  ArrayList<Student> getAllStudents(){
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String query = "SELECT * FROM students;";
        Student temp = null;
        ArrayList<Student>allStudents = new ArrayList<>();

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                String name = resultset.getString("Name");
                String gender = resultset.getString("Gender");
                String dob = resultset.getString("DOB");
                String address = resultset.getString("Address");
                String postcode = resultset.getString("Postcode");
                int  studentNumber = resultset.getInt("StudentNumber");
                String courseTitle = resultset.getString("CourseTitle");
                String startDate = resultset.getString("StartDate");
                Float bursary = resultset.getFloat("Bursary");
                String email = resultset.getString("Email");

                temp = new Student(name,gender,dob,address,postcode,studentNumber,courseTitle,startDate,bursary,email);
                allStudents.add(temp);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        closeConnection();
        return allStudents;
    }

    public boolean insertStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String query = "INSERT INTO students (Name, Gender, DOB, Address, Postcode, StudentNumber, CourseTitle, StartDate, Bursary, Email) " +
                "VALUES ('"+newStudent.getName()+"','"+newStudent.getGender()+"','"+newStudent.getDob()+"','"+newStudent.getAddress()+"','"+newStudent.getPostcode()+"'" +
                ","+newStudent.getStudentNumber()+",'"+newStudent.getCourseTitle()+"','"+newStudent.getStartDate()+"',"+newStudent.getBursary()+",'"+newStudent.getEmail()+"');";
        System.out.println("\nQUERY 3: "+query);
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        closeConnection();
        return true;
    }

    public boolean deleteStu(int stuNumber) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        int rowCount = 0;

        // Firstly check if row exists and returns anything if so delete it if not return false and display message "Row doesn't exists"
        String queryV = "SELECT COUNT (*) from students WHERE StudentNumber = '"+stuNumber+"';";
        String query = "DELETE FROM students WHERE StudentNumber = '"+stuNumber+"';";
        System.out.println("\nQUERY 5: "+query);
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(queryV);
            resultset.next();
            rowCount = resultset.getInt(1);

            if(rowCount == 0){
                return false;
            }
            else{
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        closeConnection();
        return true;
    }

    public Student getStudent(int stuNumber) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        Student temp = null;

        String query = "SELECT * FROM students where StudentNumber = '"+stuNumber+"';";
        System.out.println("\nQUERY 2: "+query);
        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                String name = resultset.getString("Name");
                String gender = resultset.getString("Gender");
                String dob = resultset.getString("DOB");
                String address = resultset.getString("Address");
                String postcode = resultset.getString("Postcode");
                int  studentNumber = resultset.getInt("StudentNumber");
                String courseTitle = resultset.getString("CourseTitle");
                String startDate = resultset.getString("StartDate");
                Float bursary = resultset.getFloat("Bursary");
                String email = resultset.getString("Email");

                temp = new Student(name,gender,dob,address,postcode,studentNumber,courseTitle,startDate,bursary,email);

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConnection();
        return temp;

    }

    public boolean updateStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        int rowCount = 0;

        String queryV = "SELECT COUNT (*) from students WHERE Address = '"+newStudent.getAddress()+"';";
        String query = "UPDATE students SET Name = 'Josh' WHERE Address = '"+newStudent.getAddress()+"';";
        System.out.println("\nQUERY 4: "+query);
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(queryV);
            resultset.next();
            rowCount = resultset.getInt(1);

            if(rowCount == 0){
                return false;
            }
            else{
                statement.executeUpdate(query);
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        closeConnection();
        return true;
    }

    public Boolean checkLoginCredentials(String username, String password) throws SQLException {
        //TODO
        return false;
    }

    public boolean checkApiKey(String key) throws SQLException {
        //TODO
        return false;
    }

}
