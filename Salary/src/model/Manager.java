package model;

import java.util.Random;

public abstract class Manager {
    private String firstName;
    private String secondName;
    private String middleName;

    //чистый оклад
    private int salary;

    public Manager(String firstName, String secondName, String middleName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }


    @Override
    public String toString() {
        return String.format("%s %s %s ", secondName, firstName, middleName);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

}
