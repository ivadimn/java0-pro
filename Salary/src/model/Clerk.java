package model;

import java.util.Random;

public class Clerk extends Manager implements Emploee {

    private final Random random = new Random();

    public Clerk(String firstName, String secondName, String middleName) {
        super(firstName, secondName, middleName);
        setSalary(60000 + random.nextInt(11) * 2000);
    }
    @Override
    public int getMonthSalary(int income) {
        return getSalary();
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
