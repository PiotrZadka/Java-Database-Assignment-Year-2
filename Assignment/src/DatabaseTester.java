import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class DatabaseTester {
    public static void main(String[] args) {
        ArrayList<Student> allStudents = new ArrayList<>();
        StudentDAO dao = new StudentDAO();
        Gson gson = new Gson();
        Student Student1 = new Student("Jack","M","21-02-1994","Gorton","GR2 3DS",
                15684574,"Software Engineering","01-09-2015",2560,"jack@email.com");
        Student Student2 = new Student("Khuram","M","01-01-1991","Salford","SF2 2DD",
                12687656,"Art","11-02-2014",1221,"khuram@email.com");

        //////////////////////////////////////////////////
        // BELOW YOU CAN FIND ALL THE TESTING CALLOUTS
        //////////////////////////////////////////////////

        try{

            // LIST STUDENTS IN JSON FORMAT
            allStudents = dao.getAllStudents();
            String myJson = gson.toJson(allStudents);
            System.out.println("QUERY 1(JSON): SELECT * FROM STUDENTS");
            System.out.println(myJson);

            // DISPLAY NAMED STUDENT
            dao.getStudent("Sandra");

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
                //THIS ROW WILL ALWAYS BE SUCCESS SINCE IT"S BEING DELETED AT LATER STAGE
                System.out.println("Row successfully inserted!");
            }
            else{
                //Row already exists or was previously inserted
                System.out.println("Row already exists!");
            }

            // UPDATING EXISTING STUDENTS WITH NEW NAME
            // CHANGE "JACK" TO "JOSH"
            if(dao.updateStu(Student1)){
                System.out.println("Row successfully updated");
            }
            else{
                System.out.println("Row failed to update.");
            }

            // DELETING PREVIOUSLY INSERTED STUDENT ID
            if(dao.deleteStu(Student2.getStudentNumber())){
                //if row exists and can be deleted
                System.out.println("Row successfully deleted!");
            }
            else{
                //if row doesn't exists or was previously deleted
                System.out.println("Row doesn't exist!");
            }

            //TODO
            //checkLoginCreditials(String,String)
            //checkApiKey(String)

        }
        catch (SQLException e){
            System.out.println("SQL exception: "+e.getMessage());
        }

    }
}
