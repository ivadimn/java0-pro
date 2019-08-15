package model;

public class TopManager extends Manager implements Emploee {

    public TopManager(String firstName, String secondName, String middleName, int  salary) {
        super(firstName, secondName, middleName, salary);
    }

    @Override
    public int getMonthSalary() {
        return 0;
    }

    //здесь увеличиваем сумму заключённых топ менеджером контрактов
    @Override
    public void setContribution() {
        increaseContribution(50000 + Manager.getCValue(100000));
    }
}
