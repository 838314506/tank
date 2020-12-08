package com.myself.tank.state;

public class Car {

    public Car(Action action){
        this.action = action;
    }

    private Action action ;

    public static void main(String[] args){
        Car c = new Car(new CloseAction());
        c.openTheDoor();
        c.closeTheDoor();
        c.runTheCar();
        c.stopTheCar();
    }

    public void openTheDoor() {
        action.openTheDoor();
    }

    public void closeTheDoor() {
        action.closeTheDoor();
    }

    public void runTheCar() {
        action.runTheCar();
    }

    public void stopTheCar() {
        action.stopTheCar();
    }
}
