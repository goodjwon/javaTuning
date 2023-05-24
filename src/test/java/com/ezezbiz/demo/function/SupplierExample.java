package com.ezezbiz.demo.function;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.function.Supplier;


public class SupplierExample {

    @Test
    public void testSupplier(){
        assertThat(getDBConnectionUrlSupplier.get(), is(getDBConnectionUrl()));
        assertThat(getDBConnectionUrlSuppliers.get().size(), is(2));
    }

    static String getDBConnectionUrl() {
        return "jdbc://localhost:5432/users";
    }

    static Supplier<String> getDBConnectionUrlSupplier = ()
            -> "jdbc://localhost:5432/users";

    static Supplier<List<String>> getDBConnectionUrlSuppliers = ()
            -> List.of(
                    "jdbc://localhost:5432/users",
                    "jdbc://localhost:5432/users");
}
