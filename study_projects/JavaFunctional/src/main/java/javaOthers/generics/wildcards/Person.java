package javaOthers.generics.wildcards;

public class Person {

    private String firstName;
    private String lastName;
    private Long identification;

    public Person(String firstName, String lastName, Long identification) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identification=" + identification +
                '}';
    }
}
