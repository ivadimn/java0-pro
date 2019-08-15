package model;

import java.util.ArrayList;
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

    public void calculateIncome() {
        totalIncome = 0;
        for (Manager manager : personal) {
            totalIncome += manager.getContribution();
        }
    }

    public static interface Income {
        public int getIncome();
    }
}
