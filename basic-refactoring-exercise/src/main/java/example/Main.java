package example;

import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

public class Main {

    public static final int AMOUNT_100 = 100;
    public static final int AMOUNT_30 = 30;
    public static final int AMOUNT_80 = 80;

    public static void main(String[] args) {
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        final BankAccount bankAccount = new SimpleBankAccount(accountHolder, 0);
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        System.out.println("Current balance is " + bankAccount.getBalance());
        bankAccount.withdraw(accountHolder.getId(), AMOUNT_30);
        System.out.println("Current balance is " + bankAccount.getBalance());
        bankAccount.withdraw(accountHolder.getId(), AMOUNT_80);
        System.out.println("Current balance is " + bankAccount.getBalance());
    }
}
