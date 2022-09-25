package com.ezezbiz.demo.functions;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Job {

    private final String jobName;
    private final int salary;
    private final String company;

    @Builder
    public Job(String jobName, int salary, String company) {
        this.jobName = jobName;
        this.salary = salary;
        this.company = company;
    }
}
