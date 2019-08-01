import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static HashSet<String> emailList = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String input;
        System.out.println("Список , доступные команды : ");
        System.out.println("ADD 'адрес электронной почты'");
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
            if (params.length < 2) {
                continue;
            }
            if (params[0].equalsIgnoreCase("ADD")) {
                //addBussines(params);
                continue;
            }

        }
    }
}
