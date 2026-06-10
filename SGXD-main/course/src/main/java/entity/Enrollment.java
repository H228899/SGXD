package com.example.course.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
@Table(name="enrollment")
public class Enrollment {
    // 无参构造，Hibernate反射需要
    public Enrollment() {}
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User student;
    @ManyToOne
    private Course course;
    private Date enrollTime;
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getEnrollTime() { return enrollTime; }
    public void setEnrollTime(Date enrollTime) { this.enrollTime = enrollTime; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}