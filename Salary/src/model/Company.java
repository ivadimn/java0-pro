package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company  {

    private String name;
    private List<Emploee> personal = new ArrayList<>();


    public Company(String name) {
        this.name = name;
    }

    public void recruit(Emploee emploee) {
        personal.add(emploee);
    }
    public void dismiss(Emploee emploee) {
        personal.remove(emploee);
    }

    public List<Emploee> getTopSalaryStaff(int count) {
        List<Emploee> top = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
           return  m2.getMonthSalary() - m1.getMonthSalary();
        });
        for (int i = 0; i < count; i++) {
            top.add(personal.get(i));
        }
        return top;
    }

    public List<Emploee> getLowestSalaryStaff(int count) {
        List<Emploee> low = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
                return m1.getMonthSalary() - m2.getMonthSalary();
        });
        for (int i = 0; i < count; i++) {
            low.add(personal.get(i));
        }
        return low;
    }

}
