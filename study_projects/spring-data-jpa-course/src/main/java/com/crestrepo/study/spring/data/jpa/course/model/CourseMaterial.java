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
public class CourseMaterial {

    @Id
    @SequenceGenerator(name = "course_material_sequence", sequenceName = "course_material_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    /*
    el cascadeType permite a la entidad o en este caso el repositorio ejecutar operaciones en cascada, cuando se ejecuta algun
    metodo del repositorio, haciando las veces de mediacion entre las capacidades de los repositorios que se relacionan mediante
    las anotaciones de mapeo entre entidades, por ejemplo: guardar en BD la entidad Course que se le envia como parametro en la
    construccion de la entidad CourseMaterial sin necesidad de ejecutar el metodo save() dos veces por repositorio

    el atributo optional nos permite indicarle a JPA que un atributo es requerido o no en la creacion de la entidad, en este caso
    al indicarle que es False la aplicacion nos obliga a incluir el material del curso cada que vayamos a crear un CourseMaterial
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;
}
