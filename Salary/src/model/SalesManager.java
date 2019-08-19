package model;

public class SalesManager extends Manager implements Emploee {

    private int interestRate = 5;
    private int contribution = 0;      //вклад каждого в доход


    public SalesManager(String firstName, String secondName, String middleName, int salary, int contribution) {
        super(firstName, secondName, middleName, salary);
        this.contribution = contribution;
    }

    @Override
    public int getMonthSalary() {
        return getSalary() + (contribution * interestRate) / 100;
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
