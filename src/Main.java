import java.io.File;

public class Main {

    public static void main(String [] args) {
        if(args.length != 1){
            System.out.println("Invalid number of argument");
            System.out.println("You need to execute the program using: java Main file.txt");
        } else {
            String fileName = args[0];
            File file = new File(fileName);
            Problem problem = Parser.parseFile(file);
            int t = 3;
        }
    }

}
