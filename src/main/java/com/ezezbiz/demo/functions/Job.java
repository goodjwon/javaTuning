package com.ezezbiz.demo.functions;

public class Job {

    private final String jobName;
    private final int salary;
    private final String company;

    public Job(String jobName, int salary, String company) {
        this.jobName = jobName;
        this.salary = salary;
        this.company = company;
    }

    public String getJobName() {
        return jobName;
    }

    public int getSalary() {
        return salary;
    }

    public String getCompany() {
        return company;
    }
}
