package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Transcript;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranscriptRepository extends BaseRepository<Transcript, Integer> {
    //查询指定学生的全部成绩项
    @Query("from Transcript  t where t.graduate.id=:id ")
    Optional<List<Transcript>> findAllTranscriptByGraduadeId(@Param("id") int id);

    //查询指定课程的全部成绩项
    @Query("from Transcript  t where t.course.id=:id")
    Optional<List<Transcript>> findAllTranscriptByCourseId(@Param("id") int id);

    //依据课程id和学生id查找指定成绩项
    @Query("from Transcript t where t.course.id=:cid and t.graduate.id=:gid")
    Optional<Transcript> findByCourseIdAndGraduateId(@Param("cid") int courseId, @Param("gid") int graduateId);

    //依据课程id和学生id删除指定成绩项
    @Modifying
    @Query("delete from Transcript t where t.course.id=:cid and t.graduate.id=:gid")
    void deleteByCourseIdAndGraduateId(@Param("cid") int courseId, @Param("gid") int graduateId);

    //删除指定学生id的成绩项
    @Modifying
    @Query("delete from Transcript t where t.graduate.id=:id")
    void deleteAllByGraduateId(@Param("id")int id);

    //删除指定课程id的成绩项
    @Modifying
    @Query("delete from Transcript t where t.course.id=:id")
    void deleteAllByCourseId(@Param("id") int id);

}
