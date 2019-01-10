import java.io.File;
import java.util.List;

public class Main {

    public static void main(String [] args) {
        if(args.length != 1){
            System.out.println("Invalid number of argument");
            System.out.println("You need to execute the program using: java Main file.txt");
        } else {
            String fileName = args[0];
            File file = new File(fileName);
            List<Problem> problems = Parser.parseFile(file);
            long start = System.nanoTime();
            for(int i = 0; i < problems.size(); i++){
                Solver.complexSolver(problems.get(i));
                //System.out.println("Case " + (i + 1) + ": " + Solver.complexSolver(problems.get(i)));
            }
            long stop = System.nanoTime();
            System.out.println("complexe : " + (stop - start));
            start = System.nanoTime();
            for(int i = 0; i < problems.size(); i++){
                Solver.solve(problems.get(i));
                //System.out.println("Case " + (i + 1) + ": " + Solver.complexSolver(problems.get(i)));
            }
            stop = System.nanoTime();
            System.out.println("simple : " + (stop - start));
        }
    }

}
