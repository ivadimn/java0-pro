import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static final String[] FNAMES = {"Иван", "Пётр", "Сергей", "Андрей", "Антон", "Виктор", "Михаил", "Александр"};
    public static final String[] SNAMES = {"Иванов", "Петров", "Сергеев", "Андреев", "Антонов", "Викторов",
            "Михайлов", "Александров"};
    public static final String[] MNAMES = {"Иванович", "Петрович", "Сергеевич", "Андреевич", "Антонович", "Викторович",
            "Михайлович", "Александрович"};

    public static final Random random = new Random();
    public static Company company;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.println("Введите наименование компании (или 'exit' для завершения ): ");
            String name = reader.readLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            company = new Company(name);
            System.out.println( name + " принимает сотрудников...");
            System.out.println("На работу принято - " + generateEmploee(company) + " сотрудников");
            //System.out.println("Посмотрим кто сколько заработал...");
            //show(company);
            System.out.println("Зарплаты принятых в 2017 году");
            company.getSalaryRecruitInYear(2017).stream()
             .forEach(e -> System.out.println(e.getName() + " зарплата - " + e.getMonthSalary(company.getIncome())
                    + " год - " + e.getYearRecruit()));;
        }
    }

    public static int generateEmploee(Company company) {
        //топ менеджеры
        for (int i = 0; i < 10; i++) {
            Emploee m = new TopManager(FNAMES[random.nextInt(8)], SNAMES[random.nextInt(8)],
                    MNAMES[random.nextInt(8)]);
            company.recruit(m);
        }
        //продажники
        int numSales = 180 + random.nextInt(20);
        for (int i = 0; i < numSales + 1; i++) {
            Emploee m = new SalesManager(FNAMES[random.nextInt(8)], SNAMES[random.nextInt(8)],
                    MNAMES[random.nextInt(8)]);
            company.recruit(m);
        }
        int numClerks = 50 + random.nextInt(20);
        for (int i = 0; i < numClerks + 1; i++) {
            Emploee m = new Clerk(FNAMES[random.nextInt(8)], SNAMES[random.nextInt(8)],
                    MNAMES[random.nextInt(8)]);
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

    public static void printManagers(List<Emploee> mlist, String info) {
        System.out.println(info);
        for (Emploee m : mlist) {
            System.out.println(m.getName() + " - оклад - " + m.getSalary() + ", за месяц - "
                    + m.getMonthSalary(company.getIncome()) + " руб.");
        }
        System.out.println();
    }
}
