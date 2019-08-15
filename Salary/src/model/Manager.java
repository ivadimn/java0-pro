package model;

import java.util.Random;

public abstract class Manager {
    private String firstName;
    private String secondName;
    private String middleName;

    //чистый оклад
    private int salary;
    private int contribution = 0;      //вклад каждого сотрудника в доход
    private Company.Income income;     //для получения суммы дохода для расчёта зарплаты

    private static final Random random = new Random();

    public Manager(String firstName, String secondName, String middleName, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.salary = salary;
    }

    public Company.Income getIncomeInterface() {
        return income;
    }

    public void setIncomeInterface(Company.Income income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s ", secondName, firstName, middleName);
    }

    public int getSalary() {
        return salary;
    }

    //увеличивает вклад конкретного сотрудника
    public void increaseContribution(int contribute) {
        this.contribution += contribute;
    }

    public int getContribution() {
        return contribution;
    }

    //генерирует случайное число для расчётоа вклада и установки оклада
    public static int getCValue(int maxVal) {
        return random.nextInt(maxVal);
    }

    //расчёт вклада каждого сотрудника в результат
    public abstract void setContribution();
}
