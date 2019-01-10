import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private Parser(){}

    /**
     * Parse the input file and generate an problem object
     * @param file the file representing the problem
     * @return the Problem object representing the problem
     */
    public static List<Problem> parseFile(final File file){
        List<Problem> problems = new ArrayList<>();
        Problem problem = null;
        try {
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();

            String[] firstLineSplitted = firstLine.split("\\s+");
            int numberMachine = Integer.parseInt(firstLineSplitted[0]);
            int startDollar = Integer.parseInt(firstLineSplitted[1]);
            int days = Integer.parseInt(firstLineSplitted[2]);

            // while we haven't meet the last line with 0 0 0
            while(numberMachine != 0 || startDollar != 0 || days != 0) {
                List<Machine> machines = new ArrayList<>();
                int i = numberMachine;
                while (i > 0 && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] splittedLine = line.split("\\s+");
                    int daySale = Integer.parseInt(splittedLine[0]);
                    int priceBought = Integer.parseInt(splittedLine[1]);
                    int priceResold = Integer.parseInt(splittedLine[2]);
                    int dailyProfit = Integer.parseInt(splittedLine[3]);
                    Machine machine = new Machine(daySale, priceBought, priceResold, dailyProfit, days);
                    // we don't add machine that can't be profitable during the period of restructuring
                    if (machine.couldBeProfitable()) {
                        machines.add(machine);
                    }
                    i--;
                }
                problem = new Problem(numberMachine, startDollar, days, machines);
                problems.add(problem);

                // next problem's line
                String nextProblemLine = scanner.nextLine();
                String[] nextProblemLineSplitted = nextProblemLine.split("\\s+");
                numberMachine = Integer.parseInt(nextProblemLineSplitted[0]);
                startDollar = Integer.parseInt(nextProblemLineSplitted[1]);
                days = Integer.parseInt(nextProblemLineSplitted[2]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return problems;
    }

}
