package pl.edu.agh.ztis.planner;

import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.Arrays;

public class PlanningExecutor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = Paths.get(args[0]).toFile();
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        PlanningTask job = (PlanningTask) stream.readObject();

        System.out.println("args = " + Arrays.toString(args));
        System.out.println("args.length = " + args.length);

    }
}
