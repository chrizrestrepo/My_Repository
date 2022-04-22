package org.cyrestrepo.test.springboot.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    /**
     * la libreria de SpringFox es aquella que nos hace las veces de integrar swagger con nuestro proyecto spring
     * por lo que para configurarla se hace necesario configurar un @Bean que retorne Docket.class, en donde se retorna un
     * Bean con toda la configuracion de la documentacion, que para el caso será Swagger, por otro lado, en el metodo apis()
     * se le envia como parametro la clase RequestHandlerSelectors.class la cual configura el package de las rutas en donde trabajaremos con
     * el framework de swagger, aunque este puede configurarse para que resiva cualquiera por medio del metodo any() al igual que el metodo paths,
     * en donde la clase PathSelectors.class mediante el metodo ant() se le indica la url.
     * @return Docket
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.cyrestrepo.test.springboot.app.controller"))
                .paths(PathSelectors.ant("/api/v1/account/*"))
                .build();
    }
}
