
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * User can update the following: -A project's due date -Total fee paid to date
 * -The Contractors contact details
 *
 * @author Test
 *
 */
public class Update implements Poised {
   private Connection conn;
//store data from database
    private ResultSet rs;
    private PreparedStatement ps;
    private Statement st;

    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://localhost/halse_db";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    public static int Proj_num;
    public static int int_option;
    public static String new_info;
    public static String updated_due_date = "";
    public static String updated_fee_paid = "";
    public static String updated_customer_contact = "";

    public void describe() {

        File file = new File("Zoo.txt");

        try {
            Scanner scanner = new Scanner(file);

            int lineNum = 0;
            boolean b = false;
            System.out.println("List of all projects that can be updated:");
            System.out.println();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                b = true;
                System.out.println("Project " + lineNum + ": " + line);

            }//while

            if (!b) {
                System.out.println("Project " + lineNum + " does not exist in textfile");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the project number that you would like to update:");

        String spn = input.nextLine();
        Proj_num = Integer.parseInt(spn);
        //Choose what to update
        System.out.println();
        System.out.println("Select what number option you would like to update from the list below: ");
        System.out.println("1 	-	Update Due Date.");
        System.out.println("2	-	Update Total Fee Paid .");
        System.out.println("3	-	Update Contractors Contact Details.");
        String io = input.nextLine();
        int_option = Integer.parseInt(io);

        if (int_option == 1) {
            System.out.println();
            System.out.println("Enter new due date: ");
            new_info = input.nextLine();
            System.out.println();
            System.out.println("Project" + Proj_num + "Option:" + int_option + "New info" + new_info);
  //Update data to database
            String qry = "UPDATE `tblProject` SET `Project_deadline`=[?] WHERE `project_num`=[?] ";

            try {
                ps = conn.prepareStatement(qry);
                ps.setInt(1, Proj_num);
                
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Unknown query" + e.getMessage());
            }
            //////////////////////////////////////////////////////////////////////////////////

            //---------------------------	UPDATE DUE DATE		
            //INPUT
            File myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Zoo.txt"); //Read input.txt file
            //3,Building,House,Curie,39,3400,Gauteng,1234,1000000.0,100000.0,20 Nov 2021,Finalized :20/08/2020

            String output = "";

            try {//------------------TRY FOR INPUT
                Scanner myReader = new Scanner(myObj);
                int count = 0;
                while (myReader.hasNextLine()) {
                    count++;
                    String data = myReader.nextLine();

                    if (Proj_num == count) {

                        String ch = ",";

                        int date_start = nthOccurrence(data, ch, 10);
                        int date_end = nthOccurrence(data, ch, 11);
                        int len = data.length();

                        updated_due_date += data.substring(0, date_start);
                        updated_due_date += "," + new_info;
                        updated_due_date += data.substring(date_end) + "\n";

                    } else {
                        updated_due_date += data + "\n";
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
                myWriter.write(updated_due_date);
                myWriter.close();
                //System.out.println("Successfully wrote to file :)");

            } catch (IOException e) {//catch for output to output.txt
                System.out.println("An error ocurred");
                e.printStackTrace();

            }

            //--------------------------- END OF MARK PROJECT AS FINALISED("finalized")
        }

///------------------------------------------------------------------		 
        if (int_option == 2) {
            System.out.println();
            System.out.println("Enter total amount fee paid : ");
            new_info = input.nextLine();
            System.out.println();

//////////////////////////////////////////////////////////////////////////////////
//---------------------------	UPDATE TOTAL 		
//INPUT
            File myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Zoo.txt"); //Read input.txt file
            //3,Building,House,Curie,39,3400,Gauteng,1234,1000000.0,100000.0,20 Nov 2021,Finalized :20/08/2020

            String output = "";

            try {//------------------TRY FOR INPUT
                Scanner myReader = new Scanner(myObj);
                int count = 0;
                while (myReader.hasNextLine()) {
                    count++;
                    String data = myReader.nextLine();

                    if (Proj_num == count) {

                        String ch = ",";

                        int date_start = nthOccurrence(data, ch, 9);
                        int date_end = nthOccurrence(data, ch, 10);
                        int len = data.length();

                        updated_fee_paid += data.substring(0, date_start);
                        updated_fee_paid += "," + new_info;
                        updated_fee_paid += data.substring(date_end) + "\n";

                    } else {
                        updated_fee_paid += data + "\n";
                    }
                }
                myReader.close();//

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();

            }
 //Update data to database
            String qry = "UPDATE `tblProject` SET `fee_paid`=[?] WHERE `project_num`=[?] ";

            try {
                ps = conn.prepareStatement(qry);
                ps.setInt(1, Proj_num);
                
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Unknown query" + e.getMessage());
            }

            //OUTPUT
            try {//try for output to Project.txt

                FileWriter myWriter = new FileWriter("Zoo.txt");
                myWriter.write(updated_fee_paid);
                myWriter.close();
                //System.out.println("Successfully wrote to file :)");

            } catch (IOException e) {//catch for output to output.txt
                System.out.println("An error ocurred");
                e.printStackTrace();

            }

        }

///------------------------------------------------------------------			 
        if (int_option == 3) { //UPDATE CONTRACTOR CONTACT DETAILS
            System.out.println();
            System.out.println("Enter new information you would like to add: ");
            new_info = input.nextLine();
            System.out.println();
            System.out.println("Project" + Proj_num + "Option:" + int_option + "New info" + new_info);

//////////////////////////////////////////////////////////////////////////////////
//---------------------------	UPDATE CONTRACTOR CONTACT DETAILS		
//INPUT
            File myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Person.txt"); //Read input.txt file

            try {//------------------TRY FOR INPUT
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = "";
                    data = myReader.nextLine();

                    String projnum = data.substring(0, 2);

                    String Proj_num_string = "";
                    Proj_num_string += Proj_num;

                    if (projnum.contains(Proj_num_string) && data.contains("Contractor")) {

                        String ch = ",";

                        int date_start = nthOccurrence(data, ch, 4);
                        int date_end = nthOccurrence(data, ch, 5);
                        int len = data.length();

                        updated_customer_contact += data.substring(0, date_start);
                        updated_customer_contact += "," + new_info;
                        updated_customer_contact += data.substring(date_end) + "\n";

                        //System.out.println(data);
                    } else {
                        //System.out.println(data);
                        updated_customer_contact += data + "\n";

                    }

                }//end while loop

                System.out.println(updated_customer_contact);

                myReader.close();

                 //Update data to database
            String qry = "UPDATE `tblProject` SET `number`=[?] WHERE `project_num`=[?] and person_type = contractor";

            try {
                ps = conn.prepareStatement(qry);
                ps.setInt(1, Proj_num);
                
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Unknown query" + e.getMessage());
            }

                //Output
                try {//try for output to output.txt

                    FileWriter myWriter = new FileWriter("Person.txt");
                    myWriter.write(updated_customer_contact);
                    myWriter.close();
                    //System.out.println("Successfully wrote to file :)");

                } catch (IOException e) {//catch for output to output.txt
                    System.out.println("An error ocurred");
                    e.printStackTrace();

                }

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();
            }
            int count = 0;

        }//END UPDATE CONTRACTOR CONTACT DETAILS	 

    }

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
