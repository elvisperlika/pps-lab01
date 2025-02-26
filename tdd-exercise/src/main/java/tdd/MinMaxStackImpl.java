package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private List<Integer> stack;
    public MinMaxStackImpl() {
        stack = new ArrayList<>();
    }

    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        return stack.get(getIndexOfLastPush());
    }

    private int getIndexOfLastPush() {
        return stack.size() - 1;
    }

    @Override
    public int peek() {
        return 0;
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return 0;
    }
}
