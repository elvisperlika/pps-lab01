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
        int PIN = 1234;
        smartLock.lock();
        assertTrue(smartLock.isLocked());
        smartLock.unlock(PIN);
        assertFalse(smartLock.isLocked());
    }

    @Test
    void testSetNewPin() {
        int OLD_PIN = 1234;
        int NEW_PIN = 5678;
        smartLock.lock();
        smartLock.setPin(NEW_PIN);
        smartLock.unlock(OLD_PIN);
        assertTrue(smartLock.isLocked());
        smartLock.unlock(NEW_PIN);
        assertFalse(smartLock.isLocked());
    }

    @Test
    void testPinHas4Digits() {
        int NEW_PIN = 00;
        assertThrows(IllegalArgumentException.class, () -> smartLock.setPin(NEW_PIN));

    }

}
