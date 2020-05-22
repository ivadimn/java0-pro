
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList  {

    @EmbeddedId
    private PurchaseKey purchaseKey;

    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseList() {

    }

    public PurchaseKey getPurchaseKey() {
        return purchaseKey;
    }

    public void setPurchaseKey(PurchaseKey purchaseKey) {
        this.purchaseKey = purchaseKey;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, стоимость: %d, дата регистрации: ",
                purchaseKey.getStudentName(), purchaseKey.getCourseName(),
                price, new SimpleDateFormat("dd.MM.yyyy").format(subscriptionDate));
    }

}
