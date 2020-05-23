package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Graduate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GraduateRepository extends BaseRepository<Graduate, Integer> {

    //查询全体学生
    @Query("from Graduate")
    Optional<List<Graduate>> graduateList();

    //依据学号查询指定学生
    @Query("from Graduate g where g.id=:id ")
    Optional<Graduate> findGraduateById(@Param("id") int id);

    //依据姓名查找学生
    @Query("from Graduate g where g.name=:name")
    Optional<List<Graduate>> findGraduateByName(@Param("name") String name);


    //依据姓名删除学生
    @Modifying
    @Query("delete from Graduate g where g.name=:gname")
    void deleteByName(@Param("gname") String gname);


    //依据id删除学生
    @Modifying
    @Query("delete from Graduate g where  g.id=:id")
    void deleteById(@Param("id")Integer id);



}
