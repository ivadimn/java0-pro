import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static HashSet<String> emailList = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String input;
        System.out.println("Список email аджресов, доступные команды : ");
        System.out.println("ADD 'адрес электронной почты' - добавить email адрес");
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
            if (params[0].equalsIgnoreCase("exit")) break;
            if (params[0].equalsIgnoreCase("LIST")) {
                printList();
                continue;
            }
            if (params.length < 2) {
                continue;
            }
            if (params[0].equalsIgnoreCase("ADD")) {
                if (isValidEmail(params[1])) {
                    emailList.add(params[1]);
                }
                else {
                    System.out.println("Не правильный email адрес");
                }
            }
        }
    }

    public static boolean isValidEmail(String em) {
        int index = em.indexOf("@");
        if (index < 0) return false;
        String dns = em.substring(index + 1);
        return dns.indexOf(".") > 0;
    }

    public static void printList() {
        for (String em : emailList) {
            System.out.println(em);
        }
    }
}
