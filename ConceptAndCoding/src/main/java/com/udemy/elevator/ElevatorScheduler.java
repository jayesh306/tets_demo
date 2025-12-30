package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

import java.util.List;

public class ElevatorScheduler {
    private final List<ElevatorController> elevatorController;
    private ElevationStrategy elevationStrategy;

    public ElevatorScheduler(List<ElevatorController> elevatorController, ElevationStrategy elevationStrategy){
        this.elevatorController=elevatorController;
        this.elevationStrategy=elevationStrategy;
    }

    public void setElevationStrategy(ElevationStrategy elevationStrategy) {
        this.elevationStrategy = elevationStrategy;
    }
    public ElevatorController assignElevator(int floor, ElevatorDirection elevatorDirection){
        return elevationStrategy.selectElevator(elevatorController,floor,elevatorDirection);
    }
}
