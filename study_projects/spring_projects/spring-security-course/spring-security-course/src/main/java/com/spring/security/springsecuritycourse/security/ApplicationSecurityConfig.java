package com.spring.security.springsecuritycourse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

import static com.spring.security.springsecuritycourse.security.ApplicationRolesEnum.*;

/*
Spring security requiere de autenticacion Basic, el framework inmediatamente es agregado al proyecto aplica este
tipo de autenticacion a todos los request que lleguen a alguna de las rutas en puerto (username:password)

al indicarle a postman que la preticion requiere autorizacion, este crea una cabecera Authorization con el valor
del tipo de autorizacion
*/
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //con Ctrl+O se pueden ver los metodos que se estan heredando para asi sobreescribirlos
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //la configuracion debe declararse en orden
        http
                //autoriza a todos los usuarios el acceso a todos los endpoints
                .authorizeRequests()
                //la funcion antMatcher permite decirle a Spring que endpoints o rutas van a estar expuestas para
                //todos los usuarios o cuales podran ser accedidas por ciertos usuarios
                .antMatchers("/", "/api/v1/students/get/*").permitAll()
                .antMatchers("/api/v1/students/name/*", "/api/v1/students/create").hasRole(ADMIN.name())
                //indica que todos los request requieren de que se este autenticado
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                //indica el mecanismo de autenticacion en el caso es Basic Auth, este tipo de autenticacion no permite
                //realizar un logout, ya que el user y password son enviados en cada peticion luego del loggeo
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        Function<String, String> encoder = pass -> new BCryptPasswordEncoder(10).encode(pass);

        UserDetails cristian = User.builder()
                .username("cristianre")
                //el metodo encode es la forma en la que internamente spring encripta sus claves, este tipo de encriptado
                //es el mas comun, aunque la interfaz passwordEncoder contiene otros dos metodos abstractos para hacerlo
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .build();

        UserDetails veronica = User.builder()
                .username("veronica")
                //se puede crear una funcion para crear el metodo de encriptado, el cual se define antes de la clave
                .passwordEncoder(encoder)
                .password("hola")
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        //esta es la forma de cargar un usuario en memoria, esto es posible gracias a que la clase User de spring
        //security implementa la interfaz UserDetails y su vez la clase InMemoryUserDetailsManager es una implementacion
        //de la interfaz UserDetailsManager... por lo tanto esta configuracion se creara al alzar el contexto de spring
        return new InMemoryUserDetailsManager(cristian, veronica);

    }
}
