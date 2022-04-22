package org.cyrestrepo.test.springboot.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyrestrepo.test.springboot.app.Data;
import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.model.TransferDTO;
import org.cyrestrepo.test.springboot.app.service.AccountServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;


/**
 * la anotacion @WebMvcTest es la que le indica al contexto de pruebas que se realizaran Test
 * de tipo MVC para la clase dada en el parametro de la anotacion
 */
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    /**
     * para el contexto de pruebas de controladores MVC se debe inyectar la clase MockMvc la cual
     * incluye todo el contexto de pruebas de tipo de REST
     */
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountServiceImpl accountService;

    private ObjectMapper objectMapper;



    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * el metodo perform() de la clase MockMvc.class nos haces las veces de ejecutar o lanzar una peticion
     * http hacia un endpoint dado, por lo que el este recibe como argumento la clase MockMvcRequestBuilders.class
     * en donde por medio del metodo statico get() se le esta indicando que el consumo se hara a traves del metodo get(),
     * pasandole como argumento la ruta que se desea probar.
     * el metodo andExpect() es el que hace las validaciones en frente al consumo de la ruta
     * la clase MockMvcResultMatchers.class es la que nos ayuda con las validaciones frente a la respuesta que nos envia la
     * aplicacion, por lo que para el ejemplo se esta validando el status, el mediaType y por ultimo con el metodo statico
     * jsonPath() se valida a partir de un string cual es el valor de la respuesta en un campo dado
     */
    @Test
    void getAccountByIdTest() throws Exception {
        when(accountService.findById(1L)).thenReturn(Data.ACCOUNT_001.orElseThrow());
        mvc.perform(get("/api/v1/account/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personName").value("Cristian"));
        verify(accountService).findById(1L);
    }

    @Test
    void transferTest() throws Exception {
        TransferDTO transferDTO = new TransferDTO(1L, 2L, new BigDecimal("200"), 1L);
        //en este paso al momento de mandar el Body del request es necesario convertir o serializar el objeto a tipo JSON, ya que el
        //contentType es: APPLICATION_JSON. por lo que usamos el metodo writeValueAsString(transferDTO) tal cual el ejemplo,
        //este metodo lanza la excepcion JsonProcessingException. para el ejemplo queda con Exception, ya que el metodo perform
        //lanza esta excepcion que es la padre de las demas
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transferDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("transfer succeed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transaction.amount").value(200));
    }

    /**
     * En el test usamos el metodo hasSize() que es de la clase Matchers
     * @throws Exception
     */
    @Test
    void findAllTest() throws Exception {
        when(accountService.findAll()).thenReturn(List.of(Data.ACCOUNT_001.orElseThrow(), Data.ACCOUNT_002.orElseThrow()));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/account/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].personName").value("Cristian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].personName").value("Veronica"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    void saveTest() throws Exception {
        Account accountTest = new Account(null, "cristian", new BigDecimal("5000"));
        when(accountService.save(any())).then(invocation -> {
            Account account = invocation.getArgument(0);
            account.setId(10L);
            return account;
        });

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountTest)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personName", Matchers.is("cristian")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance", Matchers.is(5000)));
        verify(accountService).save(any(Account.class));
    }
}