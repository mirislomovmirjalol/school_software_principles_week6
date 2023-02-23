public class Student extends Person{
    private String SID;
    public Student(String name, String DOB, String SID) {
        setName(name);
        setDOB(DOB);
        setSID(SID);
    }
    public String getSID() {
        return SID;
    }
    public void setSID(String SID) {
        this.SID = SID;
    }
}
