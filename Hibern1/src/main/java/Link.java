import javax.persistence.*;

@Entity
@Table(name = "Links",  schema = "skillbox")
public class Link {
    @EmbeddedId
    private LinkKey linkKey;

    public Link() {
        //
    }

    public Link(LinkKey linkKey) {
        this.linkKey = linkKey;
    }
    public Link(Course c, Student s) {
        this.linkKey = new LinkKey(c, s);
    }

    public LinkKey getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(LinkKey linkKey) {
        this.linkKey = linkKey;
    }
}
