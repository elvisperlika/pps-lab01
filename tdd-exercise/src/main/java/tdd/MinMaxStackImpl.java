package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MinMaxStackImpl implements MinMaxStack {

    private Stack<Integer> stack;
    public MinMaxStackImpl() {
        stack = new Stack<>();
    }

    @Override
    public void push(int value) {
        stack.push(value);
    }

    @Override
    public int pop() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty");
        return stack.pop();
    }

    @Override
    public int peek() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty");
        return stack.peek();
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
        return stack.size();
    }
}
