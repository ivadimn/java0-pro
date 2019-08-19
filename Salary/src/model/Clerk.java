package model;

public class Clerk extends Manager implements Emploee {

    public Clerk(String firstName, String secondName, String middleName, int salary) {
        super(firstName, secondName, middleName, salary);
    }
    @Override
    public int getMonthSalary() {
        return getSalary();
    }

    @Override
    public String getName() {
        return super.toString();
    }

}
