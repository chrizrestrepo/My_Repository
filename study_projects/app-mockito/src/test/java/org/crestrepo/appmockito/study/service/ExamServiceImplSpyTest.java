package org.crestrepo.appmockito.study.service;

import org.crestrepo.appmockito.study.Repository.ExamRepositoryImpl;
import org.crestrepo.appmockito.study.Repository.QuestionRepositoryImpl;
import org.crestrepo.appmockito.study.model.Exam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExamServiceImplSpyTest {

    /**
     * podemos usar spy() tambien a modo de anotaciones, lo cual cumpliria las veces de crearlo mediante codigo, al igual
     * que el metodo mock()... adicionalmente la injeccion de los @Spy se hace por medio de la misma anotacion @InjectMocks,
     * ya que no existe una para los @Spy
     */
    @Spy
    ExamRepositoryImpl examRepository;

    @Spy
    QuestionRepositoryImpl questionRepository;

    @InjectMocks
    ExamServiceImpl examService;

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
        List<String> questionList = Arrays.asList("what´s is your name", "what´s up");
        Mockito.doReturn(questionList).when(questionRepository).findQuestionByExamId(Mockito.anyLong());
        
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

        Mockito.verify(examRepository).findAll();
        Mockito.verify(questionRepository).findQuestionByExamId(Mockito.anyLong());
    }
}