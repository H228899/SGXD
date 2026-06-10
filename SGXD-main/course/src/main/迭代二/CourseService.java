@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // 仅查询当前教师的课程
    public Optional<Course> findByIdAndTeacher(Long id, User teacher) {
        return courseRepository.findByIdAndTeacher(id, teacher);
    }

    // 创建课程
    public Course create(Course course, User teacher) {
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }
}