import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int AMOUNT_100 = 100;
    public static final int AMOUNT_50 = 50;
    public static final int AMOUNT_70 = 70;
    public static final int EXPECTED_30 = 30;
    private static final double WITHDRAW_FEE = 1;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        assertEquals(AMOUNT_100, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        bankAccount.deposit(2, AMOUNT_50);
        assertEquals(AMOUNT_100, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        bankAccount.withdraw(accountHolder.getId(), AMOUNT_70);
        assertEquals(EXPECTED_30, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        bankAccount.withdraw(2, AMOUNT_70);
        assertEquals(AMOUNT_100, bankAccount.getBalance());
    }

    @Test
    void testFeeWhenWithdraw() {
        double previousBalance = bankAccount.getBalance();
        bankAccount.deposit(accountHolder.getId(), AMOUNT_100);
        bankAccount.withdrawWithFee(accountHolder.getId(), AMOUNT_100);
        assertEquals(bankAccount.getBalance(), previousBalance - WITHDRAW_FEE);
    }
}
