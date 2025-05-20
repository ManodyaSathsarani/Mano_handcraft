package lk.ijse.mano_handcraft.dto.tm;

public class CustomerManagementTM {
    private String customer_Id;
    private String name;
    private String phone;
    private String address;

    public CustomerManagementTM(String customer_Id, String name, String phone, String address) {
        this.customer_Id = customer_Id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}




