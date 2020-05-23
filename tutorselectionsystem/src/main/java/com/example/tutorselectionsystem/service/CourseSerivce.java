package com.example.tutorselectionsystem.service;


import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseSerivce {

    @Autowired
    private CoursesRepository coursesRepository;

    /**
     * 依据课程名称查询课程
     *
     * @param courseName
     * @return
     */
    public List<Courses> getCoursesByName(String courseName) {
        return coursesRepository.findByCourseName(courseName).orElse(List.of());
    }

    /**
     * 依据课程号查询课程
     *
     * @param id
     * @return
     */
    public Courses getCoursesById(Integer id) {
        return coursesRepository.findCoursesById(id).orElse(null);
    }

    /**
     * 查找全部课程
     *
     * @return
     */
    public List<Courses> getAllCourses() {
        return coursesRepository.courseList().orElse(List.of());
    }

    /**
     * 修改课程
     *
     * @param courses
     * @return
     */
    public Courses updateCourses(Courses courses) {
        if (getCoursesById(courses.getId()) != null) {

            return coursesRepository.save(courses);
        }
        return null;
    }

    /**
     * 添加课程
     *
     * @param courses
     * @return
     */
    public Courses addCourses(Courses courses) {
        if (getCoursesById(courses.getId()) != null) {
            return coursesRepository.save(courses);
        }
        return null;
    }

    /**
     * 依据名字删除课程
     *
     * @param cname
     */
    public void deleteCoursesByName(String cname) {
        coursesRepository.deleteCoursesByCourseName(cname);
    }

    /**
     * 依据id删除课程
     *
     * @param id
     */
    public void deleteCourseById(Integer id) {
        coursesRepository.deleteCoursesById(id);
    }
}
