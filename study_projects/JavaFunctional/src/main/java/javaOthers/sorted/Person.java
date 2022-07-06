package javaOthers.sorted;

public class Person implements Comparable<Person>{

    private String identification;
    private String firstName;
    private String lastName;

    public Person(String identification, String firstName, String lastName) {
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "identification='" + identification + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return this.lastName.compareTo(person.getLastName());
    }
}
