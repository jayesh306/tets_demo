package com.udemy.elevator;

import com.udemy.elevator.enums.ElevatorDirection;

import java.util.concurrent.PriorityBlockingQueue;

public class ElevatorController implements Runnable{
    PriorityBlockingQueue<Integer> upMinPQ;
    PriorityBlockingQueue<Integer> downMaxPQ;

    ElevatorCar elevatorCar;

    private final Object monitor = new Object();

    ElevatorController(ElevatorCar elevatorCar){
        this.elevatorCar=elevatorCar;
        upMinPQ = new PriorityBlockingQueue<>();
        downMaxPQ = new PriorityBlockingQueue<>(10,(a,b) -> b-a);
    }
    public void submitRequest(int destinationFloor){
        enqueueRequest(destinationFloor);
    }
    public void enqueueRequest(int destinationFloor){
        System.out.println("Request details -> destinationFloor: "+destinationFloor+" accepted by the elevator : "+elevatorCar.id);

        if(destinationFloor == elevatorCar.nextFloorStoppage){
            return;
        }
        if(destinationFloor >= elevatorCar.nextFloorStoppage){
            if(!upMinPQ.contains(destinationFloor)){
                upMinPQ.offer(destinationFloor);
            }
        }else{
            if(!downMaxPQ.contains(destinationFloor)){
                downMaxPQ.offer(destinationFloor);
            }
        }
        synchronized (monitor){
            monitor.notify();    //wake elevator thread
        }
    }

    @Override
    public void run(){
        controlElevator();
    }
    public void controlElevator(){
        while(true){

            //no request go to sleep
            synchronized(monitor){
                while(upMinPQ.isEmpty() && downMaxPQ.isEmpty()){
                    try{
                        System.out.println("Elevator "+elevatorCar.id+" id IDLE");
                        elevatorCar.movingDirection = ElevatorDirection.IDLE;
                        monitor.wait(); //sleep until request arrives
                    }catch(Exception e){
                        Thread.currentThread().interrupt();
                    }
                }
            }

            while(!upMinPQ.isEmpty()){
                int floor = upMinPQ.poll();
                System.out.println("Serving floor : "+floor+" by elevator "+ elevatorCar.id+" currentFloor : "+ elevatorCar.currentFloor);
                elevatorCar.moveElevator(floor);
            }
            while(!downMaxPQ.isEmpty()){
                int floor = downMaxPQ.poll();
                System.out.println("Serving floor : "+floor+" by elevatir");
                elevatorCar.moveElevator(floor);
            }
        }
    }

}
