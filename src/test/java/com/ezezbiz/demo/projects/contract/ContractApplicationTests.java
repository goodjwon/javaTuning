package com.ezezbiz.demo.projects.contract;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class ContractApplicationTests {

    @Autowired
    private ContractRepository repository;

    @Test
    public void add(){
        Contract contract = new Contract(
                "우아한짐카",
                1.0,
                "percent",
                "round"
        );
        repository.save(contract);
        Contract saved = repository.findAll().get(0);
        assertThat(saved.getCommission(), is(1.0));
    }
}
