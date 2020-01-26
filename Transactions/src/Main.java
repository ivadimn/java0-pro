import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Main {
    private static final Random random = new Random();
    private static ArrayList<String> accNumbers = new ArrayList<>();
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        openBank();
        for (int i = 0; i < 20; i++) {
            System.out.println(accNumbers.get(i));
        }

    }

    public static void openBank() {

        for (int i = 0; i < 10000; i++) {
            String accNumber = String.valueOf(Math.abs(random.nextInt()));
            accNumbers.add(accNumber);
            Account account = new Account(accNumber, 30000 + random.nextInt(50000));
            bank.addAccount(account);
        }
    }

}
