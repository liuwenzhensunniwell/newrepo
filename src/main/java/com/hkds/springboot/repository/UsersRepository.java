package com.hkds.springboot.repository;

import com.hkds.springboot.entity.Users;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query("select s from Users s where s.user=?1")
    public Users findByUser(String user);
}
