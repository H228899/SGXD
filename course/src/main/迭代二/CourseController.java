@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            Model model) {

        Page<Course> courses;
        if (keyword == null || keyword.isBlank()) {
            courses = courseRepository.findAll(PageRequest.of(page, size));
        } else {
            courses = courseRepository.findByNameContaining(keyword, PageRequest.of(page, size));
        }
        model.addAttribute("courses", courses);
        model.addAttribute("keyword", keyword); // 保留搜索词
        return "course-list";
    }
}