package com.hkds.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hkds.springboot.entity.Users;
import com.hkds.springboot.repository.UsersRepository;
import com.hkds.springboot.utils.JWTUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Users")
public class UsersHandler {
    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/findUser")
    public Map<String,Object> findUser(@RequestBody Users users, HttpServletRequest httpServletRequest){
        Users users1=usersRepository.findByUser(users.getUser());
        
        Map<String,Object> map=new HashMap<>();
        if (users1!=null) {
            if (users1.getPws().equals(users.getPws())){
                map.clear();
                map.put("userid",users1.getId()+"");
                map.put("user",users1.getUser());
                try {
                    String token=JWTUtil.getToken(map);
                    map.put("state",true);
                    map.put("msg","认证成功");
                    map.put("token",token);
                    return map;
                }catch (SignatureVerificationException s){
                    map.put("state",false);
                    map.put("msg","签名不一致");
                }catch (TokenExpiredException t){
                    map.put("state",false);
                    map.put("msg","令牌已过期");
                }catch (AlgorithmMismatchException a){
                    map.put("state",false);
                    map.put("msg","加密算法不一致");
                }catch (InvalidClaimException ic){
                    map.put("state",false);
                    map.put("msg","token已失效");
                }catch (Exception e){
                    map.put("state",false);
                    map.put("msg","认证失败");
                }
            }else {
                map.put("state",false);
                map.put("msg","密码错误");
            }
        }else {
            map.put("state",false);
            map.put("msg","账号不存在");
        }
        return map;
    }


}
