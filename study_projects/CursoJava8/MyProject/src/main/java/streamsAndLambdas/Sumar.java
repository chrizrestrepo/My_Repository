package streamsAndLambdas;

@FunctionalInterface
public interface Sumar {
    int sumar(int a, int b);
    default String textoSuma (int a , int b) {
        int c = a + b;
        return "la suma de A y B es igual a: " + c;
    }
}
