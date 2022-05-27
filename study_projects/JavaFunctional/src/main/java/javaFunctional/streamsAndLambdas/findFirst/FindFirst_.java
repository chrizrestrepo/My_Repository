package javaFunctional.streamsAndLambdas.findFirst;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

public class FindFirst_ {

    public static void doFindFirst(){
        System.out.println("---------------------- FindFirst ----------------------");

        Cliente costumer = DataGenerator.generarLista()
                .stream()
                .filter(cliente -> cliente.getNombre().equals("shane"))
                .findFirst()
                .orElse(new Cliente(564684, "daniel"));

        System.out.println("FINDFIRST Example: " + "ID: " + costumer.getIdentificacion() + " NAME: " + costumer.getNombre());

        Cliente costumer2 = DataGenerator.generarLista()
                .stream()
                .filter(cliente -> cliente.getNombre().equals("ronald"))
                .findFirst()
                .orElse(new Cliente(564684, "daniel"));

        System.out.println("FINDFIRST Example: " + "ID: " + costumer2.getIdentificacion() + " NAME: " + costumer2.getNombre());
    }

}
