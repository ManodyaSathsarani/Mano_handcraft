package lk.ijse.mano_handcraft.dto.tm;

public class OrderManagementTM {
    private String order_id;
    private String customer_id;
    private String employee_id;
    private String order_date;
    private String total_amount;

    public OrderManagementTM() {
    }
    public OrderManagementTM(String order_id, String customer_id, String employee_id, String order_date, String total_amount) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}


