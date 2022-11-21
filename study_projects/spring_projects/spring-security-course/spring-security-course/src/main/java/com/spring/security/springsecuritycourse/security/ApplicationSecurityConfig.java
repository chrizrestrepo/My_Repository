package com.spring.security.springsecuritycourse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //con Ctrl+O se pueden ver los metodos que se estan heredando para asi sobreescribirlos
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //la configuracion debe declararse en orden, por lo tanto los antMatchers deben tener un orden para asi aplicar
        //sus reglas como es debido
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //esta es la forma en la cual spring genera los token por defecto
//                .and()
                .csrf().disable()//HELP.md: Proteccion contra ataques CSRF
                .authorizeRequests() //autoriza a todos los usuarios el acceso a todos los endpoints
                .antMatchers("/", "/api/v1/students/get/*").permitAll() //especifica que endpoints o rutas van a estar expuestas para todos los usuarios
                .antMatchers("/api/v1/students/name/*", "/api/v1/students/create").hasRole(ADMIN.name()) //especifica que endpoints o rutas van a estar expuestas a cierto usuario
//                .antMatchers(HttpMethod.POST, "/management/api/v1/**").hasAuthority(STUDENT_WRITE.getPermission()) //indica que metodos http pueden ser accedidos o usados por cierto permiso
//                .antMatchers(HttpMethod.DELETE, "/management/api/v1/**").hasAuthority(STUDENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/v1/**").hasAuthority(STUDENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/v1/**").hasAnyRole(ADMIN.name(), STUDENT.name(), ADMINTRAINEE.name()) //especifica que endpoints o rutas van a estar expuestas a ciertos usuarios
                .anyRequest() // mapea todos los request entrantes
                .authenticated() //especifica que las URL's pueden ser accedidas por cualquier usuario autenticado
                .and()
//                .httpBasic(); //HELP.md: tipos de Autenticacion - Basic Auth:
                .formLogin();
    }

    //Spring crea los roles de la siguiente manera ROLE_ + {nombre-role}
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails cristian = User.builder()
                .username("cristianre")
                .password(passwordEncoder.encode("password")) //el metodo encode es la forma en la que internamente spring encripta sus claves, este tipo de encriptado
                //.roles(ADMIN.name())                                    //es el mas comun, aunque la interfaz passwordEncoder contiene otros dos metodos abstractos para hacerlo
                .authorities(ADMIN.getGrantedAutories())
                .build();

        Function<String, String> encoder = pass -> new BCryptPasswordEncoder(10).encode(pass);
        UserDetails veronica = User.builder()
                .username("veronica")
                .passwordEncoder(encoder) //se puede crear una funcion para crear el metodo de encriptado, el cual se define antes de la clave
                .password("hola")
                .authorities(ADMINTRAINEE.getGrantedAutories())
                .build();

        UserDetails hamilton = User.builder()
                .username("hamilton")
                .passwordEncoder(encoder) //se puede crear una funcion para crear el metodo de encriptado, el cual se define antes de la clave
                .password("hola")
                .authorities(STUDENT.getGrantedAutories())// este metodo crea tanto el role como los permisos usando el metodo del enumerador
                .build();

        //esta es la forma de cargar un usuario en memoria, esto es posible gracias a que la clase User de spring
        //security implementa la interfaz UserDetails y su vez la clase InMemoryUserDetailsManager es una implementacion
        //de la interfaz UserDetailsManager... por lo tanto esta configuracion se creara al alzar el contexto de spring
        return new InMemoryUserDetailsManager(cristian, veronica, hamilton);

    }
}
