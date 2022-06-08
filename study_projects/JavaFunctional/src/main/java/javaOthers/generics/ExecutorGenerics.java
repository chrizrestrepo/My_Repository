package javaOthers.generics;

public class ExecutorGenerics {

    public static void main(String[] args){
        GenericClass<String> stringGenericClass = new GenericClass<>("cristian");
        stringGenericClass.printObject();

        GenericClass2<String, String, String, String> genericMessage = new GenericClass2<>("hola ", "mi nombre es: ", "Cristian ", "Restrepo");
        genericMessage.generateMessageWithGenerics();
    }
}
