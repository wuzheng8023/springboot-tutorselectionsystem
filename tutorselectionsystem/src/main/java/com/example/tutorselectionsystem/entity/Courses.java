package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String courseName;//课程名称
    private float weight;//权重
    private float floorGroad;//可以本课成绩最低分，由教师设定
    private int type;//课程类型，1是普通课程，0方向；

    @OneToMany(mappedBy = "course")
    private List<Transcript> transcripts;

}

//课程类(courses)（可能手动输入以及录制相关内容）
//主键（uuid，UUID),课程名称（nameOfCourse,String）,
// 权重（weight，float），类型-1为普通课程-2为方向（type,int），最低分数线（floorGroad,float）;