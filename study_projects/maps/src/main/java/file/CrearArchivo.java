package file;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CrearArchivo {

    private File createdFile(String pathName){
        File file = new File(pathName);
        try {
            if(file.createNewFile()){
                System.out.println("the file with name: " + file.getName() + " has been created");
            }else{
                System.out.println("the file: " + file.getName() + " already exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File genereteReport(List<Student> listStudents){
        File document = createdFile("/home/cristian/Escritorio/Down/StudentReport.xlsx");
        writeInFile(document, listStudents);
        return document;
    }

    private Iterator<Student> getDataReport(List<Student> listStudents) {
        Iterator<Student> studentIterator = listStudents.iterator();
        return studentIterator;
    }

    //this method Write above an exist file
    private void writeInFile(File file, List<Student> listStudents){
        try {
            FileWriter myWriter = new FileWriter(file);
            Iterator iterator = getDataReport(listStudents);
            while(iterator.hasNext()){
            myWriter.write(iterator.next().toString());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("A ocurrido un error");
            e.printStackTrace();
        }
    }
}
