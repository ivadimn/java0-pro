package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company  {

    private String name;
    private List<Emploee> personal = new ArrayList<>();
    private int income = 0;


    public Company(String name) {
        this.name = name;
    }

    public void recruit(Emploee emploee) {
        personal.add(emploee);
        income += emploee.getContribution();
     }
    public void dismiss(Emploee emploee) {
        personal.remove(emploee);
        income -= emploee.getContribution();
    }

    public void dismiss(int index) {
        income -= personal.get(index).getContribution();
        personal.remove(index);
    }

    public List<Emploee> getTopSalaryStaff(int count) {
        List<Emploee> top = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
           return  m2.getMonthSalary(income) - m1.getMonthSalary(income);
        });
        for (int i = 0; i < count; i++) {
            top.add(personal.get(i));
        }
        return top;
    }

    public List<Emploee> getLowestSalaryStaff(int count) {
        List<Emploee> low = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
                return m1.getMonthSalary(income) - m2.getMonthSalary(income);
        });
        for (int i = 0; i < count; i++) {
            low.add(personal.get(i));
        }
        return low;
    }

    public int getIncome() {
        return income;
    }
}
