package lk.ijse.mano_handcraft.dto.tm;

public class OrderDetailsManagementTM {
    private String orderdetail_id;
    private String order_id;
    private String product_id;
    private String quantity;
    private Double price;

    public OrderDetailsManagementTM() {
    }

    public OrderDetailsManagementTM(String orderdetail_id, String order_id, String product_id, String quantity, Double price) {
        this.orderdetail_id = orderdetail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderdetail_id() {
        return orderdetail_id;
    }

    public void setOrderdetail_id(String orderdetail_id) {
        this.orderdetail_id = orderdetail_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

