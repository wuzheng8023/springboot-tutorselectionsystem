package com.example.tutorselectionsystem.controller;

import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.component.RequestComponent;
import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.entity.Transcript;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.service.CourseSerivce;
import com.example.tutorselectionsystem.service.GraduateService;
import com.example.tutorselectionsystem.service.TutorService;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/graduate/")
public class GraduateController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseSerivce courseSerivce;
    @Autowired
    private UserService userService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private GraduateService graduateService;

    //登陆页面
    @GetMapping("index")
    public Map getIndex() {

        Graduate graduate = graduateService.getById(requestComponent.getUid());
        List<Graduate> graduateList = graduateService.newRange();
        List<Transcript> transcriptList = graduate.getTranscripts();
        Tutor tutor = tutorService.getTutorByNum(MyToken.OwnID);
        return Map.of(
                "graduate", graduate,
                "graduateList", graduateList,
                "transcriptList", transcriptList,
                "tutor", tutor);
    }
//选择老师
    @PostMapping("select")
    public Map postSelect(@RequestBody Graduate graduate) {
        Graduate g = graduateService.getById(requestComponent.getUid());
        List<Transcript> transcriptList = graduate.getTranscripts();//放置方向
        if (graduateService.checkQualification(requestComponent.getUid(),MyToken.OwnID)){
           graduateService.joinTeam(graduate);
        }
            return Map.of("graduate", graduate);
    }

}