package com.example.tutorselectionsystem.repository;


import com.example.tutorselectionsystem.entity.Courses;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class CoursesRepositoryTest {
    @Autowired
    private CoursesRepository coursesRepository;

    @Test
    public void test_addCouses(){
        Courses courses = new Courses();
        courses.setCourseName("JAVA开发");
        courses.setType(2);
        coursesRepository.save(courses);
//        coursesRepository.refresh(courses);
    }

    @Test
    public void test_addCouses2(){
        Courses courses = new Courses();
        courses.setCourseName("离散数学");
        courses.setType(2);
        coursesRepository.save(courses);
//        coursesRepository.refresh(courses);
        log.debug("{}", courses.getCourseName());
        log.debug("{}", courses.getId());
        log.debug("{}", courses.getInsertTime());
    }



}
