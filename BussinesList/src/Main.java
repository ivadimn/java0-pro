import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> bussinesList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String input;
        System.out.println("Список дел, доступные команды : ");
        System.out.println("ADD 'навание дела' - добавить дело");
        System.out.println("ADD 'номер' 'навание дела' - добавить дело в позицию с 'номер'");
        System.out.println("EDIT 'номер' 'н6овое навание дела' - изменить дело в позиции 'номер' ");
        System.out.println("DELETE 'номер' - удалить дело в позиции  'номер'");
        System.out.println("LIST");
        System.out.println("exit");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(;;) {
            System.out.println("Введите команду :");
            input = reader.readLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            String[] params = input.split("\\s+");
            if (params[0].equalsIgnoreCase("LIST")) {
                printList();
                continue;
            }
            if (params[0].equalsIgnoreCase("exit")) break;
            if (params.length < 2) {
                continue;
            }
            if (params[0].equalsIgnoreCase("ADD")) {
                addBussines(params);
                continue;
            }
            if (params[0].equalsIgnoreCase("DELETE")) {
                delBussines(params);
                continue;
            }
            if (params.length < 3) {
                continue;
            }
            if (params[0].equalsIgnoreCase("EDIT")) {
                editBussines(params);
            }
        }
    }

    public static void addBussines(String[] b) {
        int len = b.length;
        if (len == 2) {
            bussinesList.add(b[1]);
        }
        else {
            int index = getIndex(b[1]);
            if (index >= 0 && index <= bussinesList.size()) {
                bussinesList.add(index, getBussinesText(b, 2));
                System.out.println("Дело добавлено");
            }
            else  if (index > bussinesList.size()) {
                System.out.println("Неправильный индекс");
            }
            else {
                bussinesList.add(getBussinesText(b, 1));
                System.out.println("Дело добавлено");
            }
        }
    }

    public static void editBussines(String[] b) {
        int len = b.length;
        int index = getIndex(b[1]);
        if (index >= 0 && index < bussinesList.size()) {
            bussinesList.set(index, getBussinesText(b, 2));
                    System.out.println("Дело изменено");
        }
        else  {
            System.out.println("Неправильный индекс");
        }
    }

    public static void delBussines(String[] b) {
        int len = b.length;
        int index = getIndex(b[1]);
        if (index >= 0 && index < bussinesList.size()) {
            bussinesList.remove(index);
            System.out.println("Дело удалено");
        }
        else {
            System.out.println("Неправильный индекс");
        }
    }

    public static void printList() {
        for (int i = 0; i < bussinesList.size(); i++) {
            System.out.println((i + 1) + " - " + bussinesList.get(i));
        }
    }

    public static int getIndex(String s) {
        try {
            return Integer.parseInt(s) - 1 ;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getBussinesText(String[] b, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < b.length; i++) {
            sb.append(b[i] + " ");
        }
        return sb.toString();
    }
}
