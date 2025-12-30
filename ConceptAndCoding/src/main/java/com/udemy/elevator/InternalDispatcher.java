package com.udemy.elevator;

public class InternalDispatcher {
    private static InternalDispatcher instance = new InternalDispatcher();
    private InternalDispatcher(){}
    public static InternalDispatcher getInstance(){
        return instance;
    }
    public void submitInternalRequest(int destinationFloor, ElevatorController controller){
        controller.submitRequest(destinationFloor);
    }
}
