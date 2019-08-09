package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends Account {

    private Calendar lastDeposit = Calendar.getInstance();

    public DepositAccount(double balance) {
        super(balance);
        lastDeposit.setTime(new Date());
        //lastDeposit.set(2019, Calendar.JULY, 1);
    }

    @Override
    public void deposit(double summa) {
        super.deposit(summa);
        lastDeposit.setTime(new Date());
    }

    @Override
    public void withdraw(double summa) {
        Calendar dateWithdraw = Calendar.getInstance();
        int dayOfMonth = 31;
        dateWithdraw.add(Calendar.DAY_OF_YEAR, -dayOfMonth);
        if (dateWithdraw.after(lastDeposit)) {
            super.withdraw(summa);
        }
        else {
            System.out.println("С момента последнего внесения средств прошло менее месяца! ");
            dateWithdraw.setTime(lastDeposit.getTime());
            dateWithdraw.add(Calendar.DAY_OF_YEAR, dayOfMonth);
            System.out.println("Ближайшая возможная дата снятия средтв - " +
                    new SimpleDateFormat("dd.MM.yyyy г.").format(dateWithdraw.getTime()));
        }
    }
}
