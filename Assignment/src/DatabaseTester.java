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


        //////////////////////////////////////////////////
        // BELOW YOU CAN FIND ALL THE TESTING CALLOUTS
        // REMOVE SELECTED (//) TO TEST SELECTED FUNCTION
        //////////////////////////////////////////////////

        try{

            // LIST ROWS IN STUDENTS TABLE
            allStudents = dao.getAllStudents();

            // DISPLAY NAMED STUDENT
            //dao.getStudent("Sandra");


            /* UPDATING EXISTING STUDENTS WITH NEW NAME
            if(dao.updateStu(Student1)){
                System.out.println("Row successfully updated");
            }
            else{
                System.out.println("Row failed to update, check error information below");
            }

            */

            /* INSERTING NEW STUDENT TO DATABASE
            if(dao.insertStu(Student1)){
                //Row inserted into database without issues
                System.out.println("Row successfully inserted!");
            }
            else{
                //Row already exists or was previously inserted
                System.out.println("Row already exists!");
            }

            */

            /* DELETING PREVIOUSLY INSERTED STUDENT
            if(dao.deleteStu(Student1)){
                //if row exists and can be deleted
                System.out.println("Row successfully deleted!");
            }
            else{
                //if row doesn't exists or was previously deleted
                System.out.println("Row doesn't exist!");
            }

           */




            //TODO
            //checkLoginCreditials(String,String)
            //checkApiKey(String)


        }
        catch (SQLException e){
            System.out.println("SQL exception: "+e.getMessage());
        }

        String myJson = gson.toJson(allStudents);
        System.out.println(myJson);

    }
}
