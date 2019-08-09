import model.CardAccount;
import model.DepositAccount;

public class Main {
    public static void main(String[] args) {
        DepositAccount d = new DepositAccount(2000.0F);
        d.withdraw(1000);
        d.showBalance();

        System.out.println("------------------------------");

        CardAccount cardAccount = new CardAccount(3000.0F);
        cardAccount.withdraw(733);
        cardAccount.showBalance();
    }
}
