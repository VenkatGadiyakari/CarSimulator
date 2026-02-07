package org.autonomous;

import org.autonomous.model.Car;
import org.autonomous.model.Direction;
import org.autonomous.model.Grid;
import org.autonomous.model.Position;
import org.autonomous.simulation.Simulator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SimulationApp {

    private final Scanner scanner = new Scanner(System.in);
    private Simulator simulator;
    private final Map<Car, String> carCommands = new LinkedHashMap<>();

    public void run(){
        showWelcome();
        initializeGrid();
        menuLoop();
    }


    private void showWelcome() {
        System.out.println("""
        Welcome to Auto Driving Car Simulation!

        Please enter the width and height of the simulation field in x y format:
        """);
    }

    private void initializeGrid() {
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();

        Grid grid = new Grid(height, width);
        simulator = new Simulator(grid);

        System.out.printf("You have created a field of %d x %d%n", width, height);
    }

    private void menuLoop() {
        String options = """
       Please choose from the following options:
       [1] Add a car to field
       [2] Run simulation
       Type 'exit' to quit
       """;

        while (true) {
            System.out.println(options);
            String choice = scanner.nextLine();

            if ("exit".equalsIgnoreCase(choice)) {
                break;
            }

            switch (choice) {
                case "1" -> addCar();
                case "2" -> runSimulation();
                default -> System.out.println("Please select either option 1 or 2");
            }
        }
    }

    private void addCar() {
        System.out.println("Please enter the name of the car:");
        String name = scanner.nextLine();

        System.out.println("Please enter initial position of car " + name + " in x y Direction format:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        char direction = scanner.next().charAt(0);
        scanner.nextLine();

        Direction cardDirection = Direction.fromChar(direction);
        Position carPostion = new Position(x, y);
        Car car = new Car(name, carPostion, cardDirection);

        System.out.println("Please enter the commands for car " + name + ":");
        String commands = scanner.nextLine();

        carCommands.put(car, commands);
        printCars();
    }

    private void runSimulation() {
        System.out.println("After simulation the result is:");
        simulator.simulate(carCommands);
    }

    private void printCars() {
        System.out.println("Your current list of cars are:");
        carCommands.forEach((car, commands) -> {
            System.out.printf(
                    "%s, (%d, %d) %s, %s%n",
                    car.getName(),
                    car.getPosition().getX(),
                    car.getPosition().getY(),
                    car.getDirection().name(),
                    commands
            );
        });
    }

}
