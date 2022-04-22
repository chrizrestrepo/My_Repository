package file;

public class Student {
    private String firtsName;
    private String lastName;
    private Integer edad;

    public Student(String firtsName, String lastName, Integer edad) {
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.edad = edad;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firtsName='" + firtsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", edad=" + edad +
                "[^\n]" +
                '}';
    }
}
