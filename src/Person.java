public class Person {
    private String name;
    protected String DOB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        DOB = dOB;
    }

    public String getAllDetail() {
        String data;
        String otherData;
        if (this instanceof Student) {
            otherData = ((Student) this).getSID();
        } else if (this instanceof Lecturer) {
            otherData = ((Lecturer) this).getPhoneNumber();
        } else {
            otherData = "";
        }
        data = name + ", " + DOB + ", " + otherData;
        return data;
    }
}
