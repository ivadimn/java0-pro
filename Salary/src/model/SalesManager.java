package model;

import java.util.Random;

public class SalesManager extends Manager implements Emploee {

    private int interestRate = 5;
    private int contribution = 0;      //вклад каждого в доход
    private final Random random = new Random();

    public SalesManager(String firstName, String secondName, String middleName) {
        super(firstName, secondName, middleName);
        setSalary(120000 + random.nextInt(11) * 5000);
        //вклад менеджера по продажми
        this.contribution = 200000 + random.nextInt(11) * 5000;
    }

    @Override
    public int getContribution() {
        return contribution;
    }

    @Override
    public int getMonthSalary(int income) {
        return getSalary() + (contribution * interestRate) / 100;
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
