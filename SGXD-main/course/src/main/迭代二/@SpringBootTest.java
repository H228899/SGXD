@SpringBootTest
@AutoConfigureMockMvc
class CourseManagementAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    // 教师创建课程（正常）
    @Test
    @WithMockUser(username = "teacher1", roles = "TEACHER")
    void testTeacherCreateCourse() throws Exception {
        mockMvc.perform(post("/teacher/courses")
                .param("name", "人工智能基础")
                .param("description", "AI入门课程")
                .param("credit", "3")
                .param("capacity", "50"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teacher/courses"));
    }

    // 学生搜索课程（空格关键词）
    @Test
    @WithMockUser(username = "student1", roles = "STUDENT")
    void testStudentSearchWithSpace() throws Exception {
        mockMvc.perform(get("/courses")
                .param("keyword", " 人工 智能 "))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("人工智能基础")));
    }

    // 权限隔离：学生访问教师URL
    @Test
    @WithMockUser(username = "student1", roles = "STUDENT")
    void testStudentAccessTeacherUrl() throws Exception {
        mockMvc.perform(get("/teacher/courses"))
                .andExpect(status().isForbidden());
    }
}