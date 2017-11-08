public class Person {
    String name, gender, dob, address, postcode;


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
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setDob(String dob){
        this.dob = dob;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
}
