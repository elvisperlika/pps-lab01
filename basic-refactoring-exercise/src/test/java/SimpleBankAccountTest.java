import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final double WITHDRAW_FEE = 1;
    private AccountHolder accountHolder;
    private AccountHolder accountHolder2;
    private BankAccount bankAccount;
    private BankAccount bankAccount2;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);

        accountHolder2 = new AccountHolder("Giovanni", "Rana", 2);
        bankAccount2 = new SimpleBankAccount(accountHolder2, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        final int amount = 100;
        final int expected = 100;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(expected, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int amountForAccount1 = 100;
        final int amountForAccount2 = 50;
        final int expected = 100;
        bankAccount.deposit(accountHolder.getId(), amountForAccount1);
        bankAccount.deposit(2, amountForAccount2);
        assertEquals(expected, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNegative() {
        final int negativeWithdrawAmount = -100;
        final double previousBalance = bankAccount.getBalance();
        bankAccount.withdraw(accountHolder.getId(), negativeWithdrawAmount);
        assertEquals(previousBalance, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int amount = 100;
        final int withdrawAmount = 70;
        final int expected = 30;
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.withdraw(accountHolder.getId(), withdrawAmount);
        assertEquals(expected, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final int amount = 100;
        final int expected = 100;
        int withdrawAmount = 70;
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.withdraw(accountHolder2.getId(), withdrawAmount);
        assertEquals(expected, bankAccount.getBalance());
    }

    @Test
    void testFeeWhenWithdraw() {
        final int amount = 100;
        final int withdrawAmount = 100;
        double previousBalance = bankAccount.getBalance();
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.withdrawWithFee(accountHolder.getId(), withdrawAmount);
        assertEquals(bankAccount.getBalance(), previousBalance - WITHDRAW_FEE);
    }
}
