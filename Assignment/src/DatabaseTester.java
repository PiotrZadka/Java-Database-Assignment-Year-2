import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class DatabaseTester {
    public static void main(String[] args) {
        ArrayList<Student> allStudents = new ArrayList<>();
        StudentDAO dao = new StudentDAO();
        Gson gson = new Gson();

        // EXAMPLE STUDENTS OBJECTS FOR TESTING METHODS
        Student Student1 = new Student("Jack","M","21-02-1994","Gorton","GR2 3DS",
                15684574,"Software Engineering","01-09-2015",2560,"jack@email.com");
        Student Student2 = new Student("Khuram","M","01-01-1991","Salford","SF2 2DD",
                12687656,"Art","11-02-2014",1221,"khuram@email.com");

        // THIS STUDENT OBJECT IS SIMILAR TO STUDENT1 THE ONLY DIFFERENCE IS NAME = JOSH NOT JACK
        Student Student3 = new Student("Josh","M","21-02-1994","Gorton","GR2 3DS",
                15684574,"Software Engineering","01-09-2015",2560,"jack@email.com");

        // VALIDATION TEST
        Student ValidationStudent = new Student();
        ValidationStudent.setName("Tester1");
        ValidationStudent.setGender("M");
        ValidationStudent.setStudentNumber(12345677);
        ValidationStudent.setCourseTitle("TEST COURSE4");
        ValidationStudent.setAddress("Test Street@");
        ValidationStudent.setPostcode("TE3 3ST");
        ValidationStudent.setStartDate("01/01/2018");
        ValidationStudent.setDob("02/02/2017");
        ValidationStudent.setEmail("test@test.com");
        ValidationStudent.setBursary(1000.0f);

        System.out.println("/////VALIDATION TESTER STUDENT/////");
        System.out.println("Student ID = "+ValidationStudent.getStudentNumber()+"\nName = "+ValidationStudent.getName()+"\nCourse Title = "+ValidationStudent.getCourseTitle()+"\nStart Date = "+ValidationStudent.getStartDate()
                +"\nEmail = "+ValidationStudent.getEmail()+"\nBursary = "+ValidationStudent.getBursary()+"\nGender = "+ValidationStudent.getGender()+"\nDate of Birth = "+ValidationStudent.getDob()
                +"\nAddress = "+ValidationStudent.getAddress()+"\nPostcode = "+ValidationStudent.getPostcode());
        System.out.println("///////////////////////////////////");

        //////////////////////////////////////////////////
        // BELOW YOU CAN FIND ALL THE TESTING CALLOUTS
        //////////////////////////////////////////////////
        try{
            // LIST STUDENTS IN JSON FORMAT
            allStudents = dao.getAllStudents();
            String myJson = gson.toJson(allStudents);
            System.out.println("QUERY 1(JSON): SELECT * FROM STUDENTS");
            System.out.println(myJson);

            // DISPLAY STUDENT BY SPECIFIC ID
            Student studentID = dao.getStudent(14056838);
            String myJson2 = gson.toJson(studentID);
            System.out.println("Student with number "+studentID.getStudentNumber()+" in JSON format:");
            System.out.println(myJson2);

            System.out.println("\nMy organised display:");
            System.out.println("Student ID = "+studentID.getStudentNumber()+"\nName = "+studentID.getName()+"\nCourse Title = "+studentID.getCourseTitle()+"\nStart Date = "+studentID.getStartDate()
                    +"\nEmail = "+studentID.getEmail()+"\nBursary = "+studentID.getBursary()+"\nGender = "+studentID.getGender()+"\nDate of Birth = "+studentID.getDob()
                    +"\nAddress = "+studentID.getAddress()+"\nPostcode = "+studentID.getPostcode());



            // INSERTING NEW STUDENTS TO DATABASE (Student2 will be deleted later using deleteStu() )
            if(dao.insertStu(Student1)) {
                //Row inserted into database without issues
                System.out.println("Row successfully inserted!");
            }
            else{
                //Row already exists or was previously inserted
                System.out.println("Row already exists!");
            }

            if(dao.insertStu(Student2)) {
                //This row will always be success since it's being deleted at later stage
                System.out.println("Row successfully inserted!");
            }
            else{
                //Row already exists or was previously inserted
                System.out.println("Row already exists!");
            }

            // UPDATING EXISTING STUDENTS WITH NEW NAME
            // Changing "Jack" to "Josh"
            if(dao.updateStu(Student3)){
                System.out.println("\nRow successfully updated");
            }
            else{
                System.out.println("Row failed to update.");
            }

            // DELETING PREVIOUSLY INSERTED STUDENT ID
            if(dao.deleteStu(Student2.getStudentNumber())){
                //if row exists and can be deleted
                System.out.println("\nRow successfully deleted!");
            }
            else{
                //if row doesn't exists or was previously deleted
                System.out.println("Row doesn't exist!");
            }

            // CREDENTIALS CHECK
            // (There is one row in database with username: admin & password: admin)
            if(dao.checkLoginCredentials("admin","admin")){
                System.out.println("Credentials correct!");
            }
            else{
                System.out.println("Credentials incorrect!");
            }

            // CHECK API KEY(String)
            System.out.println("Username 'admin' has apikey = "+dao.retrieveApiKey("admin"));

        }
        catch (SQLException e){
            System.out.println("SQL exception: "+e.getMessage());
        }

    }
}
