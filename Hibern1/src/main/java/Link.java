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

}
