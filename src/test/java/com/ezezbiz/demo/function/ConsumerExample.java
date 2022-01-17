package com.ezezbiz.demo.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ConsumerExample {

    @Test
    public void testCustomer(){
        greetCustomer(new Customer("Jwon", "9999"));
    }

    @Test
    public void testConsumer(){
        greetCustomerCustomer.accept(new Customer("Jwon", "9999"));
    }
    
    static Consumer<Customer> greetCustomerCustomer = customer
            -> System.out.println("Hello " + customer.getCustomerName()
            +", thanks for registering phone number "
            + customer.getCustomerPhoneNumber());


    static void greetCustomer(Customer customer){
        System.out.println("Hello " + customer.getCustomerName()
                +", thanks for registering phone number "
                + customer.getCustomerPhoneNumber()
        );
    }


    class Customer{
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getCustomerPhoneNumber() {
            return customerPhoneNumber;
        }
    }
}
