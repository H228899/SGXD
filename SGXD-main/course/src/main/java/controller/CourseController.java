package com.example.course.controller;

import com.example.course.entity.Course;
import com.example.course.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;

@Controller
public class CourseController {
    @Resource
    private CourseRepository courseRepository;

    // 访问/course时返回课程列表页
    @GetMapping("/course")
    public String showCourseList(Model model) {
        // 也可以把数据库里的课程传到页面动态渲染，现在先写死模拟
        return "course";
    }

    // 处理选课请求，跳转到结果页
    @PostMapping("/enroll")
    public String doEnroll(@RequestParam Long courseId, Model model) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            model.addAttribute("msg", "恭喜，成功选修《" + course.getName() + "》");
        } else {
            model.addAttribute("msg", "选课失败，课程不存在");
        }
        return "enroll";
    }
}