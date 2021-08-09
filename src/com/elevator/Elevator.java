package com.elevator;

import java.util.HashMap;
import java.util.HashSet;

public class Elevator {
    enum ElevatorState {
        MOVING_UP,
        MOVING_DOWN,
        SERVING,
        IDLE,
    }

    private int floor;
    private ElevatorState state;
    private final ElevatorQueue stops;
    private final HashMap<Integer, HashSet<Integer>> requestsMap;

    public Elevator() {
        floor = 0;
        state = ElevatorState.IDLE;
        stops = new ElevatorQueue();
        requestsMap = new HashMap<>();
    }

    public int getFloor() {
        return floor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void move() {
        var nextStop = stops.peek();
        updateState(nextStop);
        floor += getDirectionToMove();
        if (floor == nextStop) {
            // remove from stops
            var stop = stops.poll();
            // retrieve list of requests that was recorded for that stop
            var requests = requestsMap.get(stop);
            // add them to the queue
            stops.add(requests);
        }
    }

    public void addRequest(int source, int destination) {
        stops.add(source);
        addDestination(source, destination);
    }

    private void addDestination(int source, int destination) {
        HashSet<Integer> requests;
        if (!requestsMap.containsKey(source)) {
            requests = new HashSet<Integer>(destination);
        } else {
            requests = requestsMap.get(source);
            requests.add(destination);
        }
        requestsMap.put(source, requests);
    }

    private void updateState(Integer nextStop) {
        if (nextStop == null) {
            state = ElevatorState.IDLE;
        } else if (floor == nextStop) {
            state = ElevatorState.SERVING;
        } else if (floor < nextStop) {
            state = ElevatorState.MOVING_UP;
        } else {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    private int getDirectionToMove() {
        switch (state) {
            case MOVING_UP:
                return 1;
            case MOVING_DOWN:
                return -1;
            case IDLE:
            case SERVING:
            default:
                return 0;
        }
    }
}


