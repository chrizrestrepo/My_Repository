package streamsAndLambdas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Iniciador implements Sumar{

    public static void main (String args[]) {
        System.out.println("hola mundo");

        Sumar suma = (a,b) -> {
            a = b + b;
            System.out.println("El valor de A es: " + a);
            b = b + b + a;
            System.out.println("El valor de B es: " + b);

            return a + b;
        };

        Sumar suma2 = (a,b) -> (a+b);

        Iniciador iniciador = new Iniciador();

        System.out.println(iniciador.textoSuma(90,56));

        System.out.println(suma.sumar(7,8));
        System.out.println(suma2.sumar(7,9));

        System.out.println("---------------------- ForEach ----------------------");
        generarLista().stream().forEach(cl -> System.out.println("FOREACH Example: " +  "ID: " + cl.getIdentificacion() + " NAME: " + cl.getNombre()));

        System.out.println("---------------------- Map ----------------------");
        List<Object> lista = generarLista().stream().map(cliente -> cliente.getNombre()).collect(Collectors.toList());
        lista.stream().forEach(cl -> System.out.println("MAPA Example: " + cl));

        System.out.println("---------------------- Filter ----------------------");
        List<Cliente> nombres = generarLista().stream().filter(cl -> cl.getNombre().equals("sage")).collect(Collectors.toList());
        nombres.stream().forEach(cliente -> System.out.println("FILTER Example: " + "ID: " + cliente.getIdentificacion() + " NAME: " + cliente.getNombre()));

        System.out.println("---------------------- FindFirst ----------------------");
        Cliente costumer = generarLista().stream().filter(cliente -> cliente.getNombre().equals("shane")).findFirst().orElse(new Cliente(564684, "daniel"));
        System.out.println("FINDFIRST Example: " + "ID: " + costumer.getIdentificacion() + " NAME: " + costumer.getNombre());
        Cliente costumer2 = generarLista().stream().filter(cliente -> cliente.getNombre().equals("ronald")).findFirst().orElse(new Cliente(564684, "daniel"));
        System.out.println("FINDFIRST Example: " + "ID: " + costumer2.getIdentificacion() + " NAME: " + costumer2.getNombre());

        System.out.println("---------------------- FlatMap ----------------------");
        List<List<String>> listaNombres = new ArrayList<List<String>>(Arrays.asList(
                new ArrayList<String>(Arrays.asList("camilo", "anderson", "andres", "santiago")),
                new ArrayList<String>(Arrays.asList("daniela", "camila", "maria", "monica"))));

        List<String> listaUnica = listaNombres.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        listaUnica.stream().forEach(l -> System.out.println("FLATMAP Example: " + l));

        System.out.println("---------------------- Peek ----------------------");
        List<Cliente> listaDeClientes = generarLista().stream().peek(cliente -> cliente.setNombre(cliente.getNombre() + " Apellidos")).collect(Collectors.toList());
        listaDeClientes.stream().forEach(cliente -> System.out.println("PEEK Example: " + cliente.getNombre()));

        System.out.println("---------------------- Count ----------------------");
        long cantidadShanes = generarLista().stream().filter(cliente -> cliente.getNombre().equals("shane")).count();
        System.out.println("COUNT Example: " + cantidadShanes);

        System.out.println("---------------------- Skip And Limit ----------------------");
        Integer[] numeros = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> listaNumeros = Arrays.stream(numeros).skip(3).limit(2).collect(Collectors.toList());
        listaNumeros.stream().forEach(numero -> System.out.println("SKIP AND LIMIT Example: " + numero));

        System.out.println("---------------------- Sorted ----------------------");
        List<Cliente> listaOrdenada = generarLista().stream().sorted(Comparator.comparing(Cliente::getNombre)).collect(Collectors.toList());
        listaOrdenada.stream().forEach(cliente -> System.out.println("SORTED Example: " + "ID: " + cliente.getIdentificacion() + " NAME: " + cliente.getNombre()));

        System.out.println("---------------------- Min And Max ----------------------");
        Cliente clientMin = generarLista().stream().min(Comparator.comparing(Cliente::getIdentificacion)).orElse(null);
        Cliente clientMax = generarLista().stream().max(Comparator.comparing(Cliente::getIdentificacion)).orElse(null);
        System.out.println("MIN Example: " + clientMin.getNombre());
        System.out.println("MAX Example: " + clientMax.getNombre());

        System.out.println("---------------------- Distint ----------------------");
        List<String> clientesUnicos = generarLista().stream().map(cliente -> cliente.getNombre()).distinct().collect(Collectors.toList());
        clientesUnicos.stream().forEach(cliente -> System.out.println("DISTINT Example: " + "ID: " + cliente));

        System.out.println("---------------------- AllMatch, AnyMatch And NoneMatch ----------------------");
        List<Integer> numberlist = Arrays.asList(10000,9800,200,500,7200,6900);

        boolean allMatch = numberlist.stream().allMatch(numero -> numero < 10000);
        System.out.println("ALLMATCH Example: " + allMatch);
        boolean anyMatch = numberlist.stream().anyMatch(number -> number > 9900);
        System.out.println("ANYMATCH Example: " + anyMatch);
        boolean noneMatch = numberlist.stream().noneMatch(number -> number > 10000);
        System.out.println("NONEMATCH Example: " + noneMatch);

        System.out.println("---------------------- Sum, Average And Range ----------------------");
        double promedio = generarLista().stream().mapToInt(Cliente::getIdentificacion).average().orElse(0);
        System.out.println("AVERAGE Example: " + promedio);
        int sumatoria = generarLista().stream().mapToInt(Cliente::getIdentificacion).sum();
        System.out.println("SUM Example: " + sumatoria);
        System.out.println("RANGE Example: " + IntStream.range(0,1000).sum());

        System.out.println("---------------------- Joining ----------------------");
        String cadenaNombres = generarLista().stream().map(Cliente::getNombre).collect(Collectors.joining(" - ")).toUpperCase();
        System.out.println("JOINING Example: " + cadenaNombres);

        System.out.println("---------------------- ToSet ----------------------");
        Set<String> setNombres = generarLista().stream().map(Cliente::getNombre).collect(Collectors.toSet());
        setNombres.stream().forEach(nombre -> System.out.println("TOSET Example: " + nombre));

        System.out.println("---------------------- SummarizingDouble ----------------------");
        DoubleSummaryStatistics estadisticas = generarLista().stream().collect(Collectors.summarizingDouble(Cliente::getIdentificacion));
        System.out.println("SUMMARIZING-DOUBLE Example: " + estadisticas.getMax() + " " + estadisticas.getMin() + " " + estadisticas.getAverage() + " " + estadisticas.getCount() + " " + estadisticas.getSum());

        System.out.println("---------------------- PartitionalBy ----------------------");
        List<Integer> numList = Arrays.asList(8,285,54,54,8,487,1,56,28,36,987,898,14,87,2,4587,15,23);
        Map<Boolean, List<Integer>> mayores = numList.stream().collect(Collectors.partitioningBy(num -> num > 100));
        mayores.get(true).stream().forEach(list -> System.out.println("PARTITIONAL-BY Example True: " + list));
        System.out.println("----------------------");
        mayores.get(false).stream().forEach(l -> System.out.println("PARTITIONAL-BY Example False: " + l));

        System.out.println("---------------------- GroupingBy ----------------------");
        Map<Character, List<Cliente>> listAlfabetica = generarLista().stream().collect(Collectors.groupingBy(element -> new Character(element.getNombre().charAt(0))));
        listAlfabetica.get('s').stream().forEach(e -> System.out.println("GRUPING-BY Example False: " + e.getNombre()));

        System.out.println("---------------------- Mapping ----------------------");
        List<String> clientList = generarLista().stream().collect(Collectors.mapping(Cliente::getNombre, Collectors.toList()));
        clientList.stream().forEach(e -> System.out.println("MAPPING Example False: " + e));

        System.out.println("---------------------- ParallelStream ----------------------");
        long tiempo1 = System.currentTimeMillis();
        generarLista().stream().forEach(e -> convertirAmayusculas(e.getNombre()));
        long tiempo2 = System.currentTimeMillis();
        System.out.println("tiempo Stream normal: " + (tiempo1-tiempo2));
        tiempo1 = System.currentTimeMillis();
        generarLista().parallelStream().forEach(e -> convertirAmayusculas(e.getNombre()));
        tiempo2 = System.currentTimeMillis();
        System.out.println("tiempo Stream paralelo: " + (tiempo1-tiempo2));

    }

    private static String convertirAmayusculas (String cliente) {
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

    @Override
    public int sumar(int a, int b) {
        return 0;
    }
}
