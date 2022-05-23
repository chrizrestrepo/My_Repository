package main.java.behavioral.observer;

public enum SemaforoStateEnumType {


    ROJO("rojo"),
    VERDE("verde"),
    AMARILLO("amarillo");

    private final String status;

    SemaforoStateEnumType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
