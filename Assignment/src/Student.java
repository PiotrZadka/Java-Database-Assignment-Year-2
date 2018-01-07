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
        if(Integer.toString(studentNumber).matches("^\\d{8}$")) {
            this.studentNumber = studentNumber;
        }
        else{
            throw new IllegalArgumentException("Student number is invalid (Valid ID contains 8 digits");
        }
    }

    public String getCourseTitle(){
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle){

        if(courseTitle.matches(".*[a-zA-Z]+.*")) {
            this.courseTitle = courseTitle;
        }
        else{
            throw new IllegalArgumentException("Course Title must only contain alphabet letters");
        }
    }

    public String getStartDate(){
        return StartDate;
    }

    public void setStartDate(String StartDate){
        if(StartDate.matches("^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$")) {
            this.StartDate = StartDate;
        }
        else{
            throw new IllegalArgumentException("Available Date format: DD.MM.YYYY or DD/MM/YYYY or DD-MM-YYYY");
        }
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Email is invalid. Check for correct format i.e. email@email.com");
        }
    }

    public float getBursary(){
        return bursary;
    }

    public void setBursary(float bursary){
        if(Float.toString(bursary).matches("\\d+\\.\\d{1,2}")) {
            this.bursary = bursary;
        }
        else{
            throw new IllegalArgumentException("Bursary price is not valid. Correct format is 0.0 i.e. 1000.0f which is £1000.0 ");
        }
    }



}
