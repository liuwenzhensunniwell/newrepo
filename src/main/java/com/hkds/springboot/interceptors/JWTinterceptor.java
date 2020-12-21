package com.hkds.springboot.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkds.springboot.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTinterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        System.out.println("--------------"+token);
        Map<String,Object> map=new HashMap<>();
        try {
            JWTUtil.verify(token);//验证令牌
            return true;
        }catch (SignatureVerificationException e){
            map.put("mgs","无效签名");
        }catch (TokenExpiredException e){
            map.put("mgs","token过期");
        }catch (AlgorithmMismatchException e){
            map.put("mgs","token算法不一致");
        }catch (Exception e){
            map.put("mgs","token无效");
        }
        map.put("state",false);
        //将map 转换为json jackson
        String json=new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(map);
        return false;
    }
}
