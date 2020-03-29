# 毕业设计导师学生双选系统
#### creation time 2020-03-10T15:35

### Directory
- [Project introduction](Project-introduction)
- [Development Environments](Development-Environments)
- [Development record](Development-record)

### Project introduction
**实现毕业设计双向选择:**
 具体功能说明：     
 + 教师：   
         1. 登录，导入多门课程excel表格     
         3. 设置实际指导学生数    
         4. 设置加权后，有资格学生范围    
         5. 查看当前已接收学生    
         6. 各参数可修改   
         7. 设置毕设方向，设置毕设方向权值
 + 学生：   
         1.输入学号显示曾经选修课程  
         2.提交，返回身份确认    
         3.匹配，查看学生是否选择导师成功；成功，更新数据提示成功，不成功，返回友好提示；
         4.达到最大数关闭提交功能  
         5.后台并发判断

### Development Environments
开发环境/框架及版本：
 - Intellij IDEA 2019.3.3
 - OpenJDK 11.0.2
 - Springboot 2.2.5
 - Git 2.25.1
 - MySQL 8.0.18
 - VS Code 1.42
 - Node.js 12.16
 - Vue 2.2.11
 - VueX 3.1.2
 - Axios 0.19.2
---
### Development record

#### Date and time:2020-03-29T22:14 feature/develop

 - 完成JPA接口的继承以及测试使用；
 - 主键进行修改：由原本的UUID改为int自增长,为方便后续测试使用；
 - 完善了实体类的属性，添加了数据的插入时间属性以及修改时间属性，采用由数据库生成的策略；

#### Date and time:2020-03-16T17:20 bugfix

**Bug : java.lang.IllegalStateException: Failed to load ApplicationContext**

**问题分析：**
“加载程序内容出现错误”，是Spring在运行时缺失配置文件;可能是配置路径有问题，Spring启动时找不到相关配置文件（查询资料是发现这是常见的错误）或者是可能根本没有导入配置（本人自己所犯错误）；

**解决方案：**
在实际浏览项目中发现，在项目工程配置[application.properties](https://github.com/wuzheng8023/springboot-tutorselectionsystem/blob/master/tutorselectionsystem/src/main/resources/application.properties)文件中`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`显色红色报错；这是缺失mysql的驱动，所以在配置文件[pom.xml](https://github.com/wuzheng8023/springboot-tutorselectionsystem/blob/master/tutorselectionsystem/pom.xml)添加以下代码即修复了这个BUG；
   ```java
    <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <scope>runtime</scope>
           </dependency>
   ```
**补充说明：**
Spring加载配置文件路径修改：可以通过@ContextConfiguration修改实现,这是[相关文档](https://www.cnblogs.com/kinome/p/9628830.html) 。