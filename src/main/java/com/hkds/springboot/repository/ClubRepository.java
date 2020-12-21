package com.hkds.springboot.repository;

import com.hkds.springboot.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club,Integer> {

}
