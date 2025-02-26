package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    private MinMaxStack stack;

    @BeforeEach
    void setUp() {
        stack = new MinMaxStackImpl();
    }

    @Test
    void testIsEmptyByDefault() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPushValue() {
        int valueToPush = 7;
        stack.push(valueToPush);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testThePushedValueIsPushedCorrectly() {
        int valueToPush = 9;
        stack.push(valueToPush);
        int valuePopped = stack.pop();
        assertEquals(valueToPush, valuePopped);
    }

    @Test
    void testPopFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void testSize() {
        int numberOfValueToPush = 10;
        Random rand = new Random();
        for (int i = 0; i < numberOfValueToPush; i++) {
            stack.push(rand.nextInt());
        }
        assertEquals(numberOfValueToPush, stack.size());
    }

    @Test
    void  testPeekFromStack() {
        int valueToPush = 8;
        stack.push(valueToPush);
        int stackSizePrePeeking = stack.size();
        int peekedValue = stack.peek();
        int stackSizeAfterPeeking = stack.size();
        assertEquals(stackSizePrePeeking, stackSizeAfterPeeking);

        assertEquals(peekedValue, stack.pop());
    }

    @Test
    void testPeekFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    void testMinAfterPushing() {
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(9);
        assertEquals(2, stack.getMin());
    }

    @Test
    void testMinAfterPop() {
        stack.push(4);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.pop();
        assertEquals(2, stack.getMin());
    }

}