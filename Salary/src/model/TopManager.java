package model;

public class TopManager extends Manager implements Emploee {

    private int contribution = 0;      //вклад каждого в доход

    public TopManager(String firstName, String secondName, String middleName, int  salary, int contribution) {
        super(firstName, secondName, middleName, salary);
        this.contribution = contribution;
    }

    @Override
    public int getMonthSalary() {
        int monthSalary;
        if (getIncome() > 10_000_000) {
            monthSalary = getSalary() + contribution / 4;
        }
        else {
            monthSalary = getSalary();
        }
        return  monthSalary;
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
