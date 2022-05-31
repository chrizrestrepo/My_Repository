package javaFunctional.highOrderFunctions.consumer;

import java.util.function.Consumer;

public class Consumer_ {

    /**
     * la interfaz functional Consumer recibe un parametro de entrada y no retorna nada,
     * al observar su documentacion es evidente que esta recibe como parametro en el generic
     * (este egeneric indica que retornaria la funcion luego de resolverse su logica)
     * cualquier tipo de clase por eso su estereotipo es <T> el cual significa Type.
     * @param //Consumer<T>
     */
    public static void main(String[] args){
        printCostomer().accept(new Customer("cristian", "3192912454"));
    }

    public static Consumer<Customer> printCostomer() {
        return customer -> System.out.println(
                "customerName= "
                        .concat(customer.customerName)
                        .concat(" ")
                        .concat("customerPhone= ")
                        .concat(customer.customerPhone));
    }

    /**
     * esto es una inner class
     */
    static class Customer{
        private final String customerName;
        private final String customerPhone;

        public Customer(String customerName, String customerPhone) {
            this.customerName = customerName;
            this.customerPhone = customerPhone;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "customerName='" + customerName + '\'' +
                    ", customerPhone='" + customerPhone + '\'' +
                    '}';
        }
    }
}
