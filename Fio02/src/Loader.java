import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static final String[] ARABIAN = {"бар", "бен", "ибн", "бине", "бин", "мак"};
    public static final String[] NATIONAL = {"оглы", "улы", "уулу", "кызы", "гызы"};
    public static final String[] LABELS = {"Фамилия", "Имя", "Отчество"};


    public static void main(String[] args) throws IOException {

        System.out.println("Введите Фамилию Имя Отчество :");
        String fullName = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        String[] fio = fullName.split("[\\d\\s,.|?:;]+");

        switch(fio.length) {
            case 0:
            case 1:
                System.out.println("Слишком мало слов !!!");
                break;
            case 2:
                printFio(fio);
                break;
            case 3:
                if (isArabian(fio[1])) {
                    System.out.println("Имя : " + fio[0]);
                    System.out.println("Отчество : " + fio[1] + " " + fio[2]);
                }
                else {
                    printFio(fio);
                }
                break;
            case 4:
                if (isNational(fio[3])) {
                    System.out.println("Фамилия : " + fio[0]);
                    System.out.println("Имя : " + fio[1]);
                    System.out.println("Отчество : " + fio[2] + " " + fio[3]);
                }
                else {
                    System.out.println("Слишком много слов !!!");
                }
                break;
            default:
                System.out.println("Слишком много слов !!!");

        }
    }

    public static void printFio(String[] fio) {
        for (int i = 0; i < fio.length; i++) {
            System.out.println(String.format("%s : %s", LABELS[i], fio[i]));
        }
    }

    public static boolean isArabian(String part) {
        for (String p : ARABIAN) {
            if (p.equalsIgnoreCase(part))
             return true;
        }
        return false;
    }

    public static boolean isNational(String part) {
        for (String p : NATIONAL) {
            if (p.equalsIgnoreCase(part))
                return true;
        }
        return false;
    }

}
