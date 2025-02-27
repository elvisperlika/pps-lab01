package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    private final int size;
    private final List<Integer> list;

    public CircularQueueImpl(int size) {
        this.size = size;
        list = new ArrayList<>(size);
    }

    @Override
    public void add(int value) {
        if (list.size() == this.size) {
            shiftRightList();
            addInFirstPosition(value);
        } else {
            list.add(value);
        }
    }

    private void addInFirstPosition(int value) {
        list.set(0, value);
    }

    private void shiftRightList() {
        for (int i = this.size - 1; i > 1; i--) {
            list.set(i, list.get(i - 1));
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public int getByIndex(int index) {
        return list.get(index);
    }
}
