package javaOthers;

import java.util.*;
import java.util.stream.Collectors;

public class ComponenteLogico1 {

    public static void main(String[] args){
        List<String> list = Arrays.asList("abc", "ujk", "zzy" , "ahj", "aaz" , "oip");
        convertList(list).forEach(System.out::println);
    }

    public static List<Integer> convertList(List<String> list){
        return list.stream()
                .map(charLine -> Arrays.stream(charLine.split(""))
                        .mapToInt(ComponenteLogico1::convertCharToInteger)
                        .sum())
                .sorted()
                .collect(Collectors.toList());
    }

    public static Integer convertCharToInteger(String character){
        Map<String, Integer> mapOfNumbers = new LinkedHashMap<>();
        mapOfNumbers.put("a", 1);
        mapOfNumbers.put("b", 2);
        mapOfNumbers.put("c", 3);
        mapOfNumbers.put("d", 4);
        mapOfNumbers.put("e", 5);
        mapOfNumbers.put("f", 6);
        mapOfNumbers.put("g", 7);
        mapOfNumbers.put("h", 8);
        mapOfNumbers.put("i", 9);
        mapOfNumbers.put("j", 10);
        mapOfNumbers.put("k", 11);
        mapOfNumbers.put("l", 12);
        mapOfNumbers.put("m", 13);
        mapOfNumbers.put("n", 14);
        mapOfNumbers.put("o", 15);
        mapOfNumbers.put("p", 16);
        mapOfNumbers.put("q", 17);
        mapOfNumbers.put("r", 18);
        mapOfNumbers.put("s", 19);
        mapOfNumbers.put("t", 20);
        mapOfNumbers.put("u", 21);
        mapOfNumbers.put("v", 22);
        mapOfNumbers.put("w", 23);
        mapOfNumbers.put("x", 24);
        mapOfNumbers.put("y", 25);
        mapOfNumbers.put("z", 26);
        return mapOfNumbers.get(character);
    }

}
