package model;

public class EntityClient extends Client {

    public EntityClient(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double summa) {
        super.withdraw(summa + (summa / 100.0));
    }
}
