package model;

import java.util.Random;

public abstract class Manager {
    private String firstName;
    private String secondName;
    private String middleName;

    //чистый оклад
    private int salary;
    private int contribution = 0;      //вклад каждого сотрудника в доход

    public Manager(String firstName, String secondName, String middleName, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return String.format("%s %s %s ", secondName, firstName, middleName);
    }

    public int getSalary() {
        return salary;
    }

    public int getContribution() {
        return contribution;
    }

}
