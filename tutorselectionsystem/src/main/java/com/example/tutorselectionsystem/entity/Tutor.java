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
public class Tutor {
    @Id
    @GeneratedValue
    private UUID uuid;

    private String name;
    private int selectRange;//选择学生范围，例如前几名
    private int numberOfStudentRequired;//需要多少学生
    private String pwd;//登陆密码
    @OneToMany(mappedBy = "tutorname")
    private List<Graduate> graduates;//已经拥有的学生




}
//导师类（tutor）：
//主键（uuid，UUID），姓名（name,String），已选学生列表(students，List<Student>)，
// 选择学生范围-前几名（selectRange,int）,
// 需要总的学生数（numberOfStudentRequired，int），密码（pwd,String）;