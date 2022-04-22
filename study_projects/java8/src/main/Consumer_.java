package main;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Consumer_ {

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

    public static List<Customer> listCustomer(){
        return Arrays.asList(
                new Customer("cristian", "3192912454"),
                new Customer("hamilton", "3192912454"),
                new Customer("veronica", "3192912454"));
    }

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
