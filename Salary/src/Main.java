import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static final String[] FNAMES = {"Иван", "Пётр", "Сергей", "Андрей", "Антон", "Виктор", "Михаил", "Александр"};
    public static final String[] SNAMES = {"Иванов", "Петров", "Сергеев", "Андреев", "Антонов", "Викторов",
            "Михайлов", "Александров"};
    public static final String[] MNAMES = {"Иванович", "Петрович", "Сергеевич", "Андреевич", "Антонович", "Викторович",
            "Михайлович", "Александрович"};



    public static void main(String[] args) throws IOException {
        Company company;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.println("Введите наименование компании (или 'exit' для завершения ): ");
            String name = reader.readLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            company = new Company(name);
            System.out.println( name + " принимает сотрудников...");
            generateEmploee(company);
            System.out.println("На работу принято - " + generateEmploee(company) + " сотрудников");
            company.work();
            System.out.println(name + " работает... ");
            System.out.println("Заработали - " + company.calculateIncome());
            System.out.println("Посмотрим кто сколько заработал...");
            show(company);
        }
    }

    public static int generateEmploee(Company company) {
        //топ менеджеры
        for (int i = 0; i < 10; i++) {
            Manager m = new TopManager(FNAMES[Manager.getCValue(8)], SNAMES[Manager.getCValue(8)],
                    MNAMES[Manager.getCValue(8)], 150000 + Manager.getCValue(50000));
            company.recruit(m);
        }
        //продажники
        int numSales = 180 + Manager.getCValue(51);
        for (int i = 0; i < numSales + 1; i++) {
            Manager m = new SalesManager(FNAMES[Manager.getCValue(8)], SNAMES[Manager.getCValue(8)],
                    MNAMES[Manager.getCValue(8)], 100000 + Manager.getCValue(20000));
            company.recruit(m);
        }
        int numClerks = 50 + Manager.getCValue(31);
        for (int i = 0; i < numClerks + 1; i++) {
            Manager m = new Clerk(FNAMES[Manager.getCValue(8)], SNAMES[Manager.getCValue(8)],
                    MNAMES[Manager.getCValue(8)], 60000 + Manager.getCValue(20000));
            company.recruit(m);
        }
        return numClerks + numSales + 10;
    }

    public static void show(Company company) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.println("Введите число для просмотра этого количества самых высокоплачиваемх сострудников ");
            System.out.println("или отрицательное число для просмотра этого количества самых низкооплачиваемых сострудников ");
            System.out.println("или 'exit' для выхода ");
            String num = reader.readLine();
            if (num.equalsIgnoreCase("exit")) {
                break;
            }
            int count = Integer.parseInt(num);
            if (count > 0) {
                printManagers(company.getTopSalaryStaff(count), "Самые высокооплачиваемые : ");
            }
            else if (count < 0) {
                printManagers(company.getLowestSalaryStaff(-count), "Самые низкооплачиваемые : ");
            }
            else {
                continue;
            }
        }
    }

    public static void printManagers(List<Manager> mlist, String info) {
        System.out.println(info);
        for (Manager m : mlist) {
            System.out.println(m.toString() + " - оклад - " + m.getSalary() + ", за месяц - " + m.getMonthSalary() + " руб.");
        }
        System.out.println();
    }
}
