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
    void isOpen() {
        assertFalse(smartLock.isLocked());
    }

    @Test
    void isLocked() {
        smartLock.lock();
        assertTrue(smartLock.isLocked());
    }

}
