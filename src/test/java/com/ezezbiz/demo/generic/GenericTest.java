package com.ezezbiz.demo.generic;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class GenericTest {
    @Test
    public void givenNonGenericConstructor_whenCreateNonGenericEntry_thenOK(){
        Entry entry = new Entry("sample", 1);

        assertEquals("sample", entry.getData());
        assertEquals(1, entry.getRank());

    }

    @Test
    public void givenGenericConstructor_whenCreateNonGenericEntry_thenOK(){
        Product product = new Product("milk", 2.5);
        product.setSales(30);

        Entry entry = new Entry(product);

        assertEquals(product.toString(), entry.getData());
        assertEquals(30, entry.getRank());
    }
}
