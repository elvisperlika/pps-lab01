package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int PIN_MAX_LENGTH = 4;
    private boolean isLock;
    private String pin = "1234";

    public SmartDoorLockImpl() {
        this.isLock = false;
    }

    @Override
    public void setPin(final String pin) {
        String stringPin = String.valueOf(pin);
        checkPinLength(stringPin);
        this.pin = pin;
    }

    private static void checkPinLength(String pin) {
        if (pin.length() != PIN_MAX_LENGTH)
            throw new IllegalArgumentException("Number of digits wrong");
    }

    @Override
    public void unlock(final String pin) {
        if (isLock && isPinCorrect(pin))
            this.isLock = false;
    }

    private boolean isPinCorrect(final String pin) {
        return this.pin.equals(pin);
    }

    @Override
    public void lock() {
        if (this.isLock)
            throw new IllegalStateException("Locker already locked");
        this.isLock = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLock;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
