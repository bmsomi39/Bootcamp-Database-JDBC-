
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Finalize class: An invoice should be generated for the client. This invoice
 * should contain the customer’s contact details and the total amount that the
 * customer must still pay. This amount is calculated by subtracting the total
 * amount paid to date from the total fee for the project. If the customer has
 * already paid the full fee, an invoice should not be generated. The project
 * should be marked as “finalized” and the completion date should be added. All
 * the information about the project should be added to a text file called
 * “Completed project”
 *
 * @author Bonga Msomi
 *
 */
public class Finalize implements Poised {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public static int proj_num;
    public static int Total_cost;
    public static int Total_paid;
    public static String Complete_output = "";
    public static String Project_info;
    public static String Project_people = "";

    public static String Finalized_output = "";

    public static String Finalized_proj_info = "";

    public static double Outstanding;

    public void describe() {

        File file = new File("Zoo.txt");

        boolean finalised = false;
        String customer_contact = "";
        try {
            Scanner scanner = new Scanner(file);

            //First let user see list of projects before they choose which one to finalize
            int lineNum = 0;
            boolean b = false;
            System.out.println("Projects that still need to be completed:");
            System.out.println();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;

                b = true;
                String finalized = line.substring(line.lastIndexOf(",") + 1);

                if (finalized.contains("no")) {
                    System.out.println("Project " + lineNum + ": " + line);
                }
            }//while

            if (!b) {
                System.out.println("Project " + lineNum + " does not exist in textfile");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }
        //get from the table the incomplete data
        try {
            ps = con.prepareStatement("select * from Project where finalized = incomplete ");
            ps.setString(1, String.valueOf(finalised));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Finalize.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Once the user has chosen which project to finalize:
        //Collect customer details from text file
        file = new File("Person.txt");
        try {//Try Person.txt
            Scanner scanner = new Scanner(file);

            int lineNum = 0;
            boolean b = false;

            Scanner input = new Scanner(System.in);

            System.out.println();
            System.out.println("Enter project number you would like to finalize:");
            proj_num = input.nextInt();

            System.out.println(proj_num);
            String str_proj_num = String.valueOf(proj_num);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;

                b = true;
                String s = line.substring(0, 2);

                if (s.contains(str_proj_num) && (line.contains("Contractor") || line.contains("Architect") || line.contains("Customer"))) {
                    Project_people += line + "\n";

                }

                if (s.contains(str_proj_num) && line.contains("Customer")) {

                    String ch = ",";
                    int n = 5;
                    int m = 6;
                    int index_start = nthOccurrence(line, ch, 4);
                    int index_end = nthOccurrence(line, ch, 5);

                    customer_contact = line.substring(index_start + 1, index_end);

                }

            }

        } //Catch Person.txt for customer details
        catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }

//----------------------------Outstanding amount
        file = new File("Zoo.txt");
        String str_proj_num = String.valueOf(proj_num);

        try {//Try Zoo.txt Outstanding amount
            Scanner scanner = new Scanner(file);

            int lineNum = 0;
            boolean b = false;

            Scanner input = new Scanner(System.in);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                lineNum++;
                String s = line.substring(0, 2);

                if (s.contains(str_proj_num)) {

                    Project_info = line;
                    String ch = ",";
                    int n = 5;
                    int m = 6;
                    int Total_cost_start = nthOccurrence(line, ch, 8);
                    int Total_cost_end = nthOccurrence(line, ch, 9);

                    int Total_paid_start = nthOccurrence(line, ch, 9);
                    int Total_paid_end = nthOccurrence(line, ch, 10);

                    String Totalcost = line.substring(Total_cost_start + 1, Total_cost_end);
                    String Totalpaid = line.substring(Total_paid_start + 1, Total_paid_end);

                    String number = "6.24";
                    double TC = Double.parseDouble(Totalcost);

                    double TD = Double.valueOf(Totalpaid);

                    Outstanding = TC - TD;//Total fee/cost of project - Total fee paid to date

                }

            }//while scanning

        } //Catch Zoo.txt Outstanding amount
        catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }

//---------------------------End of Outstanding amount block	
//---------------------------	MARK PROJECT AS FINALISED("finalized")		
        //INPUT
        File myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Zoo.txt"); //Read input.txt file

        String output = "";

        try {//------------------TRY FOR INPUT
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                count++;
                String data = myReader.nextLine();

                if (proj_num == count) {//The following only occurs for the project number the user has entered

                    String ch = ",";

                    int end_pos = nthOccurrence(data, ch, 11);

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime now = LocalDateTime.now();

                    Finalized_output += data.substring(0, end_pos + 1) + "Finalized :" + dtf.format(now) + "\n";  	 //COMPLETION DATE ADDED      

                    Finalized_proj_info = "";
                    Finalized_proj_info = data.substring(0, end_pos + 1) + "Finalized :" + dtf.format(now) + "\n";
                } else {
                    Finalized_output += data + "\n";
                }
            }
            myReader.close();//

        } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
            System.out.println("An error occured.");
            e.printStackTrace();

        }

        //OUTPUT
        try {//try for output to Project.txt

            FileWriter myWriter = new FileWriter("Zoo.txt");
            myWriter.write(Finalized_output);
            myWriter.close();
            //System.out.println("Successfully wrote to file :)");

        } catch (IOException e) {//catch for output to output.txt
            System.out.println("An error ocurred");
            e.printStackTrace();

        }

//--------------------------- END OF MARK PROJECT AS FINALISED("finalized")
//---------ADD ALL INFORMATION ABOUT PROJECT TO A TEXT FILE CALLED “Completed project”.	
        //INPUT
        myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Completed Projects.txt"); //Read input.txt file

        output = "";

        try {//------------------TRY FOR INPUT
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Complete_output += data + "\n";

            }
            myReader.close();//

        } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
            System.out.println("An error occured.");
            e.printStackTrace();

        }

        //Output
        Complete_output += Finalized_proj_info;
        Complete_output += Project_people;

        try {//so FileWriter creates and can write to a file 
            FileWriter writer = new FileWriter("Completed Projects.txt");

            writer.append(Complete_output);

            writer.close();
        } catch (Exception e) {
            System.out.println("File could not be found");
        }
    }

    public static Project finalized(String pro_num) {
        Project pro = null;
        try {
            ps = con.prepareStatement("select * from Project where finalized =finalized AND Amoutpaid = amaountdue ");
            ps.setString(1, pro_num);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro = new Project(Project_info, Project_info, Project_info, Project_people, Project_info, Outstanding, Outstanding, Project_people);
            }
            System.out.println();
            System.out.println("INVOICE");
            System.out.println("Customer Details");
            System.out.println("Contact details: " + rs);

            System.out.println("Outstanding amount customer should still pay: R" + Outstanding);

        } catch (SQLException e) {

        }
        return pro;
    }

//---------END OF ADD ALL INFORMATION ABOUT PROJECT TO A TEXT FILE CALLED “Completed project”.			
//---------------------------Display
    //End of Describe block
    /**
     *
     * @param str1 : full string
     * @param str2 : we are looking for the nth occurrence of str2
     * @param n : the index of the occurrence of str2 we are looking for
     * @return integer that contains the index where the nth occurrence of str2
     * is currently located
     */
    public static int nthOccurrence(String str1, String str2, int n) {

        String tempStr = str1;
        int tempIndex = -1;
        int finalIndex = 0;
        for (int occurrence = 0; occurrence < n; ++occurrence) {
            tempIndex = tempStr.indexOf(str2);
            if (tempIndex == -1) {
                finalIndex = 0;
                break;
            }
            tempStr = tempStr.substring(++tempIndex);
            finalIndex += tempIndex;
        }
        return --finalIndex;
    }//end nthOccurrence

}
