package com.hkds.springboot.controller;

import com.hkds.springboot.entity.Club;
import com.hkds.springboot.entity.Users;
import com.hkds.springboot.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubHandler {
    @Autowired
    private ClubRepository clubRepository;
    @GetMapping("/findAll/{page}/{size}")
    public Page<Club> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size, HttpSession session){
        Users user=(Users) session.getAttribute("user");
        Pageable pageable= PageRequest.of(page-1,size);
        return clubRepository.findAll(pageable);
    }
    @PostMapping("/save")
    public String save(@RequestBody Club club){
        Club result=  clubRepository.save(club);
        if (result!=null){
            return "succes";
        }else {
            return "error";
        }
    }
    @GetMapping("/findById/{id}")
    public Club findById(@PathVariable("id") Integer id){
        return clubRepository.findById(id).get();
    }
    @PutMapping("/update")
    public String update(@RequestBody Club club){
        Club result=  clubRepository.save(club);
        if (result!=null){
            return "succes";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/del/{id}")
    public void del(@PathVariable("id") Integer id){
        clubRepository.deleteById(id);
    }
}
