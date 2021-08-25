package com.ezezbiz.demo.generic;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    public void givenNonGenericConstructor_whenCreateGenericEntry_thenOK(){
        GenericEntry<String> entry = new GenericEntry<String>(1);
        assertNull(entry.getDate());
        assertEquals(1, entry.getRank());
    }

    @Test
    public void givenGenericConstructor_whenCreateGenericEntry_thenOK(){
        GenericEntry<String> entry = new GenericEntry<String>("sample", 1);
        assertEquals("sample", entry.getDate());
        assertEquals(1, entry.getRank());
    }

    @Test
    public void givenGenericConstructorWithDifferentType_whenCreateGenericEntry_thenOK(){
        Product product = new Product("milk", 2.5);
        product.setSales(30);

        GenericEntry<Serializable> entry = new GenericEntry<Serializable>(product);

        assertEquals(product, entry.getDate());
        assertEquals(30, entry.getRank());
    }

    @Test
    public void givenGenericConstructor_whenCreateGenericEntryWithTwoTypes_thenOK(){
        MapEntry<String, Integer> entry = new MapEntry<String, Integer>("sample", 1);

        assertEquals("sample", entry.getKey());
        assertEquals(1, entry.getValue().intValue());
    }


}
