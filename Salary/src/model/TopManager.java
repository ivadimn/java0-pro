package model;

import java.util.Calendar;
import java.util.Random;

public class TopManager extends Manager implements Emploee {

    private int contribution = 0;      //вклад каждого в доход

    private final Random random = new Random();

    public TopManager(String firstName, String secondName, String middleName) {
        super(firstName, secondName, middleName);
        setSalary(150000 + random.nextInt(11) * 5000);
        //вкалад топ менеджера
        this.contribution = 200000 + random.nextInt(11) * 5000;
    }

    @Override
    public int getMonthSalary(int income) {
        int monthSalary;
        if (income > 10_000_000) {
            monthSalary = getSalary() + contribution / 4;
        }
        else {
            monthSalary = getSalary();
        }
        return  monthSalary;
    }

    @Override
    public int getContribution() {
        return contribution;
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
