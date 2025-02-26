package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

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
    void testSetNewPin() {
        String OLD_PIN = "1234";
        String NEW_PIN = "5678";
        smartLock.lock();
        smartLock.setPin(NEW_PIN);
        smartLock.unlock(OLD_PIN);
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
        String PIN = "1234";
        smartLock.setPin(PIN);
        String WRONG_PIN = "9999";
        for (int i = 0; i < smartLock.getMaxAttempts(); i++) {
            smartLock.unlock(WRONG_PIN);
        }
        assertTrue(smartLock.isBlocked());
    }

}
