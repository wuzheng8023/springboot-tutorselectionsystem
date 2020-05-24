package com.example.tutorselectionsystem.Interceptor;

import com.example.tutorselectionsystem.component.EncryptorComponent;
import com.example.tutorselectionsystem.component.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Optional.ofNullable(request.getHeader(MyToken.AUTHORIZATION))
                .map(auth -> encryptorComponent.decryptToken(auth))//
                .ifPresentOrElse(token -> {  //两个方法，对象存在执行第一个，不存在执行第二个
                    request.setAttribute(MyToken.UID, token.getUid());
                    request.setAttribute(MyToken.ROLE, token.getRole());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录");
                });

        return true; //为true才能执行到下一步，否则直接就拦截结束了
    }
}
