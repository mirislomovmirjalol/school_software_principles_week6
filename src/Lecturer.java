public class Lecturer extends Person {
    private String phoneNumber;

    public Lecturer(String name, String DOB, String phoneNumber) {
        setName(name);
        setDOB(DOB);
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
