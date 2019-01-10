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
            for(int i = 0; i < problems.size(); i++){
                System.out.println("Case " + (i + 1) + ": " + Solver.solve(problems.get(i)));
            }
        }
    }

}
