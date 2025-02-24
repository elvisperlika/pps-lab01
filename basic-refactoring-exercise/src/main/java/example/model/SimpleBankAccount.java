package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private static final double WITHDRAW_FEE = 1;
    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        checkUserValidity(userID);
        checkWithdrawAmountValidity(amount);
        this.balance -= amount;
    }

    private void checkWithdrawAmountValidity(double amount) {
        if (!isWithdrawAllowed(amount))
            throw new IllegalArgumentException("Amount invalid: negative or grater than balance");
    }

    private void checkUserValidity(int userID) {
        if (!checkUser(userID))
            throw new IllegalArgumentException("User ID wrong");
    }

    @Override
    public void withdrawWithFee(final int userID, final double amount) {
        withdraw(userID, (amount + WITHDRAW_FEE));
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount && amount > 0;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
