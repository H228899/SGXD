@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private int credit;

    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // 剩余名额
    public int getRemainingCapacity() {
        return capacity; // 迭代2暂未选课，剩余=容量
    }

    // 是否已满
    public boolean isFull() {
        return getRemainingCapacity() <= 0;
    }

    // getter/setter
}