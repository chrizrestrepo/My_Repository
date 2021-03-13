package com.accenture.prueba.data;

import com.accenture.prueba.cliente.Cliente;
import com.accenture.prueba.cliente.ClienteRepository;
import com.accenture.prueba.producto.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataPrueba {

    /*
	@Bean
	public CommandLineRunner crearListaDeProductos(ProductoRepository repository){
		return args -> {
			com.accenture.prueba.producto.Producto camiseta = new com.accenture.prueba.producto.Producto("camiseta", 35000);
			com.accenture.prueba.producto.Producto camibuso = new com.accenture.prueba.producto.Producto("camibuso", 45000);
			com.accenture.prueba.producto.Producto camisa = new com.accenture.prueba.producto.Producto("camisa", 50000);
			com.accenture.prueba.producto.Producto pantalon = new com.accenture.prueba.producto.Producto("pantalon", 80000);
			com.accenture.prueba.producto.Producto sudadera = new com.accenture.prueba.producto.Producto("sudadera", 40000);
			com.accenture.prueba.producto.Producto pantaloneta = new com.accenture.prueba.producto.Producto("pantaloneta", 35000);
			com.accenture.prueba.producto.Producto gorra = new com.accenture.prueba.producto.Producto("gorra", 25000);
			com.accenture.prueba.producto.Producto medias = new com.accenture.prueba.producto.Producto("medias", 50000);
			com.accenture.prueba.producto.Producto boxer = new com.accenture.prueba.producto.Producto("boxer", 10000);
			com.accenture.prueba.producto.Producto reloj = new com.accenture.prueba.producto.Producto("reloj", 55000);

			repository.saveAll(
					List.of(camiseta,camibuso,camisa,pantalon,sudadera,pantaloneta,gorra,medias,boxer,reloj)
			);
		};
	}
    */


    @Bean
    public CommandLineRunner crearClientes (ClienteRepository clienteRepository){
        return args -> {
            Cliente cristian = new Cliente(12345L, "cristian","carrera 11# 14-08");
            Cliente veronica = new Cliente(23456L, "veronica", "carrera 11# 14-08");
            Cliente camilo = new Cliente(34566L, "camilo", "transversal 5# 5-45");
            Cliente daniel = new Cliente(37865L, "daniel", "calle 78# 56-45");
            Cliente camila = new Cliente(34675L, "camila", "carrera 5# 50-82");
            Cliente valeria = new Cliente(56577L, "valeria", "circular 7# 67-40");
            Cliente susana = new Cliente(98765L, "susana", "calle 5# 78-12");

            clienteRepository.saveAll(
                    List.of(cristian,veronica,camilo,daniel,camila,valeria,susana)
            );
        };
    }
}
