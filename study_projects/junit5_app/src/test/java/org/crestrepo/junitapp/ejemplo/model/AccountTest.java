package org.crestrepo.junitapp.ejemplo.model;

import org.crestrepo.junitapp.ejemplo.exception.InsufficientAmmount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


class AccountTest {

    Account account;
    /*
    la anotacion @BeforeAll y @AfterAll es comun para todos los test y es un metodo de la instancia
    por lo que este metodo debe ser static, adicional este metodo a diferencia de los demas
    Each se ejecuta una unica vez en la clase
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("init AccountTest Class");
    }

    /*
        la anotación @BeforeEach sirve para ejecutar una misma pieza de codigo en cada uno de los
        Test, este codigo se ejecuta antes de cada Test, lo cual maneja el ciclo de vida del objeto
        ya que este inmediatamente finaliza la prueba se autodestruye
         */
    @BeforeEach
    void initTest(){
        account = new Account("cristian",new BigDecimal("500.4"));
    }
    /*
    la anotacion @AfterEach se usa para ejecutar una pieza de codigo justo despues de terminar cada Test
    es similar al @BeforeEach
     */
    @AfterEach
    void tearDown(){
        System.out.println("test finish");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("finish AccountTest class");
    }

    /*
    el metodo fail() se usa para crear un error en la prueba de manera intencionada, adicional
    la anotacion @Disabled se usa para deshabilitar una prueba, mientras corregimos nuestros
    errores de codigo y asi evitar que el proyecto no compile por culpa del test
     */
    @Test
    @Disabled
    public void numbersEquals(){
        fail();
        assertEquals(20, 20);
    }

    /*
    la anotación @RepeatedTest() se usa en los casos en los que se desea repetir un test n cantidad
    de veces indicandole un valor... adicional por injeccion de dependencias podemos enviarle al
    metodo como parametro la clase RepetitionInfo, la cual nos permitira aparte de tener la informacion
    de la repeticion, ejecutar ciertas piezas de codigo mediante alguna validacion como en el ejemplo
     */
    @RepeatedTest(value = 5)
    public void numbersEqualsRepatedTest(RepetitionInfo info){
        if(info.getCurrentRepetition() == 2){
            System.out.println("I'm the second repetition");
        }
        assertEquals(20, 20);
    }
    /*
    la anotacion @DisplayName sirve para darle un nombre personalizado al test el cual
    se muestra en en la ejecucion en ves del nombre del metodo
     */
    @Test
    @DisplayName(value = "valid that account Name is the same of test")
    void AccountNameTest() {
        //se usa la instancia account del @BeforeEach
        assertEquals(account.getPersonName(), "cristian");
        assertEquals(account.getBalance().doubleValue(), 500.4);
        assertTrue(account.getPersonName().equalsIgnoreCase("CRISTIAN"));
    }

    @Test
    void AccountBalanceTest() {
        assertEquals(account.getBalance().doubleValue(), 500.4);
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) <= 0);
    }

    /*
    el siguiente test pasa ya que la validación a nivel de java se hace por referencia mas no por valor lo
    que quiere decir que ambas cuentas son diferentes, ya que las intancias tienen espacio reservados en
    memoria diferentes... para hacer dicha validacion de las instancias sin validar su referencia es
    necesario sobreescribir el metodo equals
     */
    @Test
    void ReferenceAccountTest() {
        Account account = new Account("veronica",new BigDecimal("1000.500"));
        Account account2 = new Account("veronica",new BigDecimal("1000.500"));

        assertNotEquals(account2, account);
    }

    @Test
    void debitAccountTest() {
        Account account = new Account("andres", new BigDecimal("1000.12345"));
        account.debit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(900, account.getBalance().intValue());
        assertEquals("900.12345", account.getBalance().toPlainString());
    }

    @Test
    void creditAccountTest() {
        Account account = new Account("camilo", new BigDecimal("1000.12345"));
        account.credit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(1100, account.getBalance().intValue());
        assertEquals("1100.12345", account.getBalance().toPlainString());
    }

    /*
    el metodo assertThrows retorna una instancia de la excepcion, por lo que en este caso
    por esa razon se crea el objeto exception apartir de la excepcion capturada
     */
    @Test
    void insufficientAmmountExceptionTest() {
        Account account = new Account("cristian", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(InsufficientAmmount.class, () ->
           account.debit(new BigDecimal("1100.100")));
        assertEquals("insufficient amount", exception.getMessage());
    }

    @Test
    void transferMoneyTest() {
        Account origin = new Account("cristian restrepo", new BigDecimal("2500.12"));
        Account destiny = new Account("veronica giraldo", new BigDecimal("5000.2"));

        Bank bank = new Bank();
        bank.setName("Bancolombia");
        bank.transferMoney(origin, destiny, new BigDecimal("200.00"));
        assertEquals(5200, destiny.getBalance().intValue());
        assertEquals("2300.12", origin.getBalance().toPlainString());
    }

    @Test
    void validateRelationAccountAndBankTest() {
        Account origin = new Account("cristian restrepo", new BigDecimal("2500.12"));
        Account destiny = new Account("veronica giraldo", new BigDecimal("5000.2"));

        Bank bank = new Bank();
        bank.addAccount(origin);
        bank.addAccount(destiny);
        bank.setName("Bancolombia");

        assertEquals(2, bank.getAccountList().size());
        assertEquals("Bancolombia", origin.getBank().getName());
        assertEquals("cristian restrepo", bank.getAccountList().stream()
                .filter(account -> account.getPersonName().equalsIgnoreCase("cristian restrepo"))
                .findFirst().get().getPersonName());
        assertTrue(bank.getAccountList().stream()
                .anyMatch(account -> account.getPersonName().equalsIgnoreCase("veronica giraldo")));
    }

    /*
    el metodo assertAll permite ejecutar varias pruebas unitarias de manera anidada en una sola ejecucion
    por medio de expresiones lambda separadas por coma, estas funciones no reciben ningun parametro de entrada
    adicional permite rastrear que pruebas fallaron, ya que en el flujo normal cuando una prueba falla solo
    muestra esta ultima, ya que las ejecuciones se realizan de manera secuencial como en cualquier pieza de codigo
     */
    @Test
    void validateRelationAccountAndBankWithAssertAll() {
        Account origin = new Account("cristian restrepo", new BigDecimal("2500.12"));
        Account destiny = new Account("veronica giraldo", new BigDecimal("5000.2"));

        Bank bank = new Bank();
        bank.addAccount(origin);
        bank.addAccount(destiny);
        bank.setName("Bancolombia");

        assertAll(
                () -> assertEquals(2, bank.getAccountList().size()),
                () -> assertEquals("Bancolombia", origin.getBank().getName()),
                () -> assertEquals("cristian restrepo", bank.getAccountList().stream()
                        .filter(account -> account.getPersonName().equalsIgnoreCase("cristian restrepo"))
                        .findFirst().get().getPersonName()),
                () -> assertTrue(bank.getAccountList().stream()
                        .anyMatch(account -> account.getPersonName().equalsIgnoreCase("veronica giraldo"))));
    }

    /*
    las clases de Test pueden tener Inner class para agrupar ciertos test de tipo o similares en una clase que
    los ejecute dentro de la misma, esto se logra con la anotacion @Nested y seria algo similar a lo siguiente:

    @Nested
    class TestWithNested{
        @Test...
    }

    La anotacion @Tag se usa para dar una etiquetacion a los diferentes metodos de la clase en la que se realizan las pruebas
    adicionalmente esta anotacion se puede usar a nivel de inner class o metodo, por lo que en ese caso si quieres solo ejecutar
    las test que contengan cierto tag lo podemos hacer. ademas se puede poner varios tags a un mismo metodo o inner clase

    para ejecutar las pruebas por tags solo es necesario irse a la configuracion de la clase test de intellij, y cambiar la
    opcion para que no sea por metodo sino por tag
     */

    @Tag("Parameterized")
    @Nested
    class ParameterizedTestClass{

        /*
        la anotacion @ParameterizedTest permite crear test enviando diferentes tipos de fuentes de dato como prueba,
        siempre va acompanado de alguna fuente con la antocion @Source, aunque esta ultima puede ser de cualquier tipo
        como en el ejemplo en donde se usa @ValueSource y otras que se componen de la misma manera con la palabra Source...
        adicional en el metodo de prueba los valores del @ValueSource se estan generando a partir de injeccion de
        dependencias a traves del parametro del metodo.
        Al igual que la anotacion @RepeatTest este ejecuta la prueba segun la cantidad de parametros que hay en la anotacion,
        por lo que se genera un nodo de ejecuciones en donde podemos ver cual pasa y cual no, segun el dato
         */
        @ParameterizedTest
        @ValueSource(strings = {"100", "200", "300", "500", "700", "1000"})
        void debitAccountWithParametrizedTest(String value) {
            Account account = new Account("andres", new BigDecimal("1000.12345"));
            account.debit(new BigDecimal(value));
            assertNotNull(account.getBalance());
            assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0 );
        }

        /*
        adicional podemos enviar la data de prueba a partir de metodos tambien ayudandonos con la anotacion
        @MethodSource, el cual permite enviar la data a traves del nombre del metodo como se en el ejemplo, adicional
        el metodo a usar en dicha anotacion debe ser static... no se hace necesario enviar la lista de elementos como
        parametro, ya que estas anotaciones source funcionan como una lista a interior, por lo que la anotacion sabe
        como enviar cada parametro
         */
        @ParameterizedTest(name = "test with index: {index} and value {argumentsWithNames}")
        @MethodSource("generateDataTest")
        @Disabled
        void debitAccountWithParametrizedAndMethodSourceTest(String value) {
            Account account = new Account("andres", new BigDecimal("1000.12345"));
            account.debit(new BigDecimal(value));
            assertNotNull(account.getBalance());
            assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0 );
        }

        List<String> generateDataTest(){
            return Arrays.asList("100", "200", "300", "500", "700", "1000");
        }
    }

    @Nested
    @Tag("timeout")
    class TimeOutTest{
            /*
       la anotacion @Timeout sirve para hacer test a las clases de las cuales esperamos que se ejecuten
       en un tiempo determinado, este recibe como parametro el tiempo como un Integer o int, adicional
       podemos darle una unidad de tiempo con la clase TimeUnit
        */
        @Test
        @Timeout(5)
        void timeoutTest() throws InterruptedException{
            TimeUnit.SECONDS.sleep(4);

        }


        @Test
        @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
        void timeoutTest2() throws InterruptedException{
            TimeUnit.SECONDS.sleep(3);
        }

        /*
        existe tambien el metodo assertion para validar timeout el cual es: assertTimeOut y recibe dos parametros
        los cuales son el tiempo en Duration y una expresion lambda con la funcion o metodo que deseamos ejecutar
         */
        @Test
        void timeOutWithAssertionTest(){
            assertTimeout(Duration.ofSeconds(5), () -> {
                TimeUnit.SECONDS.sleep(5);
            });
        }
    }




}