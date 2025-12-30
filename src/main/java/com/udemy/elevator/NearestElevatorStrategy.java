package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

import java.util.List;

public class NearestElevatorStrategy implements ElevationStrategy{
    @Override
    public ElevatorController selectElevator(List<ElevatorController> controllers, int requestFloor, ElevatorDirection direction){
        ElevatorController bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (ElevatorController controller : controllers){
            int nextFloorStoppage = controller.elevatorCar.nextFloorStoppage;

            //Good candidate if moving in the same direction and min distance from nextFloorStoppage
            boolean isSameDirection = controller.elevatorCar.movingDirection == direction && ((direction == ElevatorDirection.UP && nextFloorStoppage <= requestFloor) || (direction == ElevatorDirection.DOWN && nextFloorStoppage >= requestFloor));
            int dist = Math.abs(requestFloor - nextFloorStoppage);
            if(isSameDirection && dist < minDistance){
                minDistance = dist;
                bestElevator = controller;
            }
        }
        //fallback if the elevator is not in the same direction or bigger than requestedFloor, pick the idle one then
        if(bestElevator == null){
            for (ElevatorController controller : controllers){
                if(controller.elevatorCar.movingDirection == ElevatorDirection.IDLE){
                    bestElevator = controller;
                    break;
                }
            }
            //No lift is going in the same direction and no lift is idle too, pick any lift then
            if(bestElevator==null){
                bestElevator = controllers.get(0);
            }
        }
        return bestElevator;
    }
}
