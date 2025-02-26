package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Add value in queue.
     *
     * @param value the Integer to add
     */
    void add(int value);

    /**
     * Get capacity of the queue.
     */
    int getCapacity();

    /**
     * Get the queue size.
     *
     * @return size
     */
    int getCurrentSize();

    /**
     * Get the value of the element in queue by index.
     *
     * @param index of the element
     * @return value of the element
     */
    int getByIndex(int index);

    /**
     * Remove the oldest value in the queue.
     *
     * @throws IllegalStateException if the queue is empty
     */
    void removeOldest();
}