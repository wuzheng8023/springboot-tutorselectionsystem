package com.example.tutorselectionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"transcripts"})
public class Courses {  //课程类  strategy = GenerationType.IDENTITY
    //课程类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseName;//课程名称
    private Float weight;//权重
    private Float floorGroad;//可以本课成绩最低分，由教师设定
    private Integer type;//课程类型，1是普通课程，0方向；
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)////2020.9.4新增加的fetch = FetchType.LAZY
    private List<Transcript> transcripts;

    @ManyToOne
    private Tutor tutor;//教师类主键
    /**
     * 自己专门放id的构造函数
     * * @param tid
     */
    public Courses(int tid) {
        this.id = tid;
    }

}

//课程类(courses)（可能手动输入以及录制相关内容）
//主键（uuid，UUID),课程名称（nameOfCourse,String）,
// 权重（weight，float），类型-1为普通课程-2为方向（type,int），最低分数线（floorGroad,float）;