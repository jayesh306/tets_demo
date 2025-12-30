package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

import java.util.List;

public interface ElevationStrategy {
    ElevatorController selectElevator(List<ElevatorController> controller, int requestFloor, ElevatorDirection direction);
}
