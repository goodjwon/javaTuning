package com.ezezbiz.demo.projects.contract;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class EnumContractApplicationTests {

    @Autowired
    private EnumContractRepository enumContractRepository;

    @Test
    public void add(){
        enumContractRepository.save(new EnumContract(
                "우아한짐카",
                1.0,
                EnumContract.CommissionType.MONEY,
                EnumContract.CommissionCutting.ROUND));

        EnumContract saved = enumContractRepository.findById(1L).orElse(null);
        System.out.println(saved.toString());
        assertThat(saved.getCommissionType(), is(EnumContract.CommissionType.MONEY));
        assertThat(saved.getCommissionCutting(), is(EnumContract.CommissionCutting.ROUND));
    }
}
