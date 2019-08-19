package model;

public interface Emploee {

    public int getMonthSalary();
    public String getName();
    public int getSalary();
    public default int  getIncome() {
        return 11000000;
    }
}
