
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Capture either the Architect, Contractor or the Customer details
 * 
 * @author Bonga
 *
 */
class Customer {

    String person_type, surname, email, name, street_name, house_no, postal_code, province, phone;

    Customer(String surname, String person_type, String name, String street_name, String house_no, String postal_code, String province, String phone) {
        setPerson_type(person_type);
        setSurname(surname);
        setEmail(email);
        setHouse_no(house_no);
        setPhone(phone);
        setPostal_code(postal_code);
        setProvince(province);
        setName(name);
    }

    public String getStreet_name() {
        return street_name;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getProvince() {
        return province;
    }

    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    Customer(String surname, String email, String name, String address, String phone, double amount, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        if (phone.length() > 10 || phone.length() < 10) {
            throw new IllegalArgumentException("phone number should contain 10 digits");
        }

        this.phone = phone;
    }

    public Customer() {
    }

}
