package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Graduate {
    @Id
    @GeneratedValue
    private UUID uuid;
    private int studentNumber;//学号
    private String name;//学生姓名

    @ManyToOne
    private Tutor tutorname;//导师姓名

    @OneToMany(mappedBy = "graduate")
    private List<Transcript> transcripts;//成绩表



}
//毕业生（graduate）：(通过选课名单导入)
//主键(uuid,UUID),学号（studentNumber,int），
// 姓名（name,String），已经选课程以及方向的ID号组成的集合-外键(courseList,List<courses>,
// 老师ID-外键（teacherID，UUID）；