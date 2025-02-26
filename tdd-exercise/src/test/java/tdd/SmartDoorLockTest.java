package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final String DEFAULT_PIN = "1234";
    private SmartDoorLock smartLock;

    @BeforeEach
    void setup() {
        smartLock = new SmartDoorLockImpl();
    }

    @Test
    void isUnlockedByDefault() {
        assertFalse(smartLock.isLocked());
    }

    @Test
    void isLocked() {
        smartLock.lock();
        assertTrue(smartLock.isLocked());
    }

    @Test
    void testLockWhenAlreadyLocked() {
        smartLock.lock();
        assertThrows(IllegalStateException.class, () -> smartLock.lock());
    }

    @Test
    void testUnlockOnFirstSetup() {
        String PIN = "1234";
        smartLock.lock();
        assertTrue(smartLock.isLocked());
        smartLock.unlock(PIN);
        assertFalse(smartLock.isLocked());
    }

    @Test
    void testUnlockWhenAlreadyUnlocked() {
        assertThrows(IllegalStateException.class, () -> smartLock.unlock(DEFAULT_PIN));
    }

    @Test
    void testSetNewPin() {
        String NEW_PIN = "5678";
        smartLock.lock();
        smartLock.setPin(NEW_PIN);
        smartLock.unlock(DEFAULT_PIN);
        assertTrue(smartLock.isLocked());
        smartLock.unlock(NEW_PIN);
        assertFalse(smartLock.isLocked());
    }

    @Test
    void testPinHas4Digits() {
        String NEW_PIN = "000";
        assertThrows(IllegalArgumentException.class, () -> smartLock.setPin(NEW_PIN));
    }

    @Test
    void testDeviceBlockAfterMaxAttempts() {
        blockDeviceWithMultipleUnlockErrors();
        assertTrue(smartLock.isBlocked());
    }

    @Test
    void testUnlockWhenSmartLockIsBlocked() {
        blockDeviceWithMultipleUnlockErrors();
        String WRONG_PIN = "9999";
        assertThrows(IllegalStateException.class, () -> smartLock.unlock(WRONG_PIN));
    }

    @Test
    void testResetFromBlockMode() {
        blockDeviceWithMultipleUnlockErrors();
        smartLock.reset();
        smartLock.lock();
        smartLock.unlock(DEFAULT_PIN);
    }

    private void blockDeviceWithMultipleUnlockErrors() {
        smartLock.setPin(DEFAULT_PIN);
        smartLock.lock();
        String WRONG_PIN = "9999";
        for (int i = 0; i <= smartLock.getMaxAttempts(); i++) {
            smartLock.unlock(WRONG_PIN);
        }
    }

}
