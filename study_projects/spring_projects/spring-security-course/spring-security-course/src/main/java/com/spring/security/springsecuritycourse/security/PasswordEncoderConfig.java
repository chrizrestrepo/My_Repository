package com.spring.security.springsecuritycourse.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    //aqui se crea el bean que encripta la informacion de la clave, el mas comunmente usado es BCrypt como el ejemplo,
    //sin embargo existen otros tipos de encriptacion de claves
    //el parametro que se le envia al constructor del encriptador, es la fuerza que se le da al encriptado
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
