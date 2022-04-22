package com.crestrepo.study.spring.data.jpa.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    /*
    para crear una relacion Biderecional, es necesario incluir nuevamente la anotacion en OnoToOne o la que se este usando,
    en el otro lado de la relacion en donde se incluira el atributo mappedBy, en donde le indicamos el
    nombre del atributo que lo esta mapeando a traves de la anotacion @JoinColumn

    Dato: solo es posible usar el fetchType en la clase que tiene la anotacion @JoinColumn, recordar que la clase
    CourseMaterial tiene definido el fetchTYpe EAGER. por lo que si hacemos un findAll() de Course, unicamente nos devolveria
    los cursos sin sus CourseMaterial,por esta razon en el ejemplo se usa una relacion Bidireccional, ya que esta nos permite
    usar dicho fetchType definido
     */
    @OneToOne(mappedBy = "course")
    private CourseMaterial material;


    /*
    la relacion @ManyToOne se construye totalmente igual a la @OneToMany, sino que por razones de semantica esta se hace
    más fácil de digerir o interpretar de este modo, ya que en la BD lo veriamos exactamente igual a como se ve en la entidad,
    lo que sucede es que en este caso la dueña de la relacion es el lado del Many, por lo que este sería quien podría desencadenar
    las operaciones en cascadas hacia la entidad Teacher. asi que esto no quiere decir que sea una camisa de fuerza la forma en la que
    relacionemos cada parte del modelo sino más bien la forma en la que queremos y deseamos acceder e interactuar con los distintos modelos

    se comentan las siguientes lineas de codigo, ya que es más conveniente que la relacion sea una @OneToMany, ya que el Teacher es quien
    deberia tener el poder de ejecutar operaciones sobre la entidad Course y no este sobre él
     */
    //@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //@JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    //private Teacher teacher;
}
