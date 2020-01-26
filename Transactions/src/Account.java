import java.util.concurrent.atomic.AtomicBoolean;

public class Account
{
    private long money;
    private String accNumber;
    private AtomicBoolean isBlocked = new AtomicBoolean(false);

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    public void toDeposit(long summa) {
        money += summa;
    }

    public long withdraw(long summa) {
        if (money >= summa) {
            money -= summa;
            return summa;
        }
        return 0;
    }

    public void block() {
        isBlocked.set(true);
    }

    public String getAccNumber() {
        return accNumber;
    }

    public long getMoney() {
        return money;
    }

    public boolean getIsBlocked() {
        return isBlocked.get();
    }

}
