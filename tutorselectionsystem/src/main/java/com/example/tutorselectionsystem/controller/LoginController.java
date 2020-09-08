package com.example.tutorselectionsystem.controller;


import com.example.tutorselectionsystem.component.EncryptorComponent;
import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

import static com.example.tutorselectionsystem.entity.User.*;

@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.teacher}")
    private String roleTeacher;
    @Value("${my.student}")
    private String roleStudent;
    @Value("${my.admin}")
    private String roleAdmin;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptComponent;

    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response) {
        User user = Optional.ofNullable(userService.getUser(login.getNumber()))
                .filter(u -> passwordEncoder.matches(login.getPassword(), u.getPassword()))//正确执行
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名密码错误"));//不正确执行

        MyToken token = new MyToken(user.getId(), user.getRole());
        String auth = encryptComponent.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);
        Role tempRole = user.getRole();
        String roleCode;
        //根据系统需要，这里我将管理员和老师的编码设置成一样的,所以老师和管理员的权限一样
        switch (tempRole) {
            case TEACHER:
                roleCode = roleTeacher;
                break;
            case ADMIN:
                roleCode = roleAdmin;
                break;
            case STUDENT:
                roleCode = roleStudent;
                break;
            default:
                roleCode = roleStudent;
        }


//        String roleCode = user.getRole() == Role.TEACHER ? roleTeacher : roleStudent;
        return Map.of("role", roleCode);
    }
}
