package com.example.tutorselectionsystem.Interceptor;


import com.example.tutorselectionsystem.component.EncryptorComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
//    @Autowired
//    private EncryptorComponent encryptorComponent;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String Authorization = request.getHeader("Authorization");
//
//        log.debug("{}", Authorization);
//
//        if (Authorization != null) {
//            log.debug("有token");
//            MyToken myToken = encryptorComponent.decryptToken(Authorization);
//            request.setAttribute("userName", myToken.getUserName());
//            return true;
//        } else {
//            log.debug("没有token");
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登陆");
//
//        }
//   }

}

