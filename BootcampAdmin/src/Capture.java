package task8.BootcampAdmin.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author Bonga Msomi
 *
 */
public class Capture implements Poised {

    /**
     * Describe method in Capture class does the following: Capture all the
     * information a new projects details consist of. Capture all the
     * information about the projects Architect, Contractor & Customer. Input
     * current contents of Zoo.txt before entering a new project Store the
     * Project details in the Project tect file called ZOO.txt Store the
     * Architect, Contractor & Customer details for each project.
     */
    private Connection conn;
//store data from database
    private ResultSet rs;
    private PreparedStatement ps;
    private Statement st;

    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://localhost/project_db";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    public void describe() {

        ArrayList arProject = new ArrayList(); //Store projects
        ArrayList arPerson = new ArrayList(); //Store Architect, Contractor & Customer details
        Scanner sc = new Scanner(System.in);
        Customer customer;
        String surname, email;

        String choice;
        choice = "";
        String street_name;
        String house_no;
        String postal_code;
        String province;
        System.out.println("\n-------------------");
        System.out.println("Welcome to Poised ");
        System.out.println("-------------------\n");
        System.out.println("1. Register Project.");

        choice = sc.nextLine();

        if (choice.equals("1")) { //Capture all project details including Architect, Contractor & Customer details

            Project project = new Project();

            System.out.print("Enter project number : ");
            String project_num = sc.nextLine();
            project.getProject_number();
            arProject.add(project_num);
            System.out.println("Enter project name : ");
            String project_name = sc.nextLine();
            arProject.add(project_name);
            //  surname = sc.nextLine();
            System.out.print("Enter Building type(Apartment,Store or House) : ");
            String building_ = sc.nextLine();
            //building_ = sc.nextLine();
            arProject.add(building_);
            System.out.print("Enter address\n");
            System.out.print("Enter street name : ");
            street_name = sc.nextLine();
            arProject.add(street_name);
            System.out.print("Enter house number : ");
            house_no = sc.nextLine();
            arProject.add(house_no);
            System.out.print("Enter postal code : ");
            postal_code = sc.nextLine();
            arProject.add(postal_code);
            System.out.print("Enter province : ");
            province = sc.nextLine();
            arProject.add(province);
            System.out.println("Enter ERF number :");
            String ERF = sc.next();
            sc.nextLine();
            arProject.add(ERF);
            System.out.print("Total_fee_charged : ");
            Double total_fee_charged = sc.nextDouble();
            // sc.nextLine();
            arProject.add(total_fee_charged);
            System.out.print("Enter amount paid to date : ");
            Double amt_paid_to_date = sc.nextDouble();
            //sc.nextDouble();
            arProject.add(amt_paid_to_date);
            System.out.print("Enter project due date (MM-DD-YYYY)): ");
            String project_due_date = sc.nextLine();
            project_due_date = sc.nextLine();
            arProject.add(project_due_date);
            System.out.print("Is project finalised? Enter 'no' for now until project finalised : ");
            String finalised = sc.nextLine();
            arProject.add(finalised);
            //  sc.nextLine();
            project = new Project(project_name, project_name, building_, project_due_date, ERF, total_fee_charged, amt_paid_to_date, project_due_date, finalised);
            System.out.println("Project :" + "\nProject number :" + project_num + "\nProject Name :" + project_name + "\nBuilding Type :" + building_ + "\nERF :" + ERF + "\nAddress :\n" + street_name + "\n" + house_no + "\n" + postal_code + "\n" + province + "\nDue date :"
                    + project_due_date + "\nTotal fee charged :" + total_fee_charged + "\namount paid :" + amt_paid_to_date + "\nfinalised :" + finalised);

            String file = "C:/Users/Test/Desktop/work space/BootcampAdmin/";
            String str = "";

            //First take current contents of Text File
            File myObj = new File("C:/Users/Test/Desktop/work space/BootcampAdmin/Zoo.txt"); //Read input.txt file

            String Output = "";
            try {//------------------TRY FOR INPUT-------------------------------
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    Output += data + "\n";  //Add each line previosuly entered to Project text file to string Output

                }
                myReader.close();//

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();

            }

            //Then add new project to Project text file-------------------------------------------
            try {
                FileWriter writer = new FileWriter(file + "Zoo.txt");
                int size = arProject.size();
                for (int i = 0; i < size; i++) {
                    str += arProject.get(i).toString() + ",";

                }

                writer.write(Output);
                int len = str.length();
                str = str.substring(0, len - 1);
                writer.append(str);

                writer.close();
            } catch (Exception e) {
                System.out.println("File could not be found");
            }
            //Add data to database
            try {
                ps = conn.prepareStatement("insert into tblProject values(?,?,?,?,?,?,?,?,?)");
                ps.setString(1, project.getProject_number());
                ps.setString(2, project.getProject_name());
                ps.setString(3, project.getBuilding_type());
                ps.setString(4, project.getProject_phys_address());
                ps.setString(5, project.getERF_number());
                ps.setDouble(6, project.getTotal_fee_charged());
                ps.setString(7, project.getTotal_paid_to_date());
                ps.setString(8, project.getProject_deadline());
                ps.setString(9, project.getFinalised());
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //------------------------------------End of Project capture

//-------------------------------ASSUMING EACH PROJECT HAS A CUSTOMER ,ARCHITECT AND CONTRACTOR
            //
            // 
            //  
            //  
            //  
            //  
            //Architect capture  //
            System.out.print("Enter Architect Details ");
            //add project ID
            arPerson.add(project_num);

            String person_type = "Architect";
            arPerson.add(person_type);

            System.out.print("Enter name : ");
            String name = sc.nextLine();
            arPerson.add(name);

            System.out.println("Enter surname : ");
            surname = sc.nextLine();
            arPerson.add(surname);
            System.out.print("Enter contact number (10 digits): "); //Input here should always be digits
            String phone = sc.nextLine();
            arPerson.add(phone);

            System.out.print("Enter address\n");
            System.out.print("Enter street name : ");
            street_name = sc.nextLine();
            arPerson.add(street_name);
            System.out.print("Enter house number : ");
            house_no = sc.nextLine();
            arPerson.add(house_no);
            System.out.print("Enter postal code : ");
            postal_code = sc.nextLine();
            arPerson.add(postal_code);
            System.out.print("Enter province : ");
            province = sc.nextLine();
            arPerson.add(province);
            System.out.println("Enter email should contain special character @ :");
            email = sc.nextLine();
            arPerson.add(email);

            customer = new Customer(surname, person_type, name, street_name, house_no, postal_code, province, phone);
            System.out.println("Type :  \n" + person_type + "\nSurname :" + surname + "\nName :" + name + "\nEmail :" + email + "\nPhone :" + phone + "\nAddress :\n" + street_name + "\n" + house_no + "\n" + postal_code + "\n" + province);

            System.out.println(customer);

//--------------------------------------------------------------------WRITE TO PERSON	            
            //First take current contents of Text File
            File myObject = new File("C:/Users/Test/Desktop/work space/BootcampAdmin/Person.txt"); //Read input.txt file

            String Output1 = "";
            try {//------------------TRY FOR INPUT-------------------------------
                Scanner myReader = new Scanner(myObject);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    Output1 += data + "\n";  //Add each line to string "list"

                }
                myReader.close();//

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();

            }
            str = "";

            //Then add new project-------------------------------------------
            try {
                FileWriter writer = new FileWriter(file + "Person.txt");
                int size = arPerson.size();
                for (int i = 0; i < size; i++) {
                    str += arPerson.get(i).toString() + ",";

                }

                writer.write(Output1);
                int len = str.length();
                str = str.substring(0, len - 1);
                writer.append(str);

                writer.close();
            } catch (Exception e) {
                System.out.println("File could not be found");
            }
            //------------------------------------------------------------------------
            arPerson.clear();

            try {
                ps = conn.prepareStatement("insert into tblPreson values(?,?,?,?,?,?,?,?)");
                ps.setString(1, customer.getPerson_type());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getName());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getStreet_name());
                ps.setDouble(6, customer.getHouse_no());
                ps.setString(7, customer.getPostal_code());
                ps.setString(8, customer.getProvince());
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
//----------------------------------------------------------------------------------------
            //

            // 
            //  
            //  
            //  
            //  
            //Contractor capture  //
            System.out.print("Enter Contractor Details ");
            //add project ID
            arPerson.add(project_num);
            person_type = "Contractor";
            arPerson.add(person_type);
            System.out.print("Enter name : ");
            name = sc.nextLine();
            arPerson.add(name);
            System.out.println("Enter surname : ");
            surname = sc.nextLine();
            arPerson.add(surname);
            System.out.print("Enter contact number (10 digits) : ");
            phone = sc.nextLine();
            arPerson.add(phone);
            System.out.print("Enter address\n");
            System.out.print("Enter street name : ");
            street_name = sc.nextLine();
            arPerson.add(street_name);
            System.out.print("Enter house number : ");
            house_no = sc.nextLine();
            arPerson.add(house_no);
            System.out.print("Enter postal code : ");
            postal_code = sc.nextLine();
            arPerson.add(postal_code);
            System.out.print("Enter province : ");
            province = sc.nextLine();
            arPerson.add(province);
            System.out.println("Enter email should contain special character @ :");
            email = sc.nextLine();
            //sc.nextLine();
            arPerson.add(email);
            //  sc.nextLine();
            customer = new Customer(surname, person_type, name, street_name, house_no, postal_code, province, phone);
            System.out.println("Type :  \n" + person_type + "\nSurname :" + surname + "\nName :" + name + "\nEmail :" + email + "\nPhone :" + phone + "\nAddress :\n" + street_name + "\n" + house_no + "\n" + postal_code + "\n" + province);

            System.out.println(customer);

//--------------------------------------------------------------------WRITE TO PERSON	            
            //First take current contents of Text File
            File myObject2 = new File("C:/Users/Test/Desktop/work space/BootcampAdmin/Person.txt"); //Read input.txt file
//C:\Users\Test\Desktop\work space\BootcampAdmin\Person.txt

            String Output2 = "";
            try {//------------------TRY FOR INPUT-------------------------------
                Scanner myReader = new Scanner(myObject2);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    Output2 += data + "\n";  //Add each line to string "list"

                }
                myReader.close();//

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();

            }
            str = "";

            //Then add new project-------------------------------------------
            try {
                FileWriter writer = new FileWriter(file + "Person.txt");
                int size = arPerson.size();
                for (int i = 0; i < size; i++) {
                    str += arPerson.get(i).toString() + ",";

                }

                writer.write(Output2);
                int len = str.length();
                str = str.substring(0, len - 1);
                writer.append(str);

                writer.close();
            } catch (Exception e) {
                System.out.println("File could not be found");
            }
            //------------------------------------------------------------------------
            arPerson.clear();
  //Add data to database
            try {
                ps = conn.prepareStatement("insert into tblPreson values(?,?,?,?,?,?,?,?)");
                ps.setString(1, customer.getPerson_type());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getName());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getStreet_name());
                ps.setDouble(6, customer.getHouse_no());
                ps.setString(7, customer.getPostal_code());
                ps.setString(8, customer.getProvince());
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
//----------------------------------------------------------------------------------------
            //------------------------------------------------------------------------
            //----------------------------------------------------------------------------------------
            //

            // 
            //  
            //  
            //  
            //  
            //Customer capture  //
            System.out.print("Enter Customer Details ");
            arPerson.add(project_num);
            person_type = "Customer";
            arPerson.add(person_type);
            System.out.print("Enter name : ");
            name = sc.nextLine();
            arPerson.add(name);
            System.out.println("Enter surname : ");
            surname = sc.nextLine();
            arPerson.add(surname);
            System.out.print("Enter contact number(10 digits) : ");
            phone = sc.nextLine();
            arPerson.add(phone);
            System.out.print("Enter address\n");
            System.out.print("Enter street name : ");
            street_name = sc.nextLine();
            arPerson.add(street_name);
            System.out.print("Enter house number : ");
            house_no = sc.nextLine();
            arPerson.add(house_no);
            System.out.print("Enter postal code : ");
            postal_code = sc.nextLine();
            arPerson.add(postal_code);
            System.out.print("Enter province : ");
            province = sc.nextLine();
            arPerson.add(province);
            System.out.println("Enter email should contain special character @ :");
            email = sc.nextLine();
            //sc.nextLine();
            arPerson.add(email);
            //  sc.nextLine();
            System.out.println("Project stored.");
            //sc.close();

            customer = new Customer(surname, person_type, name, street_name, house_no, postal_code, province, phone);
            System.out.println("Type :  \n" + person_type + "\nSurname :" + surname + "\nName :" + name + "\nEmail :" + email + "\nPhone :" + phone + "\nAddress :\n" + street_name + "\n" + house_no + "\n" + postal_code + "\n" + province);

            System.out.println(customer);

            //--------------------------------------------------------------------WRITE TO PERSON	            
            //First take current contents of Text File
            File myObject3 = new File("C:/Users/Test/Desktop/work space/BootcampAdmin/Person.txt"); //Read input.txt file

            String Output13 = "";
            try {//------------------TRY FOR INPUT-------------------------------
                Scanner myReader = new Scanner(myObject3);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    Output13 += data + "\n";  //Add each line to string "Output"

                }
                myReader.close();//

            } catch (FileNotFoundException e) { //-------CATCH FOR INPUT
                System.out.println("An error occured.");
                e.printStackTrace();

            }
            str = "";

            //Then add new project-------------------------------------------
            try {
                FileWriter writer = new FileWriter(file + "Person.txt");
                int size = arPerson.size();
                for (int i = 0; i < size; i++) {
                    str += arPerson.get(i).toString() + ",";

                }
                arPerson.clear();

                writer.write(Output13);
                int len = str.length();
                str = str.substring(0, len - 1);
                writer.append(str);

                writer.close();
            } catch (Exception e) {
                System.out.println("File could not be found");
            }
              //Add data to database
            try {
                ps = conn.prepareStatement("insert into tblPreson values(?,?,?,?,?,?,?,?)");
                ps.setString(1, customer.getPerson_type());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getName());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getStreet_name());
                ps.setDouble(6, customer.getHouse_no());
                ps.setString(7, customer.getPostal_code());
                ps.setString(8, customer.getProvince());
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //------------------------------------------------------------------------

//--------------------------------------------------End of Project capture
        }
    }
}
