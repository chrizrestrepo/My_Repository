package com.crestrepo.study.spring.data.jpa.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    private Long teacherId;
    private String firstName;
    private String lastName;

    /*
    el fecthType por defecto es LAZY por lo que no nos devolvera el resultado de todas las relaciones

    adicional la anotacion @JoinColumn en este caso nos permite pasarle el nombre de la columna con
    la cual vamos hacer match o mas bien quien será nuestra foreign Key y el nombre que deseemos ponerle
    a dicha columna en la entidad, es importante resaltar que ha nivel de base de datos vamos a ver una columna
    adicional en Course, ya que de esta manera se asegura una relacion entre en Teacher y Course a partir de una FK
    de Teacher, ya que de otra forma seria imposible. por esta razon esta se visualiza un poco diferente del @OneToMany
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private List<Course> listCourse;
}
