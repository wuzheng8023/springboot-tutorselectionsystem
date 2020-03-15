package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transcript {
    @Id
    @GeneratedValue
    private UUID uuid;
    private float grade;//分数

    @ManyToOne
    private Graduate graduate;//学生类主键
    @ManyToOne
    private Courses course;//课程类主键


}

//成绩单类（transcript）
//主键（uuid，UUID),毕业生主键-外键，课程类主键-外键，分数（grade,float）；