package com.exercise.SpringDataBook.student;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//es posible crear interfaces personalizadas para las funciones que deseeamos
//tener en nuestro repositorio, como por ejemplo extender de la interfaz CrudRepository
//que extiende de repository y dota la interfaz de los metodos crud,
//de esta misma manera se pueden generar diferentes tipos de interfaces en funcion de la
//necesidad del proyecto con implementaciones de interfaces como: PagingAndSortingRepositories,
//ReadOnlyRepository o SaveOnlyRepository como meros ejemplos

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //los metodos de busqueda son derivados de una consulta SQL segun los criterios
    //seleccionados como son el caso del nombre o el email, etc.
    //los @Query no son necesarios si la consulta se realiza con findBy
    //para consultas mas complejas se usa la anotacion
    List<Student> findByFirstName(String name);
    //@Query("select s from Student s where s.emailAdress =? 1")
    Optional<Student> findByEmailAdress(String email);

    //las consultas pueden ser concatenadas con And, Or, Between , LessThan , GreaterThan o Like
    //como una propiedad de expresion
    List<Student> findByFirstNameAndLastName(String lastName, String nombre);
    List<Student> findByFirstNameOrLastName(String name, String lastName);


    //cuando usamos la interfaz pageable como parametro, es importante tener claro
    //que está no esta vinculada con la consulta sino que por el contrario crea una serie de
    //restricciones y metadatos correspondientes a la consulta
    //que en el caso de retornar una pagina sera necesario realizar una consulta adicional para
    //traer los metadatos. otra opcion el retornar una list, aunque esta no devolveria los metadatos
    
    //Sort por el contrario ordenara los elementos segun el criterio enviado dentro del parametro
    List<Student> findByFirstName(String name, Sort sort);
}
