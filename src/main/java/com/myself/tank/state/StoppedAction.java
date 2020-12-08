package com.myself.tank.state;

public class StoppedAction implements Action {
    @Override
    public void openTheDoor() {
        System.out.println("yes");
    }

    @Override
    public void closeTheDoor() {
        System.out.println("yes");
    }

    @Override
    public void runTheCar() {
        System.out.println("yes");
    }

    @Override
    public void stopTheCar() {
        System.out.println("no");
    }
}
