package com.ezezbiz.demo.jpa;

import com.ezezbiz.demo.jpa.daos.TeamRepository;
import com.ezezbiz.demo.jpa.daos.UserRepository;
import com.ezezbiz.demo.jpa.entity.Team;
import com.ezezbiz.demo.jpa.entity.User;
import com.ezezbiz.demo.jpa.entity.UserType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
class JpaApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenUserProfile_whenAddUser_thenCreateNewUser(){

        Team team = new Team("teamA");
        User user = new User("John", "Doe","Programmer", UserType.Admin, team);
        teamRepository.save(team);
        userRepository.save(user);

        Optional<User> findUser = userRepository.findById(user.getId());
        System.out.println(findUser);


        System.out.println("* findUser ============");
        assertFalse(findUser.isEmpty());
        System.out.println("============");

        //List<User> users = (List<User>) userRepository.findAll();

        System.out.println("============");
        //users.forEach(System.out::println);
        System.out.println("============");

        //assertFalse(users.isEmpty());

    }

}
