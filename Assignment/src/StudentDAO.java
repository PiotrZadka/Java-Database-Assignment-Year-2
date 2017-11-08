import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String dbURL = "jdbc:sqlite:studentdb.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            return dbConnection;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public ArrayList<Student> getAllStudents() throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String query = "SELECT * FROM students;";
        Student temp = null;
        ArrayList<Student>allStudents = new ArrayList<>();

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
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
        finally {
            if (resultset != null) { resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) { dbConnection.close();
            }
        }
        return allStudents;
        //array of students
    }

    public void insertStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String query = "INSERT INTO students (Name, Gender, DOB, Address, Postcode, StudentNumber, CourseTitle, StartDate, Bursary, Email) " +
                "VALUES ('"+newStudent.getName()+"','"+newStudent.getGender()+"','"+newStudent.getDob()+"','"+newStudent.getAddress()+"','"+newStudent.getPostcode()+"'" +
                ","+newStudent.getStudentNumber()+",'"+newStudent.getCourseTitle()+"','"+newStudent.getStartDate()+"',"+newStudent.getBursary()+",'"+newStudent.getEmail()+"');";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println("\n"+query);
            statement.executeUpdate(query);


        } catch (SQLException e) {
            System.out.println(e.getMessage()); }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void deleteStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String query = "DELETE FROM students WHERE Address = '"+newStudent.getAddress()+"';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println("\n"+query);

            statement.executeUpdate(query);


        } catch (SQLException e) {
            System.out.println(e.getMessage()); }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void getStudent(String stuName) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;

        String query = "\nSELECT Name, StudentNumber, CourseTitle FROM students where Name = '"+stuName+"';";

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);

            resultset = statement.executeQuery(query);
            while (resultset.next()) { System.out.println(resultset.getString("Name")+" "
                    +resultset.getInt("StudentNumber")+" "
                    +resultset.getString("CourseTitle"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (resultset != null) { resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) { dbConnection.close();
            }
        }
    }

    public void updateStu(Student newStudent) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE students SET Name = 'Josh McEwen' WHERE Name = '"+newStudent.getName()+"';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println("\n"+query);

            statement.executeUpdate(query);


        } catch (SQLException e) {
            System.out.println(e.getMessage()); }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

}
