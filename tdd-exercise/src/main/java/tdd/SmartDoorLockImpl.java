package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int PIN_MAX_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 4;
    private boolean isLock;
    private String pin = "1234";
    private int tryCounter = 0;
    private boolean isBlock;

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
        if (isLock && isPinCorrect(pin) && tryCounter < getMaxAttempts()) {
            inizialiseTryCounter();
            this.isLock = false;
        } else {
            this.tryCounter++;
            if (tryCounter >= getMaxAttempts())
                goInBlockMode();
        }
    }

    private void goInBlockMode() {
        this.isBlock = true;
    }

    private void inizialiseTryCounter() {
        this.tryCounter = 0;
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
        return this.isBlock;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
