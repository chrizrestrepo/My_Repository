package javaFunctional.highOrderFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IniciadorHighOrderFuntions implements ISumar {
    public static void main(String args[]){
        IniciadorHighOrderFuntions funciones = new IniciadorHighOrderFuntions();
        System.out.println("---------------------- Metodo normal ----------------------");
        System.out.println(funciones.sumar(10,10));

        System.out.println("---------------------- Metodo Interfaz Funcional ----------------------");
        System.out.println(funciones.sumarNumeros(20,20));

        System.out.println("---------------------- High Order Functions ----------------------");
        ISumar sumar = (a,b)->a+b+100;
        System.out.println(funciones.sumarNumerosConInterfaz(sumar,50,50));

        System.out.println("---------------------- High Order Functions ----------------------");
        System.out.println("* Interfaz Function *");
        /*
        Function<T parametro, R resultado> {
            R.apply(parametro);
        }
        */
        Function<String, String> convertirAMayusculas = e->e.toUpperCase();
        funciones.imprimirTexto(convertirAMayusculas, "cristian");

        System.out.println("---------------------- High Order Functions ----------------------");
        /*
        BiFunction<T parametro, U parametro2, R resultado> {
            R.apply(parametro, parametro2);
        }
        */
        /*
        Predicate<T parametro> {
            Boolean.test(parametro);
        }
        */
        System.out.println("* Interfaz BiFunction *");
        List<String> listaNombres = Arrays.asList("anderson", "Dayana", "Cristian", "daniel", "Santiago", "Sara", "Diana");

        BiFunction<List<String>, Predicate<String>, List<String>> biFunction;
        biFunction = (lista, predicado) -> lista.stream().filter(e->predicado.test(e)).collect(Collectors.toList());
        System.out.println(biFunction.apply(listaNombres, e->e.length()<=5));



    }

    public int sumar(int a, int b){
        return a+b;
    }


    @Override
    public int sumarNumeros(int a, int b) {
        return a+b;
    }

    public int sumarNumerosConInterfaz(ISumar suma, int a, int b){
        return suma.sumarNumeros(a,b);
    }

    public void imprimirTexto(Function<String, String> imprimir, String texto){
         System.out.println(imprimir.apply(texto));
    }


}
