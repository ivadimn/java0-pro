package model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public abstract class Manager {
    private String firstName;
    private String secondName;
    private String middleName;
    private Date dateRecruit;
    private final Random random = new Random();

    //чистый оклад
    private int salary;

    public Manager(String firstName, String secondName, String middleName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000 + random.nextInt(20), 1, 1);
        dateRecruit = calendar.getTime();
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

    public Date getDateRecruit() {
        return dateRecruit;
    }
    public int getYearRecruit() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateRecruit);
        return calendar.get(Calendar.YEAR);
    }

}
