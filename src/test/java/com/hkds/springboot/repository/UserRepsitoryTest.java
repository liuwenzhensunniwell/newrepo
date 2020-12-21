package com.hkds.springboot.repository;

import com.hkds.springboot.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepsitoryTest {
    @Autowired
    UsersRepository repository;
    @Test
    void  finduser(){
        System.out.println("-------------"+repository.findByUser("123").toString());
    }

}
