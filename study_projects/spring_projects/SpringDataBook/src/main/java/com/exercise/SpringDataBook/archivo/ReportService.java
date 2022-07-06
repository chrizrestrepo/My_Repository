package com.exercise.SpringDataBook.archivo;


import com.exercise.SpringDataBook.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Service
public class ReportService {

    public final StudentRepository repository;

    @Autowired
    public ReportService(StudentRepository repository) {
        this.repository = repository;
    }


    public File genereteReport() throws IOException {
        File document = createdFile("/home/cristian/Escritorio/Down/StudentReport.xlsx");
        writeInFile(document);
        return document;
    }

    /*this method create a File instance with a PathName
     */
    private File createdFile(String pathName){
        File file = new File(pathName);
        try {
            if (file.createNewFile()) {
                System.out.println("the file with name: " + file.getName() + " has been created");
            } else {
                System.out.println("the file: " + file.getName() + " already exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    //this method Write above an exist file
    private void writeInFile(File file) throws IOException {
            FileWriter myWriter = new FileWriter(file);
            repository.findAll()
                    .forEach(student -> {
                        try {
                            myWriter.write(student.toString());
                            myWriter.close();
                        } catch (IOException e) {
                            System.out.println("A ocurrido un error");
                        }
                    });
    }















        //metodo para leer del documento, el path es: "/home/cristian/Escritorio/Down/ensayo.txt"
    public void readFile(File file){
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //metodo para traer la informacion del documento, el path es: "/home/cristian/Escritorio/Down/ensayo.txt"
    public void getInfoDocument(String pathName){
        File file = new File(pathName);
        if(file.exists()) {
            System.out.println(file.getAbsolutePath());
            System.out.println(file.length());
        }else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
