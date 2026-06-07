package com.example.course.repository;
import com.example.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
// 继承JpaRepository就自带增删改查、分页方法，不用额外写代码
public interface CourseRepository extends JpaRepository<Course, Long> {
    // 可以在这里加自定义查询方法，比如按课程名查找
    // List<Course> findByNameContaining(String name);
}