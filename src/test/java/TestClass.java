import org.example.model.Car;
import org.example.model.Direction;
import org.example.model.Grid;
import org.example.model.Position;
import org.example.simulation.Simulator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class TestClass {

    public static void main(String[] args) {
        Grid g = new Grid(10,10);
        Position car1Position = new Position(1,2);
        Direction car1Direction = Direction.N;
        Car c1 = new Car("A",car1Position,car1Direction);
        Simulator s = new Simulator(g);
        String car1Command = "FFRFFFFRRL";
        simulateOneCar(s,c1,car1Command);

    }

    private static void simulateOneCar(Simulator s, Car c,String command){
        Map<Car, String> mp = new HashMap<>();
        mp.put(c, command);

        PrintStream originalOut = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        s.simulate(mp);

        System.setOut(originalOut);

        String output = out.toString().trim();
        System.out.println("Captured output:");
        System.out.println(output);
        if(output.equals("A, (3, 6) S")){
            System.out.println("Test case passed");
        }else{
            throw new AssertionError("Test case failed");
        }


    }
}
