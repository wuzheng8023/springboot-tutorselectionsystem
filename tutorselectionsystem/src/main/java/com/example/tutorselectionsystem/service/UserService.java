package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.repository.GraduateRepository;
import com.example.tutorselectionsystem.repository.TutorRepository;
import com.example.tutorselectionsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private GraduateRepository graduateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 查找指定number的用户（即根据账号查用户）
     *
     * @param num
     * @return
     */
    public User getUser(int num) {
        return userRepository.findById(num).orElse(null);
    }


    /**
     * 对user声明了persist/remove操作,有了级联，所以可以直接添加老师什么的，持久化老师的时候
     * 会先持久化老师对应的user
     *
     * @param tutor
     * @return
     */
    public Tutor addTutor(Tutor tutor) {
        return tutorRepository.refresh(tutorRepository.save(tutor));
    }


    public Tutor updateTeacher(int selectRange, int numberOfStudentRequired, int tid) {
        Tutor t = tutorRepository.findById(tid)
                .orElseThrow();
        t.setNumberOfStudentRequired(numberOfStudentRequired);
        t.setSelectRange(selectRange);
        return t;
    }

    //修改账户密码
    public void updatePassWord(String newpwd, int uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (!passwordEncoder.matches(newpwd, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "旧密码错误！");
        } else {
            user.setPassword(newpwd);
        }

    }

}
