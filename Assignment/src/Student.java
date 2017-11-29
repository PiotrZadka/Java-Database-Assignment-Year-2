public class Student extends Person {
    int studentNumber;
    String courseTitle, StartDate, email;
    float bursary;

    Student(){

    }
    Student(String name, String gender, String dob, String address, String postcode, int studentNumber, String courseTitle,
            String StartDate, float bursary, String email){

        super(name,gender,dob,address,postcode);

        this.studentNumber = studentNumber;
        this.courseTitle = courseTitle;
        this.StartDate = StartDate;
        this.email = email;
        this.bursary = bursary;
    }

    public int getStudentNumber(){
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber){
        this.studentNumber = studentNumber;
    }

    public String getCourseTitle(){
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle){
        this.courseTitle = courseTitle;
    }

    public String getStartDate(){
        return StartDate;
    }

    public void setStartDate(String StartDate){
        this.StartDate = StartDate;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public float getBursary(){
        return bursary;
    }

    public void setBursary(float bursary){
        this.bursary = bursary;
    }


}
