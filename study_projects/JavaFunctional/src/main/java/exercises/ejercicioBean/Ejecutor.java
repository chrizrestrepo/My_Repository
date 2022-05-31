package exercises.ejercicioBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Ejecutor {
    public static void main(String args[]){
        Colegio colegio = new Colegio("inem");
        Curso cursoCalculo = new Curso("calculo", colegio);
        cursoCalculo.setFechaInicio(LocalDate.parse("2020-01-01"));
        cursoCalculo.setFechaFin(LocalDate.parse("2020-06-01"));
        DisponibilidadCurso(cursoCalculo);
        List<Curso> listaCursos = Arrays.asList(cursoCalculo);
        colegio.setListaCursos(listaCursos);
        Profesor profesorCalculo = new Profesor("camilo", "cardona", "123");
        profesorCalculo.setCurso(cursoCalculo);
        Alumno alumnoCalculo = new Alumno("camila", "gomez", "234");
        alumnoCalculo.setNombreColegio(colegio);
        alumnoCalculo.setNombreCurso(cursoCalculo);


//        System.out.println(cursoCalculo.getEstadoCurso());
        cambioEstadoCursoSegunInstitucion(colegio);
    }

    public static void DisponibilidadCurso(Curso curso){
        if(curso.getFechaInicio().isAfter(LocalDate.now()) || curso.getFechaFin().isBefore(LocalDate.now())){
            curso.setEstadoCurso(Curso.EstadoCurso.INACTIVO);
        }
       if(curso.getFechaInicio().isBefore(LocalDate.now()) && curso.getFechaFin().isAfter(LocalDate.now())){
           curso.setEstadoCurso(Curso.EstadoCurso.ACTIVO);
       }
    }

    public static void cambioEstadoCursoSegunInstitucion(Colegio colegio){
        colegio.getListaCursos().stream()
                .filter(e->!e.getNombreColegio().equals("inem"))
                .forEach(e->{
            cambioEstadoCurso(e);
            System.out.println(e.getEstadoCurso());
        });
    }

    public static void cambioEstadoCurso(Curso curso){
        if (curso.getEstadoCurso().equals(Curso.EstadoCurso.ACTIVO)) {
            curso.setEstadoCurso(Curso.EstadoCurso.INACTIVO);
        } else if (curso.getEstadoCurso().equals(Curso.EstadoCurso.INACTIVO)) {
            curso.setEstadoCurso(Curso.EstadoCurso.ACTIVO);
        }
    }
}


