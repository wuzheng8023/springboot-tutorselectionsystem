package com.example.tutorselectionsystem.controller;

import com.example.tutorselectionsystem.component.EncryptorComponent;
import com.example.tutorselectionsystem.component.RequestComponent;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PatchMapping("password")
    public void patchPassWord(@RequestBody User user){
        userService.updatePassWord(passwordEncoder.encode(user.getPassword()),requestComponent.getUid());
    }

}
