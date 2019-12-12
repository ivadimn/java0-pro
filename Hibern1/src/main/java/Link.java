import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Links",  schema = "skillbox")
public class Link {

    @Embeddable
    public static class LinkKey implements Serializable {

        @Column(name = "course_id", columnDefinition = "int unsigned")
        protected Integer courseId;

        @Column(name = "student_id", columnDefinition = "int unsigned")
        protected Integer studentId;

        public LinkKey() {
            //
        }

        public LinkKey(Integer courseId, Integer studentId) {
            this.courseId = courseId;
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinkKey linkKey = (LinkKey) o;
            return courseId == linkKey.courseId &&
                    studentId == linkKey.studentId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseId, studentId);
        }

    }


    @EmbeddedId
    private LinkKey linkKey = new LinkKey();

    public Link() {
        //
    }
    @OneToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    protected Course course;

    @OneToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    protected Student student;

    public Link(Course c, Student s) {
        this.course = c;
        this.student = s;
        this.linkKey.courseId = c.getId();
        this.linkKey.studentId = s.getId();

    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
