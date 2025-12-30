package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

public class ExternalButton {
    private final ExternalDispatcher dispatcher;
    public  ExternalButton(ExternalDispatcher dispatcher){
        this.dispatcher=dispatcher;
    }

    //this direction of external elevator will help in choosing elevator
    public void pressButton(int floor, ElevatorDirection direction){
        dispatcher.submitExternalRequest(floor, direction);
    }
}
