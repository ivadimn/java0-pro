import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)
    {
        Account accountFrom = accounts.get(fromAccountNum);
        Account accountTo = accounts.get(toAccountNum);
        if (accountFrom.getIsBlocked()) {
            System.out.println("Счёт № " + fromAccountNum + " заблокирован!");
            return;
        }
        if (accountTo.getIsBlocked()) {
            System.out.println("Счёт № " + toAccountNum + " заблокирован!");
            return;
        }
        long summa = accountFrom.withdraw(amount);
        if (summa > 0) {
            accountTo.toDeposit(summa);
            System.out.println("Деньги в сумме " + summa + " со счёта: "
                    + fromAccountNum + "на счёт: " + toAccountNum + " переведены!");
            if (summa > 50000) {
                try {
                    boolean isBlock = isFraud(fromAccountNum, toAccountNum, amount);
                    if (isBlock) {
                        accountFrom.block();
                        accountTo.block();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Недостаточно средств для перевода !");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        String accountNum = account.getAccNumber();
        accounts.put(accountNum, account);
    }
}
