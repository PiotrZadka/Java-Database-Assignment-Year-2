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

        try{
            //allStudents = dao.getAllStudents();
            //dao.getStudent("Sandra");
            //dao.insertStu(Student1);
            dao.deleteStu(Student1);

            //dao.updateStu(Student1);

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
