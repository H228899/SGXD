package com.example.course.service;
import com.example.course.entity.Course;
import com.example.course.entity.Enrollment;
import com.example.course.entity.User;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class EnrollmentService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    public String enroll (Long courseld, Long studentld) throws Exception{
        Course course = courseRepository.findByld (courseld).orElseThrow (() -> new RuntimeException ("课程不存在"));
        if (course.getRemainingCapacity () <= 0){
            throw new RuntimeException ("课程名额已售罄");
        }
        course.setRemainingCapacity (course.getRemainingCapacity () - 1);
        com.example.repository.CourseRepository.saveAndFlush (course);
        Enrollment enrollment = new Enrollment ();
        enrollment.setCourse(course);
        enrollment.setStudent(new User(studentld));
        enrollment.setEnrollTime(new Date());
        com.example.repository.EnrollmentRepository.save(enrollment);
        return "选课成功，该课程剩余名额：" + course.getRemainingCapacity ();
    }
}