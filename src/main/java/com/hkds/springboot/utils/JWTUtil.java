package com.hkds.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.hibernate.annotations.Fetch;

import java.util.Calendar;
import java.util.Map;

/*
* JWT帮助类
* */
public class JWTUtil {
    private static String TOKEN="@QQ##EFF~EF`";
    /*
    * 生成token
    * @param map：传入payload
    * @return token
    * */
    public static String getToken(Map<String,Object> map){
        JWTCreator.Builder builder = JWT.create();
        for (String k:map.keySet()) {
            builder.withClaim(k,map.get(k).toString());
        }
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        builder.withExpiresAt(calendar.getTime());
       return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
    }
    /*
    * 验证token
    * @param token
    * @return
    * */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /*
    * 获取token中payload
    * @param token
    * @return
    * */
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
