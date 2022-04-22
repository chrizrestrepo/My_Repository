package org.crestrepo.appmockito.study.service;

import org.crestrepo.appmockito.study.Repository.ExamRepository;
import org.crestrepo.appmockito.study.Repository.ExamRepositoryImpl;
import org.crestrepo.appmockito.study.Repository.QuestionRepository;
import org.crestrepo.appmockito.study.Repository.QuestionRepositoryImpl;
import org.crestrepo.appmockito.study.model.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
esta anotación es la version más optimizada del siguiente método: MockitoAnnotations.openMocks(this)
 que es el que usamos en el metodo @BeforeEach para el reconocimiento de anotaciones en la clase test,
 esta anotacion hace las veces de integrar las anotaciones de Mockito con las de Jupiter de JUnit
 */
@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    ExamRepository examRepository;
    @Mock
    QuestionRepository questionRepository;

    @Captor
    ArgumentCaptor<Long> captor;

    /**
    la anotacion @InjectMocks cumple la funcion por medio de injeccion de dependencias de injectarle
     a la clase anotada con dicha anotacion, las demas dependencias que se encuentran anotadas con
     @Mock, esto siempre y cuando se encuentren dentro del constructor de la clase, como se ve en el
     metodo @BeforeEach en donde esta injeccion se hace de manera manual por medio del constructor
     */
    @InjectMocks
    ExamServiceImpl examService;


    /**
    el orden de las intancias del metodo anotado con @BeforeEach importa, ya que la invocacion de estas
     se hace de manera secuencial como en cualquier pieza de codigo, por lo tanto si hay alguna dependencia entre los
     objetos creados en el metedo, es importante recordar lo anterior, ya que podria presentarse alguna exception
     */
    @BeforeEach
    void setUp() {
        /*
        para habilitar las pruebas con anotaciones es necesario incluir la siguiente pieza de codigo
        para que Mockito reconozca y pueda ejecutar los test, ya que sino se hace saca un NullpointerExaception
         */
        /**
         * MockitoAnnotations.openMocks(this);
         */
//        repository = Mockito.mock(ExamRepository.class);
//        questionRepository = Mockito.mock(QuestionRepository.class);
//        service = new ExamServiceImpl(repository, questionRepository);
    }

    /**
        el motodo Mockito.mock sirve para crear un objeto simulado de alguna clase en concreto,
        lo cual nos permite crear una instancia del mismo para ejecutar algún metodo.
        adicional el Mockito.when nos permite indicarle a la clase test, que cuando se ejecute
        un método X se haga una un retorno esperado con el método thenReturn, por lo que todo el comportamiento
        de la clase es totalmente simulado. ademas el metodo thenReturn() solo puede ser invocado cuando el
        metodo retorna algun valor, por lo que en el caso de los metodos void no aplica.

        por otro lado solo se pueden simular los metodos que son de tipo publico o default, los metodos private
        y staticos no se pueden simular
         */
    @Test
    void findByNameTest() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);

        Exam exam = examService.findByName("history");

        assertAll(
                () -> assertNotNull(exam),
                () -> assertEquals(5L, exam.getId()),
                () -> assertEquals("history", exam.getName()));
    }

    @Test
    void findByNameTestException() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> examService.findByName("spanish"));
        assertEquals("the element doesn't exist", exception.getMessage());
    }

    /**
    la clase Mockito cuenta tambien con un metodo llamado: any() el cual se aplica a cualquier tipo de dato,
     esto con el fin de que el metodo mockiado reciba cualquier cosa, por lo que cuando hacemos el llamado al
     metodo este funciona con cualquier elemento o parametro que le enviemos, asi que podemos
    enviarle en el metodo any(Cualquier.class) cualquier tipo de clase o dato que necesitemos.

    por otro lado es importante que al momento de crear los mocks con el metodo when, se tenga en cuenta el codigo
    ya que es necesario mockear cada uno de los llamados de los metodos para asi simularlos con alguna respuesta
    deseada con el metodo thenReturn()
     */
    @Test
    void findByNameExamQuestionTest() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.when(questionRepository.findQuestionByExamId(Mockito.anyLong())).thenReturn(DataTest.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("history");
        assertAll(
                () -> assertEquals(3, exam.getQuestions().size()),
                () -> assertTrue(exam.getQuestions().stream()
                        .anyMatch(question -> question.contains("when the last time that human visit the moon"))));
    }

    /**
    el metodo verify() de la clase Mockito sirve para verificar si un metodo si se invoco en el test,
    este funciona enviandole al metodo la instancia de la clase para luego ejecutar el metodo
     */
    @Test
    void findByNameExamQuestionTestWithVerify() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.when(questionRepository.findQuestionByExamId(Mockito.anyLong())).thenReturn(DataTest.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("history");
        assertAll(
                () -> assertEquals(3, exam.getQuestions().size()),
                () -> assertTrue(exam.getQuestions().stream()
                        .anyMatch(question -> question.contains("when the last time that human visit the moon")))
        );
        Mockito.verify(examRepository).findAll();
        Mockito.verify(questionRepository).findQuestionByExamId(5L);
    }

    @Test
    @Disabled
    void findNonExistentExamVerify() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.when(questionRepository.findQuestionByExamId(Mockito.anyLong())).thenReturn(DataTest.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("develop");
        assertNull(exam);
        Mockito.verify(examRepository).findAll();
        Mockito.verify(questionRepository).findQuestionByExamId(5L);
    }

    /**
    en el siguiente metodo pasa la validacion del verify del repositorio de preguntas, ya que en el WHEN
    que hace referencia a la ejecucion del metodo se le esta pasando un examen con preguntas, por lo que en
    este caso a pesar de que en el When y el ThenReturn no se esten enviando, en el metodo save donde se crea
    la instancia si se esta ejecuatndo el metodo del questionRepository que es el que guarda las preguntas en
    dicho repositorio =
     */
    @Test
    void saveExamTest() {
        Exam otherExam = DataTest.EXAM;
        otherExam.setQuestions(DataTest.QUESTIONS);

        Mockito.when(examRepository.save(Mockito.any(Exam.class))).thenReturn(DataTest.EXAM);
        Exam exam = examService.save(otherExam);
        assertAll(
                () -> assertNotNull(exam),
                () -> assertEquals(40L, exam.getId()),
                () -> assertEquals("marketing", exam.getName())
        );
        Mockito.verify(examRepository).save(Mockito.any());
        Mockito.verify(questionRepository).saveQuestions(Mockito.anyList());
    }

    @Test
    void exceptionManageTest() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.when(questionRepository.findQuestionByExamId(Mockito.anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> examService.findQuestionsByExamName("history"));
    }


    /**
     * el metodo argThat() es un metodo que tiene sus implementaciones en las clases Mockito y ArgumentMatchers, y este
     * sirve para validar los argumentos que se estan enviando y esperamos en un metodo a traves de una expresion lambda
     * adicional este metodo al igual que el metodo any() tambien tiene sus implementaciones con tipo de dato como por ejemplo
     * byteThat(), charThat(), doubleThat(), etc.
     *
     * ademas existe tambien el metodo eq() el cual es un equals, el cual sirve para validar si el valor que entra como argumento
     * es igual al esperado, al igual que el anterior este tambien tiene sus implementaciones en ambas clases.
     */
    @Test
    void testArgumentMatchers() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.when(questionRepository.findQuestionByExamId(Mockito.anyLong())).thenReturn(DataTest.QUESTIONS);
        examService.findQuestionsByExamName("history");

        Mockito.verify(examRepository).findAll();
        Mockito.verify(questionRepository).findQuestionByExamId(Mockito.argThat(id -> id != null && id.equals(5L) && id >= 5L));
        Mockito.verify(questionRepository).findQuestionByExamId(Mockito.eq(5L));
    }

    /**
     * La clase ArgumentCaptor sirve para capturar el valor del argumento que ingresa dentro del metodo
     * mediante el metodo capture(). adicional esta clase recibe un generic en donde le indicamos el tipo
     * de dato que se capturara.
     *
     * En el ejemplo se observa como se captura el valor del id que ingresa en el metodo verify del repositorio
     * y se realiza un assertion de este al mismo tiempo habiendo capturado su valor
     *
     * tambien podemos usar la clase captor por medio de anotaciones, en este caso la anotación es @Captor y se
     * implementa igualmente con la misma clase y generico a nivel de atributo de la clase. para el ejemplo se comenta
     * la implementacion con instancia y se ejecuta por medio de anotacion
     */
    @Test
    void testArgumentCaptor() {
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        examService.findQuestionsByExamName("Math");

        //ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(questionRepository).findQuestionByExamId(captor.capture());
        assertEquals(10L, captor.getValue());
    }

    /**
     * los metodos do*** se usan unicamente con los metodos void, esto es para darles una especie de comportamiento,
     * por lo que el metodo doThrow() se ejecuta antes del metodo when(), el cual cambia un poco su comportamiento y
     * se ejecuta muy parecido al metodo verify() de Mockito, invocando el metodo despues de pasarle la instancia del
     * objeto a simular
     *
     * es importante recordar que el metodo doThrow() al igual que el metodo verify() se ejecuta unicamente cuando
     * se hace el llamado al metodo que hace referencia despues del metodo when()
     */
    @Test
    void testDoThrow(){
        Exam exam = DataTest.EXAM;
        exam.setQuestions(DataTest.QUESTIONS);
        Mockito.doThrow(IllegalArgumentException.class).when(questionRepository).saveQuestions(Mockito.anyList());

        assertThrows(IllegalArgumentException.class, () -> examService.save(exam));
    }

    /**
     * el metodo doAnswer() funciona similar a un predicate, por lo cual en dicho metodo se usan expresiones lambda para
     * ejecutar un metodo de una clase anonima, y asi retornar un valor que cumpla con la condicion dada en el predicado, que en
     * este caso es un operador terniario
     *
     * al igual que el metodo doThrow() este funciona de la misma manera recibiendo el mock dentro del metodo when() para luego
     * ejecutar el metodo que se desea
     *
     * el metodo getArgument() de la clase Answer.class, que es aquella que nos brinda la respuesta, nos devuelve el valor del argumento
     * dado por medio del indice segun la cantidad de argumentos dados, por lo que es similar a una lista si asi queremos verlo
     *
     * en el ejemplo se observa que si el id del examen es igual a 5L pues devuelve las preguntas, sino en ese caso devuelve una coleccion vacia
     */
    @Test
    void testDoAnswer(){
        Mockito.when(examRepository.findAll()).thenReturn(DataTest.EXAMS);
        Mockito.doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return id == 5L? DataTest.QUESTIONS : Collections.EMPTY_LIST;
        }).when(questionRepository).findQuestionByExamId(Mockito.anyLong());

        Exam exam = examService.findQuestionsByExamName("history");
        assertAll(
                () -> assertEquals(5L, exam.getId()),
                () -> assertEquals("history", exam.getName()),
                () -> assertEquals(3, exam.getQuestions().size()));
    }

    /**
     * la forma en que se crean los spy() es muy similar a la de un mock, simplemete invocamos el metodo y le enviamos la clase que deseas crearle
     * el espia y listo.
     *
     * los spy() son una especie de mock hibrido, en pocas palabras un espia es un clon de la clase en donde de cierta manera se ejecutan los metodos
     * reales de la clase, lo cual quiere decir que un mock() simula en comportamiento y el espia ejecuta el metodo real a traves de un clon de la
     * instancia, adicional hay que tener en cuenta que a la hora de crear un spy() debemos hacerlo con clases reales, ya que vamos a hacer el llamado
     * a metodos que existen en una instancia, por lo tanto no debemos enviarle interfaces o clases abstractas que no implementen ninguna logica,
     * por otro lado el hecho de que el spý() sea una especie de hibrido nos permite ejecutar los metodos reales y simular otros al mismo tiempo
     *
     * cuando implementamos un spy() y deseamos simular una parte del codigo con el metodo Mockito.when(), al momento de hacer el llamado del metodo
     * la api hace el llamado del metodo real en el metodo when pero genera la simulacion del metodo. por lo que este se estaria llamando dos veces,
     * este es un comportamiento raro que tiene el spy() aunque es algo de la API, aunque de igual modo funciona y envia el el metodo mockeado
     * en ves del real. aunque para que erto no suceda es mejor usar alguno de los metodos do, que para el ejemplo se usa el doReturn()
     *
     */
    @Test
    void testSpy() {
        ExamRepository eRepository = Mockito.spy(ExamRepositoryImpl.class);
        QuestionRepository qRepository = Mockito.spy(QuestionRepositoryImpl.class);
        ExamService examService = new ExamServiceImpl(eRepository, qRepository);

        List<String> questionList = Arrays.asList("what´s is your name", "what´s up");
        Mockito.doReturn(questionList).when(qRepository).findQuestionByExamId(Mockito.anyLong());

        /**
         ** este es el metodo que se llama dos veces **
         Mockito.when(qRepository.findQuestionByExamId(Mockito.anyLong())).thenReturn(questionList);
         */

        Exam exam = examService.findQuestionsByExamName("history");
        assertAll(
                () -> assertEquals(2, exam.getQuestions().size()),
                () -> assertEquals("history", exam.getName()),
                () -> assertEquals(5L, exam.getId()),
                () -> assertTrue(exam.getQuestions().stream()
                        .anyMatch(question -> question.contains("what´s is your name")))
        );
    }
}