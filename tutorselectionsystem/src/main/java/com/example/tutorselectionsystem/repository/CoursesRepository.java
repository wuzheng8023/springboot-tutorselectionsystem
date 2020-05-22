package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Courses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends BaseRepository<Courses,Integer> {
    //查询出全部的课程
    @Query("from Courses ")
    Optional<List<Courses>> courseList();

    //依据指定id查询课程
    @Query("from Courses c where c.id=:id")
    Optional<Courses> findCoursesById(@Param("id") Integer id);

    //依据课程名称查询课程
    @Query("from Courses c where c.courseName=:cname")
    Optional<List<Courses>> findByCourseName(@Param("cname")String courseName);

    //基于名字删除指定课程
    @Modifying
    @Query("delete from Courses c where c.courseName=:cname")
    void deleteCoursesByCourseName(@Param("cname")String cname);

    //基于id删除指定课程
    @Modifying
    @Query("delete from Courses c where c.id=:cid")
    void deleteCoursesById(@Param("cid") Integer id);







}
