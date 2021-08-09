package com.elevator;

import java.util.LinkedList;

public class ElevatorQueue {
    LinkedList<Integer> queue;

    public ElevatorQueue() {
        queue = new LinkedList<>();
}

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Integer peek() {
        return queue.peek();
    }

    public Integer poll() {
        return queue.poll();
    }

    public void add(Integer floor) {
        for (int i = 0; i < queue.size() - 1; i++) {
            var curr = queue.get(i);
            if (floor.equals(curr)) {
                // floor is already in queue
                return;
            }
            if (i == queue.size() - 1) {
                // if we reach the last value, the only possible place to place the number is at the end
                queue.add(floor);
                return;
            }
            // check to see if the number can be placed in between two numbers
            var next = queue.get(i + 1);
            if (isNumberBetween(floor, curr, next)) {
                queue.add(i + 1, floor);
                return;
            }
        }
    }

    public void add(Iterable<Integer> floors) {
        floors.forEach(this::add);
    }

    private boolean isNumberBetween(int num, int from, int to) {
        int max = Integer.max(from, to);
        int min = Integer.min(from, to);
        return num > min && num < max;
    }
}
