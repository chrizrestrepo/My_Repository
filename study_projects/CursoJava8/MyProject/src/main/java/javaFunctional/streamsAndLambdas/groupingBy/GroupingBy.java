package javaFunctional.streamsAndLambdas.groupingBy;

import javaFunctional.streamsAndLambdas.Cliente;
import javaFunctional.streamsAndLambdas.DataGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingBy {

    public static void doGroupingBy(){
        System.out.println("---------------------- GroupingBy ----------------------");
        Map<Character, List<Cliente>> listAlfabetica = DataGenerator.generarLista()
                .stream()
                .collect(Collectors.groupingBy(element -> new Character(element.getNombre().charAt(0))));

        listAlfabetica.get('s')
                .stream()
                .forEach(e -> System.out.println("GRUPING-BY Example False: " + e.getNombre()));
    }
}
