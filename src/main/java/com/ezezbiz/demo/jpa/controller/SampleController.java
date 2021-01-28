package com.ezezbiz.demo.jpa.controller;


import com.ezezbiz.demo.jpa.daos.UserRepository;
import com.ezezbiz.demo.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    List<User> users() {
        return (List<User>) userRepository.findAll();
    };
}
