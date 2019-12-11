import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class LinkKey implements Serializable {

    @OneToOne
    @JoinColumn(name = "course_id", columnDefinition = "int unsigned")
    private Course course;

    @OneToOne
    @JoinColumn(name = "student_id", columnDefinition = "int unsigned")
    private Student student;

    public LinkKey() {
        //
    }

    public LinkKey(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
}
