package sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class controlador {
    public static void main(String args[]){
        listaIterator().stream().forEach(e->System.out.println(e));

    }

    public static List<String> listaIterator(){
        List<String> listaNombres = new ArrayList<>();
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("cristian");
        listaNombres.add("daniel");
        listaNombres.add("hamilton");
        listaNombres.add("veronica");

        Iterator<String> iterable = listaNombres.iterator();

        while(iterable.hasNext()){
            if(iterable.next().contains("c")){
                iterable.remove();
            }
        }

        Iterator<String> iterator = iterable;

        List<String> lista = new ArrayList<>();
        while(iterator.hasNext()){
            lista.add(iterator.next());
        }

        return lista;
    }
}
