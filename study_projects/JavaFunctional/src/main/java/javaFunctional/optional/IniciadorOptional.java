package javaFunctional.optional;

import java.util.Optional;

public class IniciadorOptional {
    public static void main(String args[]) {
        System.out.println("hola perras");
        crearOptional(null);
//        orelseThrowOptional(null);
        isPresentOptional("camilo");
    }

    public static void crearOptional(String name) {
        Optional<String> nombre = Optional.of(Optional.ofNullable(name).orElse("camilo torres"));
        System.out.println(nombre.get());
    }

    public static void orelseThrowOptional(String nombre) {
        Optional<String> name = Optional.ofNullable(Optional.ofNullable(nombre).orElseThrow(NullPointerException::new));
        System.out.println(name.get());
    }

    public static void isPresentOptional(String nombre) {
        boolean presente = Optional.ofNullable(nombre).isPresent();
        System.out.println(presente);

    }
}
