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

    private int id;
    private int floor;
    private ElevatorState state;
    private final ElevatorQueue stops;
    private final HashMap<Integer, HashSet<Integer>> requestsMap;

    public Elevator(int id) {
        this.id = id;
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
        state = getNextState(nextStop);
        handleState();
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

    private ElevatorState getNextState(Integer nextStop) {
        if (nextStop == null) {
            return ElevatorState.IDLE;
        }
        if (floor == nextStop) {
            return ElevatorState.SERVING;
        }
        if (floor < nextStop) {
            return ElevatorState.MOVING_UP;
        }
        return ElevatorState.MOVING_DOWN;
    }

    private void handleState() {
        switch (state) {
            case MOVING_UP:
                moveUp();
                break;
            case MOVING_DOWN:
                moveDown();
                break;
            case SERVING:
                servePassengers();
            case IDLE:
            default:
                break;
        }
    }

    private void moveUp() {
        floor += 1;
        System.out.println("Elevator " + id + " moving up to floor " + floor);
    }

    private void moveDown() {
        floor -= 1;
        System.out.println("Elevator " + id + " moving down to floor " + floor);
    }

    private void servePassengers() {
        System.out.println("Elevator " + id + " has stopped at floor " + floor);
        // remove current floor from stops
        var stop = stops.poll();
        // retrieve list of requests that was recorded for that stop
        var requests = requestsMap.get(stop);
        // add them to the queue
        stops.add(requests);
    }
}


