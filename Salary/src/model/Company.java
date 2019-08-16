package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company  {

    private String name;
    private int totalIncome;
    private List<Manager> personal = new ArrayList<>();

    public Income income = new Income() {
        @Override
        public int getIncome() {
            return totalIncome;
        }
    };

    public Company(String name) {
        this.name = name;
    }

    public void recruit(Manager manager) {
        manager.setIncomeInterface(income);
        personal.add(manager);
    }

    public int calculateIncome() {
        totalIncome = 0;
        for (Manager manager : personal) {
            totalIncome += manager.getContribution();
        }
        return totalIncome;
    }

    public static interface Income {
        public int getIncome();
    }

    //имитация работы компании
    public void work() {
        for (Manager manager : personal) {
            manager.setContribution();
        }
    }

    public List<Manager> getTopSalaryStaff(int count) {
        List<Manager> top = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
            if (m1.getMonthSalary() > m2.getMonthSalary()) {
                return -1;
            }
            else  if (m1.getMonthSalary() < m2.getMonthSalary()){
                return 1;
            }
            else {
                return 0;
            }
        });
        for (int i = 0; i < count; i++) {
            top.add(personal.get(i));
        }
        return top;
    }

    public List<Manager> getLowestSalaryStaff(int count) {
        List<Manager> low = new ArrayList<>();
        Collections.sort(personal, (m1, m2) -> {
            if (m1.getMonthSalary() > m2.getMonthSalary()) {
                return 1;
            }
            else  if (m1.getMonthSalary() < m2.getMonthSalary()){
                return -1;
            }
            else {
                return 0;
            }
        });
        for (int i = 0; i < count; i++) {
            low.add(personal.get(i));
        }
        return low;
    }
}
