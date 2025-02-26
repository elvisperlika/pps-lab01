package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int PIN_MAX_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 4;
    private static final String DEFAULT_PIN = "1234";
    private boolean isLock;
    private String pin;
    private int failedAttempts;
    private boolean isBlock;

    public SmartDoorLockImpl() {
        this.isLock = false;
        this.pin = DEFAULT_PIN;
        this.failedAttempts = 0;
    }

    @Override
    public void setPin(final String pin) {
        checkPinLength(pin);
        this.pin = pin;
    }

    private static void checkPinLength(String pin) {
        if (pin.length() != PIN_MAX_LENGTH)
            throw new IllegalArgumentException("Number of digits wrong");
    }

    @Override
    public void unlock(final String pin) {
        if (this.isBlocked())
            throw new IllegalStateException("Smart Lock is blocked, Reset it!");
        else if (!isLock)
            throw new IllegalStateException("Smarl Lock is already unlocked.");
        else if (getFailedAttempts() <= getMaxAttempts() && isLock && isPinCorrect(pin)) {
            inizialiseFailedAttemptsCounter();
            this.isLock = false;
        } else {
            this.failedAttempts++;
            if (getFailedAttempts() > getMaxAttempts())
                goInBlockMode();
        }
    }

    private void goInBlockMode() {
        this.isBlock = true;
    }

    private void inizialiseFailedAttemptsCounter() {
        this.failedAttempts = 0;
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
        return failedAttempts;
    }

    @Override
    public void reset() {
        inizialiseFailedAttemptsCounter();
        this.isLock = false;
        this.isBlock = false;
        setPin(DEFAULT_PIN);
    }
}
