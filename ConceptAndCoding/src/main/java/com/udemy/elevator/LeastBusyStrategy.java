package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

import java.util.List;

public class LeastBusyStrategy implements ElevationStrategy{
    @Override
    public ElevatorController selectElevator(List<ElevatorController> controllers, int requestFloor, ElevatorDirection direction){
        ElevatorController bestElevator = null;
        int minLoad = Integer.MAX_VALUE;
        for(ElevatorController controller : controllers){
            int load = controller.upMinPQ.size() + controller.downMaxPQ.size();
            if(load<minLoad){
                minLoad = load;
                bestElevator = controller;
            }
        }
        return bestElevator;
    }
}
