
class Student extends Person {
    int studentNumber;
    String courseTitle, StartDate, email;
    float bursary;

    // Empty constructor
    Student(){}

    /**
     * @see Person#Person
     * @param studentNumber Student's student ID
     * @param courseTitle Student's course name
     * @param StartDate Student's start date
     * @param bursary Student's bursary value
     * @param email Student's email address
     */
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

    /**
     * Validates student ID number to only contain numeric values of maximum total length of 8
     * @param studentNumber Student's 8 digit number
     */
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

    /**
     * Validates student Course Title to only contain lower or upper case alphabetic letters
     * @param courseTitle Student's course name
     */
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

    /**
     * Validates student Start Date to only accept specific date format such as: DD.MM.YYYY or DD/MM/YYYY or DD-MM-YYYY
     * @param StartDate Student's start date
     */
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

    /**
     * Validates student email address to only accept email address format i.e. email@email.com
     * @param email Student's email address
     */
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

    /**
     * Validates student bursary value to only accept numeric values with following format a.b where a - stands for number before comma and b - stands for numbers after comma
     * @param bursary Student's bursary value
     */
    public void setBursary(float bursary){
        if(Float.toString(bursary).matches("\\d+\\.\\d{1,2}")) {
            this.bursary = bursary;
        }
        else{
            throw new IllegalArgumentException("Bursary price is not valid. Correct format is 0.0 i.e. 1000.0f which is £1000.0 ");
        }
    }



}
