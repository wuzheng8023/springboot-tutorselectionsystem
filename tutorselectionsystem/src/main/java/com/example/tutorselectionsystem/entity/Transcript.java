package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transcript {
    //成绩单类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float grade;//分数

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime uodateTime;

    private LocalDateTime updateTime;//数据修改时间
    @ManyToOne
    private Graduate graduate;//学生类主键
    @ManyToOne
    private Courses course;//课程类主键


}

//成绩单类（transcript）
//主键（uuid，UUID),毕业生主键-外键，课程类主键-外键，分数（grade,float）；