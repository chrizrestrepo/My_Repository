package javaFunctional.streamsAndLambdas.matchers;

import java.util.Arrays;
import java.util.List;

public class Matchers {

    public static void doMatchers(){
        System.out.println("---------------------- AllMatch, AnyMatch And NoneMatch ----------------------");
        List<Integer> numberlist = Arrays.asList(10000,9800,200,500,7200,6900);

        boolean allMatch = numberlist
                .stream()
                .allMatch(numero -> numero < 10000);
        System.out.println("ALLMATCH Example: " + allMatch);

        boolean anyMatch = numberlist
                .stream()
                .anyMatch(number -> number > 9900);
        System.out.println("ANYMATCH Example: " + anyMatch);

        boolean noneMatch = numberlist
                .stream()
                .noneMatch(number -> number > 10000);
        System.out.println("NONEMATCH Example: " + noneMatch);
    }
}
