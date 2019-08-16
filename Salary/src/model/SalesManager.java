package model;

public class SalesManager extends Manager {

    private int interestRate = 5;

    public SalesManager(String firstName, String secondName, String middleName, int salary) {
        super(firstName, secondName, middleName, salary);
    }

    //здесь увеличение суммы проданного конкретным менежером
    @Override
    public void setContribution() {
        increaseContribution(15000 + Manager.getCValue(5000));
    }

    @Override
    public int getMonthSalary() {
        return getSalary() + (getContribution() * interestRate) / 100;
    }

}
