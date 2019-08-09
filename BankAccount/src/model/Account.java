package model;

public class Account {

    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double summa) {
        this.balance += summa;
    }

    public void withdraw(double summa) {
        if (balance - summa >= 0) {
            balance -= summa;
        }
        else {
            System.out.println("Недостаточно средств на счёте ...");
        }
    }

    public void showBalance() {
        System.out.printf("%s - %.2f\n", "Текущий остаток на счёте", balance);
    }

    public double getBalance() {
        return balance;
    }
}
