package model;

public class IndividualClient extends Client  {
    public IndividualClient(double balance) {
        super(balance);
    }

    @Override
    public void deposit(double summa) {
        double comission = (summa < 1000) ? summa / 100 : summa / 200;
        super.deposit(summa - comission);
    }
}
