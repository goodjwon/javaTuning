package com.ezezbiz.demo.jpa;

import com.ezezbiz.demo.jpa.daos.TeamRepository;
import com.ezezbiz.demo.jpa.daos.UserRepository;
import com.ezezbiz.demo.jpa.entity.Team;
import com.ezezbiz.demo.jpa.entity.User;
import com.ezezbiz.demo.jpa.entity.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 외래키가 있는 쪽을 주인으로 정해라
 * 주인이 아닌 보조에 mapped by를 사용한다.
 * 가급적 단방향 연관관계로 작업 하고 처리가 되지 않을 경우 양방향 관계를 사용한다.
 * 단방양으로 작업 후 양방향으로 해도 DB에는 영향을 주지 않는다.
 * 주인이 아닌쪽은 읽기만 가능하다.
 */
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
    @Rollback(false)
    @Transactional
    public void givenUserProfile_whenAddUser_thenCreateNewUser(){
        try {
        Team team = new Team("teamA");
        User user = new User("John", "Doe","Programmer", UserType.Admin, team);
        teamRepository.save(team);
        userRepository.save(user);

        Optional<User> findUser = userRepository.findById(user.getId());

        List<User> users = new ArrayList<>();

        findUser.ifPresent(User -> {
            System.out.println("ifPresent: "+User);
            User.getTeam().getUsers().forEach(user1 -> {
                users.add(user1);
            });
        });

        System.out.println(users);

        System.out.println("* findUser: ");
        assertFalse(findUser.isEmpty());
        System.out.println(findUser);
        System.out.println("============");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void givenUsersProfile(){
        List<User> users  = (List<User>) userRepository.findAll();
        assertTrue(users.isEmpty());
    }


    @Test
    @Transactional
    public void updateUserProfile(){
        Optional<User> user = userRepository.findById(5);
        user.ifPresent(User -> {
            System.out.println("here??");
            User.setLastName("Jwon");
            userRepository.save(User);
            System.out.println("here??");
        });
    }
}
