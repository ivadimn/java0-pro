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
        doWork();

    }

    public static void doWork() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Transfer()).start();
        }
    }

    public static void openBank() {
        for (int i = 0; i < 4; i++) {
            String accNumber = String.valueOf(Math.abs(random.nextInt()));
            accNumbers.add(accNumber);
            Account account = new Account(accNumber, 30000 + random.nextInt(50000));
            bank.addAccount(account);
        }
    }

    public static class Transfer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String accFrom = accNumbers.get(random.nextInt(accNumbers.size() - 1));
                String accTo = accNumbers.get(random.nextInt(accNumbers.size() - 1));
                long amount = random.nextInt(52500);
                if (!accFrom.equals(accTo)) {
                    bank.transfer(accFrom, accTo, amount);
                }
            }

        }
    }

}
