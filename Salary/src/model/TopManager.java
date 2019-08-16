package model;

public class TopManager extends Manager {

    public TopManager(String firstName, String secondName, String middleName, int  salary) {
        super(firstName, secondName, middleName, salary);
    }

    @Override
    public int getMonthSalary() {
        int monthSalary;
        if (getTotalIncome() > 10_000_000) {
            //премию как то посчитаем
            monthSalary = getSalary() + getContribution() / 4;
        }
        else {
            monthSalary = getSalary();
        }
        return  monthSalary;
    }

    //здесь увеличиваем сумму заключённых топ менеджером контрактов
    @Override
    public void setContribution() {
        increaseContribution(200000 + Manager.getCValue(100000));
    }
}
