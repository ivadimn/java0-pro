package model;

public interface Emploee {

    public int getMonthSalary(int income);
    public String getName();
    public int getSalary();
    public int getYearRecruit();
    public default int  getContribution() {
        return 0;
    }
}
