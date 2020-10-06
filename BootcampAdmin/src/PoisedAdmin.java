
import java.util.*;

/**
 * Main class Handles main menu User can choose to either: 1-Capture 2-Update
 * 3-Finalize 4-View Completed Projects 5-View Past Due Projects 6-Search or
 * "quit"
 *saves on Database and textfile
 * added from previous project
 * @author Bonga Msomi
 *
 */
public class PoisedAdmin {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean resume = true;

        while (resume) {

            System.out.println("Enter number(1,2,3,4,5,6) or \"quit\".");
            System.out.println("1-Capture");
            System.out.println("2-Update");
            System.out.println("3-Finalize");
            System.out.println("4-View Completed Projects");
            System.out.println("5-View Past Due Projects ");
            System.out.println("6-Search");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("quit")) {

                resume = false;
                in.close();
                break;

            }
            //I use Object Oriented Programming(Factory Design Pattern)
            //A Very convenient structure    
            PoisedFactory bootFactory = new PoisedFactory();
            Poised bootcamp = bootFactory.createBootcamp(line);
            bootcamp.describe();
        }
    }
}
