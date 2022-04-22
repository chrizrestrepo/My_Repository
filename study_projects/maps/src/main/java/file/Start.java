package file;

import java.util.List;

public class Start {
    public static void main(String[] args){

        CrearArchivo fileGenerator = new CrearArchivo();
        List<Student> listStudent = generateListStudent();

        fileGenerator.genereteReport(listStudent);

    }

    public static List<Student> generateListStudent(){
        List<Student> studentList = List.of(
                new Student("cristian", "restrepo", 26),
                new Student("veronica", "giraldo", 24),
                new Student("La", "Altanera", 23),
                new Student("hamilton", "restrepo", 23),
                new Student("isabel", "uribe", 23),
                new Student("daniel", "rojas", 36),
                new Student("alonso", "restrepo", 62),
                new Student("omaira", "escobar", 59),
                new Student("alisson", "rios", 25));
        return studentList;
    }
}
