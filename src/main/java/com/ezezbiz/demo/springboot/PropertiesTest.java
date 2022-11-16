package com.ezezbiz.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@Profile("local1")
public class PropertiesTest implements CommandLineRunner {
    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(PropertiesTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====================================================");
        System.out.println("Enviroment's Active Profile : " + Arrays.toString(env.getActiveProfiles()));
        System.out.println("defaultonly.name : " + env.getProperty("app.name"));
        System.out.println("testonly.name : " + env.getProperty("testonly.name"));
        System.out.println("devonly.name : " + env.getProperty("devonly.name"));
        System.out.println("profile-common.name : " + env.getProperty("profile-common.name"));
        System.out.println("=====================================================");
    }
}
