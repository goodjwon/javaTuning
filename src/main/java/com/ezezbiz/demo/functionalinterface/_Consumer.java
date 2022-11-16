package com.ezezbiz.demo.functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {
    public static void main(String[] args) {
        Customer goodjwon = new Customer("goodjwon", "01044445555");
        greetCustomer(goodjwon);
        greetCustomerV2(goodjwon, false);
        greetCustomerConsumer.accept(goodjwon);
        greetCustomerConsumerV2.accept(goodjwon, false);
    }

    //Functional interface multi argument
    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println("Hello " + customer.getCustomerName() +
                    ", thanks for registering phone number " +
                    (showPhoneNumber?customer.getCustomerPhoneNumber():"*********"));

    //Functional interface
    static Consumer<Customer> greetCustomerConsumer = customer ->
                System.out.println("Hello " + customer.getCustomerName() +
                        ", thanks for registering phone number " +
                        customer.getCustomerPhoneNumber());

    //Nomal java method
    static void greetCustomer(Customer customer) {
        System.out.println("Hello " +
                customer.getCustomerName() +
                ", thanks for registering phone number " +
                customer.getCustomerPhoneNumber());
    }

    static void greetCustomerV2(Customer customer, boolean showPhoneNumber) {
        System.out.println("Hello " + customer.getCustomerName() +
                ", thanks for registering phone number " +
                (showPhoneNumber?customer.getCustomerPhoneNumber():"*********"));
    }

    static class Customer {
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

        @Override
        public String toString() {
            return "Customer{" +
                    "customerName='" + customerName + '\'' +
                    ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                    '}';
        }
    }
}
