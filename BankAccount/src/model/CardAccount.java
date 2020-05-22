package model;

public class CardAccount extends Account{

    public CardAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double summa) {
        double comission = summa / 100;
        System.out.printf("%s - %.2f\n", "Комиссия при снятии суммы 1% и составит", comission);
        super.withdraw(summa + comission);
    }
}
