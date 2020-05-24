package com.example.tutorselectionsystem.component;


import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.service.TutorService;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @Override
    public void afterPropertiesSet() throws Exception {
        int num = MyToken.OwnID;
        User user = userService.getUser(num);
        if (user == null) {
            User user1 = new User();
            user1.setRole(User.Role.TEACHER);
            user1.setNumber(num);
            user1.setPassword(passwordEncoder.encode(String.valueOf(num)));
            Tutor tutor = new Tutor();
            tutor.setUser(user1);
            tutor.setName("郝伟");
            userService.addTutor(tutor);//级联操作，在持久化tutor之前，会自动持久化user,
        }

    }
}
