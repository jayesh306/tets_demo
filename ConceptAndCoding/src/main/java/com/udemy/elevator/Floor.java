package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

public class Floor {
    int floorNo;
    ExternalButton upButton;
    ExternalButton downButton;

    public Floor(int floorNo, ExternalDispatcher dispatcher){
        this.floorNo=floorNo;
        this.upButton= new ExternalButton(dispatcher);
        this.downButton= new ExternalButton(dispatcher);
    }
    public void pressUpButton(){
        upButton.pressButton(floorNo, ElevatorDirection.UP);
    }
    public void pressDownButton(){
        downButton.pressButton(floorNo,ElevatorDirection.DOWN);
    }
}
