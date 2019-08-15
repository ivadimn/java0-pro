package model;

public class SalesManager extends Manager implements Emploee {

    private int interestRate = 5;

    public SalesManager(String firstName, String secondName, String middleName, int salary) {
        super(firstName, secondName, middleName, salary);
    }

    //здесь увеличение суммы проданного конкретным менежером
    @Override
    public void setContribution() {
        increaseContribution(5000 + Manager.getCValue(10000));
    }

    @Override
    public int getMonthSalary() {
        return 0;
    }

}
