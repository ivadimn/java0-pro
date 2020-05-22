import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey1 subscriptionKey;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription() {
    }

    public SubscriptionKey1 getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(SubscriptionKey1 subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return String.format("%s курс: %s, дата регистрации - %s",
                subscriptionKey.getStudent().getName(), subscriptionKey.getCourse().getName(),
                new SimpleDateFormat("dd.MM.yyyy").format(subscriptionDate));
    }
}
