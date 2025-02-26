package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> stack;
    private int minValue = Integer.MAX_VALUE;
    private int maxValue = Integer.MIN_VALUE;

    public MinMaxStackImpl() {
        stack = new Stack<>();
    }

    @Override
    public void push(int value) {
        if (value < minValue)
            this.minValue = value;
        if (value > maxValue)
            this.maxValue = value;
        stack.push(value);
    }

    @Override
    public int pop() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty");
        int popValue = stack.pop();
        findNewMin();
        findNewMax();
        return popValue;
    }

    private void findNewMax() {
        this.maxValue = Integer.MIN_VALUE;
        for (int value : stack.stream().toList())
            if (value >= maxValue) {
                maxValue = value;
            }
    }

    private void findNewMin() {
        this.minValue = Integer.MAX_VALUE;
        for (int value : stack.stream().toList())
            if (value <= minValue)
                this.minValue = value;
    }

    @Override
    public int peek() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty");
        return stack.peek();
    }

    @Override
    public int getMin() {
        return this.minValue;
    }

    @Override
    public int getMax() {
        return this.maxValue;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
