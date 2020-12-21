package com.hkds.springboot.repository;

import com.hkds.springboot.entity.Club;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClubRepositoryTest {
    @Autowired
    private ClubRepository clubrepository;
    @Test
    void findAll(){
        System.out.println(clubrepository.findAll());
    };
    @Test
    void update(){
        Club club=new Club();
        club.setId(1);
        club.setName("湖南米线2");
        clubrepository.save(club);
    }
}