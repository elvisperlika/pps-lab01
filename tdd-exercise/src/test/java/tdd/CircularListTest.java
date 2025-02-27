package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    int size = 10;
    CircularQueue circularQueue;
    @BeforeEach
    void setup() {
        circularQueue = new CircularQueueImpl(size);
    }

    @Test
    void testAddInQueue() {
        int valueToAdd = 2;
        circularQueue.add(valueToAdd);
    }

    @Test
    void testQueueSizeNotChange() {
        int surplusElements = 7;
        Random rand = new Random();
        int previousSize = this.size;
        for (int i = 0; i < (previousSize + surplusElements); i++) {
            circularQueue.add(rand.nextInt(1, 5));
        }
        assertEquals(previousSize, circularQueue.getSize());
    }

    @Test
    void testAddInFullQueue(){
        Random rand = new Random();
        for (int i = 0; i < circularQueue.getSize(); i++) {
            circularQueue.add(rand.nextInt(1, 5));
        }
        int differentValueToAdd = 1;
        circularQueue.add(differentValueToAdd);
        assertEquals(differentValueToAdd, circularQueue.getByIndex(0));
    }

    @Test
    void testRemoveOldestValue() {
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(1, 200); i++) {
            circularQueue.add(rand.nextInt(1, 5));
        }
        circularQueue.removeOldest();
        circularQueue.removeOldest();
        assertEquals(this.size - 2, circularQueue.getSize());
    }

    @Test
    void testRemoveFromEmptyList() {
        assertThrows(IllegalStateException.class, () -> circularQueue.removeOldest());
    }

}
