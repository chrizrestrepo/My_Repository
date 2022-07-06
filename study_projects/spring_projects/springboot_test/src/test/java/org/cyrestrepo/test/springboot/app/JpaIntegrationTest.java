package org.cyrestrepo.test.springboot.app;

import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * la anotación @DataJpaTest nos permite hacer test sobre todo el contexto de persistencia de JPA,
 * ademas habilita la base de datos en memoria de prueba y habilita los repositorios de Spring estereotipados en
 * el código. por lo que nuestros repositorios pueden ser inyectados en el contexto de pruebas, ya que estos están
 * registrados en el contexto de spring
 */
@DataJpaTest
public class JpaIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    /**
     * el contexto de pruebas de spring al momento de levantarse con la BD en memoria H2, va y consulta el archivo import.sql
     * para ejecutar las sentencias a la base de datos, la cual en primera instancia elimina las tablas de primerazo, por lo que
     * seguidamente crea las tablas con los datos para luego al finalizar la prueba eliminar las tablas nuevamente, después de ejecutar
     * las consultas a probar
     */
    @Test
    //@Disabled
    void findByIdTest() {
        Optional<Account> account = accountRepository.findById(1L);
        assertTrue(account.isPresent());
        assertEquals("cristian", account.orElseThrow().getPersonName());
    }

    @Test
    void findByPersonNameWhenPersonDoesNotExistTest() {
        Optional<Account> account = accountRepository.findByPersonName("jhon");
        assertThrows(NoSuchElementException.class, account::orElseThrow);
        assertFalse(account.isPresent());
    }

    @Test
    void findAllTest() {
        List<Account> accountList = accountRepository.findAll();
        assertAll(
                () -> assertFalse(accountList.isEmpty()),
                () -> assertEquals(2, accountList.size()));
    }

    @Test
    void saveAccountTest() {
        Account account = new Account(null, "hamilton", BigDecimal.valueOf(1500L));
        accountRepository.save(account);
        assertAll(
                () -> assertTrue(accountRepository.findAll().stream().anyMatch(acc -> acc.getPersonName().equalsIgnoreCase("hamilton"))),
                () -> assertSame(accountRepository.findByPersonName("hamilton").orElseThrow(), account),
                () -> assertEquals(3, accountRepository.findAll().size()));
    }

    @Test
    void updateAccountTest() {
        Account account = new Account(null, "hamilton", BigDecimal.valueOf(1500L));
        accountRepository.save(account);
        assertAll(
                () -> assertTrue(accountRepository.findAll().stream().anyMatch(acc -> acc.getPersonName().equalsIgnoreCase("hamilton"))),
                () -> assertSame(accountRepository.findByPersonName("hamilton").orElseThrow(), account),
                () -> assertEquals(3, accountRepository.findAll().size()));

        account.setBalance(BigDecimal.valueOf(3000L));
        accountRepository.save(account);
        assertEquals(3000, accountRepository.findByPersonName("hamilton").orElseThrow().getBalance().intValue());
    }

    @Test
    void deleteTest() {
        Account account = accountRepository.findByPersonName("cristian").orElseThrow();
        accountRepository.delete(account);
        assertThrows(NoSuchElementException.class, () -> accountRepository.findByPersonName("cristian").orElseThrow());
        assertEquals(1, accountRepository.findAll().size());

    }
}
