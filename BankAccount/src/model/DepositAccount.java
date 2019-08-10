package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends Account {

    private Calendar dateWithdraw;

    public DepositAccount(double balance) {
        super(balance);
        dateWithdraw  = Calendar.getInstance();;
        //dateWithdraw.set(2019, Calendar.JULY, 1);
        dateWithdraw.add(Calendar.MONTH, 1);
    }

    @Override
    public void deposit(double summa) {
        super.deposit(summa);
        dateWithdraw.add(Calendar.MONTH, 1);
    }

    @Override
    public void withdraw(double summa) {
        Calendar today = Calendar.getInstance();
        if (today.after(dateWithdraw)) {
            super.withdraw(summa);
        }
        else {
            System.out.println("С момента последнего внесения средств прошло менее месяца! ");
            System.out.println("Ближайшая возможная дата снятия средтв - " +
                    new SimpleDateFormat("dd.MM.yyyy г.").format(dateWithdraw.getTime()));
        }
    }
}
