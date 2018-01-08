import java.sql.*;
import java.util.ArrayList;

/**
 * This class handles all CRUD methods, Login Credentials check and API checking/retrieving.
 */

public class StudentDAO {
    Connection dbConnection = null;
    Statement s = null;

    /**
     * This method connects to our database
     * @return connection to database is made
     */
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

    /**
     * This method closes connection to database
     */
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

    /**
     * This method retrieves all students in database
     * @return Array List of all students in database
     */
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

    /**
     * This method inserts new Student object into our database
     * @param newStudent Student object
     * @return True if successful, False otherwise.
     * @throws SQLException SQL errors
     */
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
        finally {
            statement.close();
        }
        closeConnection();
        return true;
    }

    /**
     * This method deletes specific student based on provided ID
     * @param stuNumber Student ID number
     * @return True if successful, otherwise false.
     * @throws SQLException SQL errors
     */
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

    /**
     * This method returns specific student based on ID number
     * @param stuNumber Students ID number
     * @return Student Object
     * @throws SQLException SQL Error
     */
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

    /**
     * This method updates specific students row in database
     * @param newStudent Provided student object
     * @return True if update is successful, otherwise false
     * @throws SQLException SQL errors
     */
    public boolean updateStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        int rowCount = 0;

        String queryV = "SELECT COUNT (*) from students WHERE StudentNumber = '"+newStudent.getStudentNumber()+"';";
        String query = "UPDATE students SET " +
                "Name = '"+newStudent.getName()+"',"+
                "Gender = '"+newStudent.getGender()+"', "+
                "DOB = '"+newStudent.getDob()+"', "+
                "Address = '"+newStudent.getAddress()+"', "+
                "Postcode = '"+newStudent.getPostcode()+"', "+
                "CourseTitle = '"+newStudent.getCourseTitle()+"', "+
                "StartDate = '"+newStudent.getStartDate()+"', "+
                "Bursary = '"+newStudent.getBursary()+"', "+
                "Email = '"+newStudent.getEmail()+"' "+
                "WHERE StudentNumber = '"+newStudent.getStudentNumber()+"';";

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

    /**
     * This method handles user login in HTML form
     * @param username Form username
     * @param password Form password
     * @return true if connection is made, otherwise false
     * @throws SQLException SQL errors
     */
    public Boolean checkLoginCredentials(String username, String password) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;

        // Firstly check if user account exists and returns anything
        String query = "SELECT * from users WHERE username = '"+username+"';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(query);
            resultset.next();
            String dbPassword = resultset.getString("password");

            if(password.equals(dbPassword)){
                return true;
            }
            else{
                closeConnection();
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method retrieves API key from database
     * @param username Form username provided by the user.
     * @return String format of API key
     * @throws SQLException SQL errors
     */
    public String retrieveApiKey(String username) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String dbApiKey = "";

        // Firstly check if user account exists and returns anything
        String query = "SELECT apikey from users WHERE username = '"+username+"';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(query);
            resultset.next();
            dbApiKey = resultset.getString("apikey");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        statement.close();
        resultset.close();
        closeConnection();
        return dbApiKey;
    }

    /**
     * This method check if api key is valid
     * @param key Api key provided by the user in REST post. ?key=
     * @return true if matches post key value, otherwise false.
     * @throws SQLException SQL errors
     */
    public boolean checkApiKey(int key) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String dbApiKey = "";
        String query = "SELECT apikey from users WHERE apikey = '" + key + "';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            resultset = statement.executeQuery(query);
            resultset.next();
            String StringKey = String.valueOf(key);
            dbApiKey = resultset.getString("apikey");

            if (StringKey.equals(dbApiKey)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            statement.close();
            resultset.close();
            closeConnection();
        }
    }

}
