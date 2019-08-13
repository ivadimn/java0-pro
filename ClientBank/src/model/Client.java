package model;

public abstract class Client {
    private double balance;

    public Client(double balance) {
        this.balance = balance;
    }

    public void deposit(double summa) {
        this.balance += summa;
    }

    public void withdraw(double summa) {
        if (balance >= summa) {
            balance -= summa;
        }
        else {
            System.out.println("Недостаточно средств на счёте!");
        }
    }

    public double getBalance() {
        return balance;
    }
}
