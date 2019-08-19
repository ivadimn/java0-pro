package model;

public class SalesManager extends Manager implements Emploee {

    private int interestRate = 5;

    public SalesManager(String firstName, String secondName, String middleName, int salary) {
        super(firstName, secondName, middleName, salary);
    }

    @Override
    public int getMonthSalary(int income) {
        return getSalary() + (getContribution() * interestRate) / 100;
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
