package com.example.tutorselectionsystem.controller;


import com.example.tutorselectionsystem.component.RequestComponent;
import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.entity.Transcript;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/tutor/")
public class TutorController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseSerivce courseSerivce;
    @Autowired
    private UserService userService;
    @Autowired
    private GraduateService graduateService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private TranscriptService transcriptService;

    //登陆页面,返回老师教授的全部课程以及当前已经选择他的学生
    @GetMapping("index")
    public Map getTeacher() {
        Tutor t = tutorService.getTutorByID(requestComponent.getUid());
        return Map.of(
                "teacher", t,
                "courses", t.getCourses(),
                "students", t.getGraduates());
    }

    //添加课程实体
    @PostMapping("courses")
    public Map postCourse(@RequestBody Courses course) {
        course.setTutor(new Tutor(requestComponent.getUid()));
        courseSerivce.addCourses(course);
        return Map.of("course", course);
    }

    //删除课程实体
    @DeleteMapping("nocourses")
    public Map deleteCourse(@RequestBody Courses course) {
        Tutor tutor = tutorService.deleteCourse(requestComponent.getUid(), course.getId());
        return Map.of("tutor", tutor);
    }


    //添加学生实体
    @PostMapping("students")
    public Map postStudent(@RequestBody Graduate graduate) {
        Graduate g = graduateService.addGraduate(graduate,requestComponent.getUid());
        return Map.of("student", g);
    }

    //删除学生实体
    @DeleteMapping("nostudents")
    public void DeleteStudent(@RequestBody Graduate graduate) {
        graduateService.deleteById(graduate.getId());
    }

    //添加成绩项
    @PostMapping("transcript")
    public Map postTranscript(@RequestBody Transcript transcript) {
        Transcript t = transcriptService.addTranscript(transcript);
        return Map.of("transcript", t);
    }
    //删除成绩项
    @DeleteMapping("notranscript")
    public void DeleteTranscript(@RequestBody Transcript transcript) {
      transcriptService.deleteTranscript(transcript.getId());
    }

    //添加选择自己中意的学生
    @PatchMapping("select")
    public Map patchSelectGrauate(@RequestBody Graduate graduate) {
        Tutor tutor = tutorService.addTutorStudent(requestComponent.getUid(), graduate.getId());
        return Map.of("tutor", tutor);

    }
    //删除自己不中意的学生
    @DeleteMapping("deleteGraduate")
    public Map deleteSelectedGraduate(@RequestBody Graduate graduate){
        Tutor tutor = tutorService.deleteTutorStudent(requestComponent.getUid(),graduate.getId());
        return Map.of("tutor", tutor);
    }
    //添加自己教授的课程
    @PatchMapping("addCourses")
    public Map patchAddCourse(@RequestBody Graduate graduate){
        Tutor tutor = tutorService.addCourse(requestComponent.getUid(),graduate.getId());
        return Map.of("tutor", tutor);
    }
    //删除自己教授的课程
    @DeleteMapping("deleteCourse")
    public Map deleteDeleteGraduate(@RequestBody Graduate graduate){
        Tutor tutor = tutorService.deleteCourse(requestComponent.getUid(),graduate.getId());
        return Map.of("tutor", tutor);
    }


    //查询指定课程
    @GetMapping("courses/{cid}")
    public Map getCourse(@PathVariable int cid) {
        return Map.of("course", courseSerivce.getCoursesById(cid));

    }

    //修改老师一些参数
    @PatchMapping("settings")
    public Map patchSettings(@RequestBody Tutor t) {
        Tutor tutor = userService.updateTeacher(t.getSelectRange(), t.getNumberOfStudentRequired(), requestComponent.getUid());
        return Map.of("tutor", tutor);
    }
    //修改课程参数
    @PatchMapping("coursesettting")
    public Map patchcourse(@RequestBody Courses courses){
        Courses c = courseSerivce.updateCourese(courses.getId(),courses.getCourseName(),courses.getWeight(),courses.getFloorGroad(),courses.getType());
        return Map.of("course",c);
    }
    //计算排名
    @GetMapping("calculate")
    public Map getCalculate(){

        List<Graduate> graduateList = graduateService.newRange();
        return Map.of("graduateList",graduateList);
    }

}
