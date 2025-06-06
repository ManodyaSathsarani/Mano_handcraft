package lk.ijse.mano_handcraft.dto;

public class OrderManagementDto {
    private String order_id;
    private String customer_id;
    private String employee_id;
    private String order_date;
    private String total_amount;
    private String product_id;
    private String quantity;
    private String price;

    public OrderManagementDto(String order_id, String customer_id, String employee_id, String order_date, String total_amount, String product_id, String quantity, String price) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}



