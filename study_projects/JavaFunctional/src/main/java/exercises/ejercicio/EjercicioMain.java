package exercises.ejercicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjercicioMain {
    public static void main(String args[]){
        List<Estudiante> estudianteList = Arrays.asList(
                new Estudiante("camilo", "matematicas"),
                new Estudiante("daniel", "fisica"),
                new Estudiante("anderson", "ciencias"),
                new Estudiante("santiago", "matematicas"),
                new Estudiante("cristian", "fisica"),
                new Estudiante("jeff", "sociales"),
                new Estudiante("carlos", "sociales"),
                new Estudiante("andres", "fisica"),
                new Estudiante("juan", "ciencias"),
                new Estudiante("jeyson", "matematicas")
                );

        List<Estudiante> estudianteLista = new ArrayList<>();
        estudianteLista.add(new Estudiante("camila", "fisica"));
        estudianteLista.add(new Estudiante("daniela", "fisica"));
        estudianteLista.add(new Estudiante("maria", "fisica"));


        List<Estudiante> list = estudianteList.stream().filter(estudiante -> estudiante.getMateria().equals("fisica")).collect(Collectors.toList());
        //estudianteLista.addAll(list);
        //estudianteLista.stream().forEach(e->System.out.println("lista inicial: " + e.getNombre()));
        list.stream().forEach(e-> estudianteLista.add(e));
        estudianteLista.stream().forEach(e->System.out.println("lista peek: " + e.getNombre()));
        System.out.println("-----------------------------------------------");
        addToList(estudianteLista, list.stream());
        estudianteLista.stream().forEach(e->System.out.println("lista funcion: " + e.getNombre()));

    }

    public static<T> void addToList(List<T> lista, Stream<T> fuente) {
        fuente.collect(Collectors.toCollection(()-> lista));
    }
}
