public class Person {
    String name, gender, dob, address, postcode;

    Person(){

    }
    Person(String name, String gender, String dob, String address, String postcode){
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public String getDob(){
        return dob;
    }

    public String getAddress(){
        return address;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setName(String name){
        if(name.matches(".*[a-zA-Z]+.*")) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Course Title must only contain alphabet letters");
        }
    }

    public void setGender(String gender){
        if(gender.equals("M") || gender.equals("F")) {
            this.gender = gender;
        }
        else{
            throw new IllegalArgumentException("Invalid gender foramt. Use 'M' or 'F' (M - Male / F - Female");
        }
    }

    public void setDob(String dob){
        if(dob.matches("^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$")) {
            this.dob = dob;
        }
        else{
            throw new IllegalArgumentException("Available Date format: DD.MM.YYYY or DD/MM/YYYY or DD-MM-YYYY");
        }
    }

    public void setAddress(String address){

        if(address.matches(".*[a-zA-Z]+.*")) {
            this.address = address;
        }
        else{
            throw new IllegalArgumentException("Course Title must only contain alphabet letters");
        }
    }

    public void setPostcode(String postcode){
        if(postcode.matches("^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$")){
            this.postcode = postcode;
        }
        else{
            throw new IllegalArgumentException("Invalid postcode format. Example UK postcode i.e. SK1 3AL");
        }
    }
}
