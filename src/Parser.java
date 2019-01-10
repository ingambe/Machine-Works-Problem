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
    public static Problem parseFile(final File file){
        // TODO : more than one problem
        Problem problem = null;
        try {
            Scanner scanner = new Scanner(file);
            String firstLine = scanner.nextLine();
            String[] firstLineSplitted = firstLine.split("\\s+");
            int numberMachine = Integer.parseInt(firstLineSplitted[0]);
            int startDollar = Integer.parseInt(firstLineSplitted[1]);
            int days = Integer.parseInt(firstLineSplitted[2]);

            List<Machine> machines = new ArrayList<>();
            int i = numberMachine;
            while (i > 0 && scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] splittedLine = line.split("\\s+");
                int daySale = Integer.parseInt(splittedLine[0]);
                int priceBought = Integer.parseInt(splittedLine[1]);
                int priceResold = Integer.parseInt(splittedLine[2]);
                int dailyProfit = Integer.parseInt(splittedLine[3]);
                Machine machine = new Machine(daySale, priceBought, priceResold, dailyProfit, days);
                if(machine.couldBeProfitable()) {
                    machines.add(machine);
                }
                i--;
            }
            scanner.close();
            problem = new Problem(numberMachine, startDollar, days, machines);
            return problem;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return problem;
    }

}
