package com.example.relations;

import com.example.relations.model.entity.Phone;
import com.example.relations.model.entity.Product;
import com.example.relations.model.entity.Customer;
import com.example.relations.model.repository.PhoneRepository;
import com.example.relations.model.repository.ProductoRepository;
import com.example.relations.model.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class RelationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CustomerRepository customerRepository, PhoneRepository phoneRepository, ProductoRepository productoRepository){
		return args -> {
			Phone phone1 = new Phone(3007213545L);
			Phone phone2 = new Phone(3192912454L);
			Phone phone3 = new Phone(3137904618L);
			Phone phone4 = new Phone(3007509867L);
			Phone phone5 = new Phone(3200990787L);
			Phone phone6 = new Phone(3156735490L);

			phoneRepository.saveAll(Arrays.asList(phone1,phone2,phone3,phone4,phone5,phone6));

			Customer cristian = new Customer("cristian", "restrepo", "c@gmail.com");
			Customer veronica = new Customer("veronica", "giraldo", "verog@hotmail.com");
			Customer hamilton = new Customer("hamilton", "restrepo", "hrestre@gmail.com");
			Customer daniel = new Customer("daniel", "rojas", "cabe@yopmail.com");
			Customer santiago = new Customer("santiago", "morales", "bean@hotmail.com");
			Customer simon = new Customer("simon", "giraldo", "sigiral@gmail.com");
			Customer alonso = new Customer("alonso", "restrepo", "frestre@hotmail.com");
			Customer omaira = new Customer("omaira", "escobar", "omesoquen@hotmial.com");
			Customer carlos = new Customer("carlos", "castañeda", "carcas@gmail.com");

			customerRepository.saveAll(Arrays.asList(cristian, veronica, hamilton, daniel, santiago, simon, alonso, omaira, carlos));

			Product product1 = new Product("camiseta", "adidas", 50000);
			Product product2 = new Product("sudadera", "adidas", 100000);
			Product product3 = new Product("tennis", "adidas", 350000);
			Product product4 = new Product("camiseta", "nike", 70000);
			Product product5 = new Product("pantaloneta", "adidas", 70000);
			Product product6 = new Product("chaqueta", "adidas", 300000);
			Product product7 = new Product("tennis", "vans", 350000);
			Product product8 = new Product("gorra", "adidas", 50000);
			Product product9 = new Product("camiseta", "underarmor", 80000);
			Product product10 = new Product("camiseta", "reebok", 50000);
			Product product11 = new Product("camiseta", "primitive", 80000);
			Product product12 = new Product("tabla", "primitive", 200000);
			Product product13 = new Product("tabla", "element", 150000);
			Product product14 = new Product("tabla", "death wish", 170000);
			Product product15 = new Product("tabla", "creature", 200000);
			Product product16 = new Product("gorra", "santa cruz", 100000);
			Product product17 = new Product("gorra", "death wish", 80000);
			Product product18 = new Product("gorra", "vans", 120000);
			Product product19 = new Product("camiseta", "vans", 70000);
			Product product20 = new Product("camiseta", "clop", 40000);
			Product product21 = new Product("camiseta", "jordan", 60000);
			Product product22 = new Product("chaqueta", "primitive", 350000);
			Product product23 = new Product("reloj", "casio", 150000);
			Product product24 = new Product("bolso", "aether", 150000);
			Product product25 = new Product("gorra", "neva", 70000);
			Product product26 = new Product("zapatos", "huf", 250000);
			Product product27 = new Product("camiseta", "huf", 90000);
			Product product28 = new Product("camiseta", "palace", 150000);
			Product product29 = new Product("camiseta", "anti social club", 100000);
			Product product30 = new Product("camiseta", "dgk", 70000);
			Product product31 = new Product("tabla", "dgk", 150000);
			Product product32 = new Product("trucks", "independent", 200000);
			Product product33 = new Product("trucks", "thunder", 180000);
			Product product34 = new Product("trucks", "krux", 190000);
			Product product35 = new Product("gorra", "indepent", 80000);
			Product product36 = new Product("camiseta", "volcom", 80000);
			Product product37 = new Product("gorra", "volcom", 90000);
			Product product38 = new Product("pantalon", "volcom", 150000);
			Product product39 = new Product("pantalon", "levis", 180000);
			Product product40 = new Product("pantalon", "dgk", 200000);

			productoRepository.saveAll(Arrays.asList(
				product1,product2, product3, product4, product5, product6, product7, product8, product9, product10,
				product11, product12, product13, product14, product15, product16, product17, product18, product19, product20,
				product21, product22, product23, product24, product25, product26, product27, product28, product29, product30,
				product31, product32, product33, product34, product35, product36, product37, product38, product39, product40));
		};
	}

}
