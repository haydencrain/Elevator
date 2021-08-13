package com.elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ElevatorQueue {
    private final LinkedList<Integer> queue = new LinkedList<>();

    public ElevatorQueue() {}
    public ElevatorQueue(Iterable<Integer> values) {
        add(values);
    }

    public LinkedList<Integer> getQueue() {
        return queue;
    }

    public void add(Iterable<Integer> floors) {
        floors.forEach(this::add);
    }

    public void add(Integer floor) {
        if (queue.isEmpty()) {
            queue.add(floor);
            return;
        }
        var peak = getPeak();
        var trough = getTrough();
        for (int i = 0; i < queue.size() - 1; i++) {
            var curr = queue.get(i);
            var next = queue.get(i + 1);
            // check if floor is already in queue
            if (floor.equals(curr)) {
                return;
            }
            // check to see if the number can be placed in between two numbers
            if (isNumberBetween(floor, curr, next)) {
                queue.add(i + 1, floor);
                return;
            }

            // check if we're at the "peak" of the list
            if ((curr.equals(peak) && floor > peak) || (curr.equals(trough) && floor < trough)) {
                queue.add(i + 1, floor);
                return;
            }
        }
        // if we reach the last value, the only possible place to place the number is at the end
        queue.add(floor);
    }

    public Integer size() {
        return queue.size();
    }

    public Integer peek() {
        return queue.peek();
    }

    public Integer poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private boolean isNumberBetween(int num, int from, int to) {
        int max = Integer.max(from, to);
        int min = Integer.min(from, to);
        return num > min && num < max;
    }

    private Integer getPeak() {
        var max = Collections.max(queue);
        // we should ignore the start as it's not really the peak
        if (max.equals(queue.getFirst())) {
            return null;
        }
        return max;
    }

    private Integer getTrough() {
        var min = Collections.min(queue);
        // we should ignore the start as it's not really the trough
        if (min.equals(queue.getFirst())) {
            return null;
        }
        return min;
    }
}
