
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.text.*;
import java.util.Date;

/**
 * This class allows the user to view all projects that are past due.
 *
 * @author Bonga Msomi
 *
 */
public class ViewPastDue implements Poised {

    public static String past_due_projects = "";
    public static String curr_date = "";
    public static String due_date = "";

    public void describe() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        curr_date = dtf.format(now);

        File myObj = new File("C:\\Users\\Test\\Desktop\\work space\\BootcampAdmin\\Zoo.txt"); //Read input.txt file
        //3,Building,House,Curie,39,3400,Gauteng,1234,1000000.0,100000.0,20 Nov 2021,Finalized :20/08/2020

        String output = "";
        System.out.println("Past Due Projects:");
        System.out.println();

        try {//------------------TRY FOR INPUT
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                boolean bool_overdue = false;
                count++;
                String data = myReader.nextLine();

                String ch = ",";

                int date_start = nthOccurrence(data, ch, 10);
                int date_end = nthOccurrence(data, ch, 11);
                int len = data.length();

                due_date = data.substring(date_start + 1, date_end);

                //  System.out.println(due_date);
                SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
                Date d1 = null;
                try {
                    d1 = sdformat.parse(curr_date);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
                Date d2 = null;
                try {
                    d2 = sdformat.parse(due_date);
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                if (d1.compareTo(d2) > 0) {
                    System.out.println(data);
                }

            }

            myReader.close();//

        } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
            System.out.println("An error occured.");
            e.printStackTrace();

        }
        System.out.println();

    }//end of describe

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

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    // get past due project
     public static Project pastdue(String pro_num) {
        Project pro = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Project WHERE date >= currnetDate AND date <= dueDate");
            ps.setString(1, pro_num);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro = new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8));
            }

        } catch (SQLException e) {

        }
        return pro;
    }

}
