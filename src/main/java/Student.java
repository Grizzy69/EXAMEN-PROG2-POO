import java.time.Instant;
import java.util.List;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Instant entryDate;
    private List<Group> groups;

    public Student(int id, String firstName, String lastName, Instant entryDate, List<Group> groups) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.entryDate = entryDate;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }
}