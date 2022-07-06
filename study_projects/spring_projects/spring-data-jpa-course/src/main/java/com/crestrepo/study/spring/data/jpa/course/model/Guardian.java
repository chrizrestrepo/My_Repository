package com.crestrepo.study.spring.data.jpa.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
la anotacion @Embeddable sirve para indicar que un objeto va a ser integrado en otra entidad del mismo modo que integramos a
nuestro codigo o entidades atributos, por lo que este no es persistido como una entidad ya que no cuenta con la anotacion @Entity,
sino mas bien este es adignado a la entidad por medio de la anotacion @Embeded

adicional la anotacion @AttributeOverrides nos permite mapear los datos de la clase a columnas de la BD, por lo que en el momento
que se integren a alguna clase o entidad por medio de la anotacion @Embeded estos seran los mapeos de los campos en las columnas de
las entidades o tablas
*/
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
        @AttributeOverride(name = "email", column = @Column(name = "guardian_email")),
        @AttributeOverride(name = "mobila", column = @Column(name = "guardian_mobile"))
})
@Builder
public class Guardian {

    private String name;
    private String email;
    private Long mobile;
}
