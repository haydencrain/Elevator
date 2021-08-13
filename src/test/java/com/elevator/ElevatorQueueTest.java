package com.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorQueueTest {

    @Test
    void test_size_returns_0_on_empty() {
        var queue = new ElevatorQueue();
        assertEquals(0, queue.size());
    }

    @Test
    void test_size_returns_correct_size() {
        var queue = new ElevatorQueue(List.of(1, 2, 3, 4));
        assertEquals(4, queue.size());
    }

    @Test
    void test_add_adds_single_value() {
        var queue = new ElevatorQueue();
        queue.add(1);
        assertEquals(List.of(1), queue.getQueue());
    }

    @Test
    void test_add_adds_2_values() {
        var queue = new ElevatorQueue();
        queue.add(1);
        queue.add(2);
        assertEquals(List.of(1, 2), queue.getQueue());
    }

    @Test
    void test_add_lower_number_added_to_end() {
        var queue = new ElevatorQueue(List.of(4, 5, 8));
        queue.add(3);
        assertEquals(List.of(4, 5, 8, 3), queue.getQueue());
    }

    @Test
    void test_add_number_added_between() {
        var queue = new ElevatorQueue(List.of(4, 5, 8));
        queue.add(7);
        assertEquals(List.of(4, 5, 7, 8), queue.getQueue());
    }

    @Test
    void test_add_largest_number_added_to_top() {
        var queue = new ElevatorQueue(List.of(6, 8, 4));
        queue.add(9);
        assertEquals(List.of(6, 8, 9, 4), queue.getQueue());
    }

    @Test
    void test_add_smallest_number_added_to_bottom() {
        var queue = new ElevatorQueue(List.of(4, 2, 6));
        queue.add(1);
        assertEquals(List.of(4, 2, 1, 6), queue.getQueue());
    }

    @Test
    void test_add_multiple_with_peak() {
        var queue = new ElevatorQueue(List.of(4, 6, 7, 8));
        queue.add(3);
        assertEquals(List.of(4, 6, 7, 8, 3), queue.getQueue());
        queue.add(9);
        assertEquals(List.of(4, 6, 7, 8, 9, 3), queue.getQueue());
        queue.add(5);
        assertEquals(List.of(4, 5, 6, 7, 8, 9, 3), queue.getQueue());
        queue.add(1);
        assertEquals(List.of(4, 5, 6, 7, 8, 9, 3, 1), queue.getQueue());
        queue.add(2);
        assertEquals(List.of(4, 5, 6, 7, 8, 9, 3, 2, 1), queue.getQueue());
    }

    @Test
    void test_add_multiple_with_trough() {
        var queue = new ElevatorQueue(List.of(7, 5, 3));
        queue.add(2);
        queue.add(9);
        assertEquals(List.of(7, 5, 3, 2, 9), queue.getQueue());
        queue.add(8);
        assertEquals(List.of(7, 5, 3, 2, 8, 9), queue.getQueue());
        queue.add(6);
        assertEquals(List.of(7, 6, 5, 3, 2, 8, 9), queue.getQueue());
        queue.add(1);
        assertEquals(List.of(7, 6, 5, 3, 2, 1, 8, 9), queue.getQueue());
        queue.add(4);
        assertEquals(List.of(7, 6, 5, 4, 3, 2, 1, 8, 9), queue.getQueue());
    }
}