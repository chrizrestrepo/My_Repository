package javaOthers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComponenteLogico2 {

    public static void main(String[] args){
        List<Integer> listNumber = Arrays.asList(1,4,6,7,8,8,127,89,3);
        divideList(listNumber, 3).forEach(System.out::println);

        System.out.println(obtainBiggerNumber(divideList(listNumber, 3)));
    }

    public static Integer obtainBiggerNumber(List<List<Integer>> listOflist){
        return listOflist.stream()
                .map(listNumber -> listNumber.stream().mapToInt(num -> num).sum())
                .max(Comparator.comparing(Integer::intValue))
                .orElse(0);
    }

    public static List<List<Integer>> divideList(List<Integer> list, Integer size){
        List<List<Integer>> resultList = new ArrayList<>();
        if(list.size() % size != 0){
            throw new IllegalArgumentException("the given number isn'n module of: " + list.size());
        }
        int index = 0;
        int position = size;
        while(index <= list.size() - size){
            resultList.add(list.subList(index, position));
            index = position;
            position = position + size;
        }
        return resultList;
    }
}
