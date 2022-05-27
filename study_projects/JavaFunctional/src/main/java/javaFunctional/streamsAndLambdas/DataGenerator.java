package javaFunctional.streamsAndLambdas;

import javaFunctional.streamsAndLambdas.count.Count_;
import javaFunctional.streamsAndLambdas.distinct.Distinct_;
import javaFunctional.streamsAndLambdas.filter.Filter_;
import javaFunctional.streamsAndLambdas.findFirst.FindFirst_;
import javaFunctional.streamsAndLambdas.flatMap.FlatMap_;
import javaFunctional.streamsAndLambdas.forEach.ForEach_;
import javaFunctional.streamsAndLambdas.groupingBy.GroupingBy;
import javaFunctional.streamsAndLambdas.joining.Joining_;
import javaFunctional.streamsAndLambdas.map.Map_;
import javaFunctional.streamsAndLambdas.mapping.Mapping_;
import javaFunctional.streamsAndLambdas.matchers.Matchers;
import javaFunctional.streamsAndLambdas.minAndMax.MinAndMax;
import javaFunctional.streamsAndLambdas.parallelStream.ParallelStream_;
import javaFunctional.streamsAndLambdas.partitionalBy.PartitionalBy_;
import javaFunctional.streamsAndLambdas.peek.Peek_;
import javaFunctional.streamsAndLambdas.skipAndLimit.SkipAndLimit_;
import javaFunctional.streamsAndLambdas.sorted.Sorted_;
import javaFunctional.streamsAndLambdas.sumAverageAndRange.SumAverageAndRange_;
import javaFunctional.streamsAndLambdas.summarizingDouble.SummarizingDouble;
import javaFunctional.streamsAndLambdas.toSet.ToSet_;

import java.util.*;

public class DataGenerator {

    public static void main (String args[]) {
        Count_.doCount();
        Distinct_.doDistinct();
        Filter_.doFilter();
        FindFirst_.doFindFirst();
        FlatMap_.doFlatMap();
        ForEach_.doForEach();
        GroupingBy.doGroupingBy();
        Joining_.doJoining();
        Map_.doMap();
        Mapping_.doMapping();
        Matchers.doMatchers();
        MinAndMax.doMinAndMax();
        ParallelStream_.doParallelStream();
        PartitionalBy_.doPartitionalBy();
        Peek_.doPeek();
        SkipAndLimit_.doSkipAndLimit();
        Sorted_.doSorted();
        SumAverageAndRange_.doSumAverageAndRange();
        SummarizingDouble.doSummarizingDouble();
        ToSet_.doToSet();
    }

    public static String convertirAmayusculas (String cliente) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cliente.toUpperCase();
    }

    public static List<Cliente> generarLista() {
        List<Cliente> listClientes = new ArrayList<Cliente>();
        listClientes.add(new Cliente(1, "sage"));
        listClientes.add(new Cliente(2, "nakel"));
        listClientes.add(new Cliente(3, "shane"));
        listClientes.add(new Cliente(4, "paul"));
        listClientes.add(new Cliente(5, "chris"));
        listClientes.add(new Cliente(6, "shane"));
        listClientes.add(new Cliente(7, "shane"));

        return listClientes;
    }
}
