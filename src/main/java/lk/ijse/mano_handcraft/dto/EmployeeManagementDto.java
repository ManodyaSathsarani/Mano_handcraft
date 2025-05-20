package lk.ijse.mano_handcraft.dto;

import javax.management.relation.Role;

public class EmployeeManagementDto {
    private String employee_Id;
    private String name;
    private String role;
    private String hire_date;
    private String phone;
    private String address;

    private EmployeeManagementDto(){

    }

    public EmployeeManagementDto (String employee_Id ,String name ,String role,String hire_date,String phone,String address){
        this.employee_Id =employee_Id;
        this.name=name;
        this.role=role;
        this.hire_date=hire_date ;
        this.phone=phone;
        this.address=address;
    }

    public String getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(String Employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        hire_date = hire_date;
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
