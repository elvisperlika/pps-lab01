package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}