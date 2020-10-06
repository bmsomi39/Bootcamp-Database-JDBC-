
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer;
//import java.util.StringTokenizer;  don't have to use StringTokenizer

/**
 * This class allows the user to view all projects that still need to be
 * completed.
 *
 * @author Bonga Msomi
 *
 */
public class ViewComplete implements Poised {

    public void describe() {

        File file = new File("Zoo.txt");

        try {
            Scanner scanner = new Scanner(file);

            System.out.println("Projects that still need to be completed:");

            //Now read the file line by line
            int lineNum = 0;

            while (scanner.hasNextLine()) {
                lineNum++;
                String line = scanner.nextLine();
                // System.out.println(line.substring(line.lastIndexOf(",") + 1));
                String s = line.substring(line.lastIndexOf(",") + 1);

                if (s.contains("no")) {

                    System.out.println("Project number :" + lineNum);
                    System.out.println("Project information : " + line);
                }

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            //handle this
        }
    }
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
// show completed
    public static Project finalized(String pro_num) {
        Project pro = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Project WHERE Finalized = 'finalized'");
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
