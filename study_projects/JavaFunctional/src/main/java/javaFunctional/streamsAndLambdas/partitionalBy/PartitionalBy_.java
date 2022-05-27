package javaFunctional.streamsAndLambdas.partitionalBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionalBy_ {

    public static void doPartitionalBy(){
        System.out.println("---------------------- PartitionalBy ----------------------");
        List<Integer> numList = Arrays.asList(8,285,54,54,8,487,1,56,28,36,987,898,14,87,2,4587,15,23);

        Map<Boolean, List<Integer>> mayores = numList.
                stream()
                .collect(Collectors.partitioningBy(num -> num > 100));

        mayores.get(true)
                .stream()
                .forEach(list -> System.out.println("PARTITIONAL-BY Example True: " + list));

        System.out.println("----------------------");

        mayores.get(false)
                .stream()
                .forEach(l -> System.out.println("PARTITIONAL-BY Example False: " + l));
    }
}
