package model;

public class TopManager extends Manager  {

    public TopManager(String firstName, String secondName, String middleName, int  salary) {
        super(firstName, secondName, middleName, salary);
    }

    @Override
    public int getMonthSalary(int income) {
        int monthSalary;
        if (income > 10_000_000) {
            //премию как то посчитаем
            monthSalary = getSalary() + getContribution() / 4;
        }
        else {
            monthSalary = getSalary();
        }
        return  monthSalary;
    }

}
