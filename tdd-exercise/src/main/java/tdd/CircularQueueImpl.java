package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    private final int capacity;
    private final List<Integer> list;

    public CircularQueueImpl(int size) {
        this.capacity = size;
        list = new ArrayList<>(size);
    }

    @Override
    public void add(int value) {
        if (list.size() == this.capacity) {
            shiftRightList();
            addInFirstPosition(value);
        } else {
            list.add(value);
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    private void addInFirstPosition(int value) {
        list.set(0, value);
    }

    private void shiftRightList() {
        for (int i = list.size() - 1; i > 1; i--) {
            list.set(i, list.get(i - 1));
        }
    }

    @Override
    public int getCurrentSize() {
        return list.size();
    }

    @Override
    public int getByIndex(int index) {
        if (index < 0 || index > this.getCurrentSize())
            throw new IllegalArgumentException("Out of Bound.");
        return list.get(index);
    }

    @Override
    public void removeOldest() {
        if (list.isEmpty())
            throw new IllegalStateException("Queue is empty, no element to remove.");
        list.remove(0);
    }
}
