package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    //导师类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name;
    private Integer selectRange;//选择学生范围，例如前几名
    private Integer numberOfStudentRequired;//需要多少学生
    private String pwd;//后台权限验证密码

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "tutorname")
    private List<Graduate> graduates;//已经拥有的学生




}
//导师类（tutor）：
//主键（uuid，UUID），姓名（name,String），已选学生列表(students，List<Student>)，
// 选择学生范围-前几名（selectRange,int）,
// 需要总的学生数（numberOfStudentRequired，int），密码（pwd,String）;