package com.udemy.elevator;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        try {
            //Create elevators and their controllers
            ElevatorCar car1 = new ElevatorCar(1);
            ElevatorController controller1 = new ElevatorController(car1);

            ElevatorCar car2 = new ElevatorCar(2);
            ElevatorController controller2 = new ElevatorController(car2);

            //Create one internal buttons for each elevator
            InternalButton internalButton_For_Elevator_One = new InternalButton(controller1);
            InternalButton internalButton_For_Elevator_Two = new InternalButton(controller2);

            //2. Create scheduler with Nearest Strategy
            ElevatorScheduler elevatorScheduler = new ElevatorScheduler(Arrays.asList(controller1, controller2), new NearestElevatorStrategy());

            //3. Create ExternalDispatcher
            ExternalDispatcher externalDispatcher = new ExternalDispatcher(elevatorScheduler);

            //4.Create a 5 floor building
            Building building = new Building(5, externalDispatcher);

            //5. Start both the elevator control threads
            new Thread(controller1, "Elevator-1").start();
            new Thread(controller2, "Elevator-2").start();

            building.getFloor(3).pressUpButton();
            Thread.sleep(5);

            building.getFloor(5).pressDownButton();
            Thread.sleep(5);

            internalButton_For_Elevator_One.pressButton(4);
            Thread.sleep(5);

            internalButton_For_Elevator_Two.pressButton(5);
            Thread.sleep(5);

            building.getFloor(1).pressDownButton();
            Thread.sleep(5);

            building.getFloor(2).pressUpButton();
            Thread.sleep(5);

            internalButton_For_Elevator_One.pressButton(2);
        } catch (Exception e) {

        }
    }
}
