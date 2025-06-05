package lk.ijse.mano_handcraft.dto;


public class PaymentManagementDto {
    private String  payment_id;
    private String order_id;
    private String amount;
    private String method;
    private String status;
    private String payment_date;

    public PaymentManagementDto() {
    }

    public PaymentManagementDto(String payment_id, String order_id, String amount, String method, String status, String payment_date) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.payment_date = payment_date;

    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPayment_date() {
        return payment_date;
    }
    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }
}






