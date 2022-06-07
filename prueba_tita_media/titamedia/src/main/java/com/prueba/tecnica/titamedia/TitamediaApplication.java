package com.prueba.tecnica.titamedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TitamediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitamediaApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner saveSomeDepts(UserRepository repository, BankRepository bankRepository) {
//		return args -> {
//			Bank bancolombia = new Bank(BANCOLOMBIA);
//			Bank jfk = new Bank(JFK);
//			Bank bancoDeOccidente =  new Bank(BANCO_DE_OCCIDENTE);
//			Bank bancoDeBogota = new Bank(BANCO_DE_BOGOTA);
//			Bank davivienda = new Bank(DAVIVIENDA);
//			bankRepository.saveAll(
//					Arrays.asList(bancolombia, jfk, bancoDeOccidente, bancoDeBogota, davivienda)
//			);
//
//			User camilo = new User("camilo", List.of(
//					new Debt(bancolombia, 1000000L),
//					new Debt(jfk, 500000L),
//					new Debt(bancolombia, 300000L),
//					new Debt(bancoDeBogota, 200000L),
//					new Debt(bancoDeOccidente, 100000L)));
//			User cristian = new User("cristian", List.of(
//					new Debt(bancolombia, 320000L),
//					new Debt(jfk, 500000L),
//					new Debt(bancolombia, 2700000L),
//					new Debt(jfk, 200000L),
//					new Debt(davivienda, 3000000L)));
//			User hamilton =  new User("hamilton", List.of(
//					new Debt(davivienda, 470000L),
//					new Debt(davivienda, 590000L),
//					new Debt(bancolombia, 700000L)));
//			repository.saveAll(
//					Arrays.asList(camilo, cristian, hamilton)
//			);
//		};
//	}

}
