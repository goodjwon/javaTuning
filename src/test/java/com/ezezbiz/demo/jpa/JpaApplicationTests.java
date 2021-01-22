package com.ezezbiz.demo.jpa;

import com.ezezbiz.demo.jpa.daos.UserRepository;
import com.ezezbiz.demo.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
class JpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenUserProfile_whenAddUser_thenCreateNewUser(){

        User user = new User(1, "John", "Doe","Programmer");
        userRepository.save(user);

        List<User> users = (List<User>) userRepository.findAll();

        System.out.println("============");
        users.forEach(System.out::println);
        System.out.println("============");

        assertFalse(users.isEmpty());

    }

}
