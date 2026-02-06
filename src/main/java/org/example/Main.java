package org.example;

import org.example.simulation.Simulator;
import org.example.model.Car;
import org.example.model.Direction;
import org.example.model.Grid;
import org.example.model.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<Car,String> carHolder = new HashMap<>();
        String welcomeText = """
                Welcome to Auto Driving Car Simulation!
                
                Please enter the width and height of the simulation field in x y format:
                """;
        System.out.println(welcomeText);

        int gridLength = sc.nextInt();
        int gridWidth = sc.nextInt();
        Grid grid = new Grid(gridLength,gridWidth);
        Simulator simulator = new Simulator(grid);
        System.out.printf("You have created a field of  %d x %d",gridLength,gridWidth);
        System.out.println();
        String options = """
                Please choose from the following options:
                [1] Add a car to field
                [2] Run simulation
                """;
        while(true){
            String command = sc.nextLine();
            if(command.equals("exit")){
                break;
            }
            System.out.println(options);
            switch (command){
                case "1":
                    System.out.println("Please enter the name of the car:");
                    String name = sc.nextLine();
                    System.out.println("Please enter initial position of car " + name + " in x y Direction format:");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    char direction = sc.next().charAt(0);
                    sc.nextLine();
                    Direction d = Direction.fromChar(direction);
                    Position p = new Position(x,y);
                    Car car = new Car(name, p ,d);
                    System.out.println("Please enter the commands for car" + name);
                    String commands = sc.nextLine();
                    carHolder.put(car,commands);
                    System.out.println("Your current list of cars are: ");
                    for(Map.Entry<Car,String> entry: carHolder.entrySet()){
                        Car c = entry.getKey();
                        System.out.printf("%s, (%d, %d) %s, %s", c.getName(),c.getPosition().getX(),c.getPosition().getY()
                        ,c.getDirection().name(), entry.getValue());
                        System.out.println();

                    }
                    break;
                case "2":
                    System.out.println("After simulation the result is: ");
                    simulator.simulate(carHolder);
                    break;
                default:
                    System.out.println("Please select either option 1 or 2");
            }
        }
    }
}