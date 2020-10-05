
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Facilitates creation/capture of each project
 * 
 * @author Bonga
 *
 */
public class Project {

    String Project_number;
    String Project_name;
    String Building_type;

    public Project() {
    }//constructor

    //Input
    /**
     * 
     * @param Project_number 
     * @param Project_name
     * @param Building_type :Could either be an Apartment/Store/House
     * @param Project_phys_address
     * @param ERF_number
     * @param Total_fee_charged
     * @param Total_paid_to_date
     * @param Project_deadline
     */
    public Project(String Project_number, String Project_name, String Building_type, String Project_phys_address, String ERF_number, Double Total_fee_charged, Double Total_paid_to_date, String Project_deadline) {
        setProject_number(Project_number);
        setProject_name(Project_name);
        setBuilding_type(Building_type);
        setProject_phys_address(Project_phys_address);
        setERF_number(ERF_number);
        setTotal_fee_charged(Total_fee_charged);
        setTotal_paid_to_date(Total_paid_to_date);
        setProject_deadline(Project_deadline);
    }

    public String getProject_number() {
        return Project_number;
    }

    public String getProject_name() {
        return Project_name;
    }

    public String getBuilding_type() {
        return Building_type;
    }

    public String getProject_phys_address() {
        return Project_phys_address;
    }

    public String getERF_number() {
        return ERF_number;
    }

    public Double getTotal_fee_charged() {
        return Total_fee_charged;
    }

    public Double getTotal_paid_to_date() {
        return Total_paid_to_date;
    }

    public String getProject_deadline() {
        return Project_deadline;
    }

    public String getFinalised() {
        return Finalised;
    }

    public void setProject_number(String Project_number) {
        this.Project_number = Project_number;
    }

    public void setProject_name(String Project_name) {
        Customer cs = new Customer();
        if (Project_name.isEmpty()) {
            Project_name = cs.getSurname();
        }
        this.Project_name = Project_name;
    }

    public void setBuilding_type(String Building_type) {
        if (!Building_type.equalsIgnoreCase("Apartment" + "House" + "Store")) {
            throw new IllegalArgumentException("Please choose the right building type!");
        }
        this.Building_type = Building_type;
    }

    public void setProject_phys_address(String Project_phys_address) {
        this.Project_phys_address = Project_phys_address;
    }

    public void setERF_number(String ERF_number) {
        this.ERF_number = ERF_number;
    }

    public void setTotal_fee_charged(Double Total_fee_charged) {
        this.Total_fee_charged = Total_fee_charged;
    }

    public void setTotal_paid_to_date(Double Total_paid_to_date) {
        this.Total_paid_to_date = Total_paid_to_date;
    }

    public boolean setProject_deadline(String Project_deadline) {

        if (Project_deadline.trim().equals("")) {
            return true;
        } /* Date is not 'null' */ else {
            /*
	     * Set preferred date format,
	     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
	     * parse the string into date 
             */
            try {
                Date javaDate = sdfrmt.parse(Project_deadline);
                System.out.println(Project_deadline + " is valid date format");
            } /* Date format is invalid */ catch (ParseException e) {
                System.out.println(Project_deadline + " is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;

        }

    }

    public void setFinalised(String Finalised) {
        if (!Finalised.equalsIgnoreCase("YES") || !Finalised.equalsIgnoreCase("NO")) {
            throw new IllegalArgumentException("Either YES or NO");
        }
        this.Finalised = Finalised;
    }

    String Project_phys_address;
    String ERF_number;
    Double Total_fee_charged;
    Double Total_paid_to_date;
    String Project_deadline;

    String Finalised = "";

    public Project(String Project_number, String Project_name, String Building_type, String Project_phys_address, String ERF_number, Double Total_fee_charged, Double Total_paid_to_date, String Project_deadline, String Finalised) {
        this.Project_number = Project_number;
        this.Project_name = Project_name;
        this.Building_type = Building_type;

        this.Project_phys_address = Project_phys_address;
        this.ERF_number = ERF_number;
        this.Total_fee_charged = Total_fee_charged;
        this.Total_paid_to_date = Total_paid_to_date;
        this.Project_deadline = Project_deadline;
        this.Finalised = Finalised;

    }

    public String getFinalised(String Finalised) {
        if (Finalised == "No") {
            return "No";
        } else {
            return "Yes";
        }

    }

    public String toString() { //Output
        String output = "Project information\n";
        output += "\nProject number: " + Project_number;
        output += "\nProject name: " + Project_name;
        output += "\nBuilding Type: " + Building_type;

        output += "\nProject physical address: " + Project_phys_address;
        output += "\nERF number: " + ERF_number;
        output += "\nTotal fee charged for project: R" + Total_fee_charged;
        output += "\nTotal amount paid to date: R" + Total_paid_to_date;
        output += "\nFinalised: " + this.getFinalised(Finalised);

        return output;
    }

}
