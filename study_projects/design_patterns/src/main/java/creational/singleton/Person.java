package main.java.creational.singleton;

/*
la implementaion del patron singleton permite tener uan unica instancia de una clase,
para implementarlo es necesario crear un atributo llamado INSTANCE del mismo tipo de la
clase que sea estatico el cual sera precisamente la instancia que se reutilizara, adicional
se debe crear un contructor privado para que este no pueda ser usado con la sentencia new, por
lo que en el metodo getInstance se crea y reutiliza la instancia de la clase (el metodo getInstance
debe usar synchronized para que la instancia solo pueda ser accedida de manera sincronizada y que por
el contrario no se intente acceder a ella desde dos puntos al mismo tiempo)
 */
public class Person {
    private static Person INSTANCE;

    private String firtsName;
    private String lastName;
    private String phone;

    private Person() {}

    public synchronized static Person getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new Person();
        }
        return INSTANCE;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
