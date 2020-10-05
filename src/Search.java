package task8.BootcampAdmin.src;

//import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * User can search for any project that exists in Project text file(Zoo.txt)
 *
 * @author Bonga Msomi
 *
 */
public class Search implements Poised {

    public void describe() throws SQLException {

        /*try {

            File file = new File("Zoo.txt");

            try {
                Scanner scanner = new Scanner(file);

                Scanner input = new Scanner(System.in);
                System.out.println("Enter project name/project number");
                String s = input.nextLine();

                //now read the file line by line...
                int lineNum = 0;
                boolean b = false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    lineNum++;
                    if (line.contains(s)) {
                        b = true;
                        System.out.println("found " + s + " on line " + lineNum);
                        System.out.println("Line " + lineNum + ": " + line);
                    }

                }
                if (!b) {//if search string is not found in text file
                    System.out.println("Project " + s + " does not exist in textfile");
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }*/
        final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
        final String DB_URL = "jdbc:derby://localhost:1527/Project1";
        //  Database credentials
        final String USER = "root";
        final String PASS = " ";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }

        //STEP 3: Open a connection
        System.out.println(
                "Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(
                "Connected database successfully...");

        //STEP 4: Execute a query
        System.out.println(
                "Creating statement...");
        stmt = conn.createStatement();
        Scanner scan = new Scanner(System.in);
        //search for project using project number
        String sql = "SELECT * FROM Project WHERE LOWER(project_num) LIKE LOWER('%" + scan + "%')";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            //Display values
            System.out.print(rs);

        } else {
            System.out.println("That project does not exist...");
        }

        rs.close();
    }
}
