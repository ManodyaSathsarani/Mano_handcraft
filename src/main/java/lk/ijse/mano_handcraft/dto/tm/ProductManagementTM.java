package lk.ijse.mano_handcraft.dto.tm;

public class ProductManagementTM {
    private String product_id;
    private String product_name;
    private String catagorie_id;
    private Double price;
    private String description;

    public ProductManagementTM() {
    }
    public ProductManagementTM(String product_id, String product_name, String catagorie_id, Double price, String description) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.catagorie_id = catagorie_id;
        this.price = price;
        this.description = description;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCatagorie_id() {
        return catagorie_id;
    }

    public void setCatagorie_id(String catagorie_id) {
        this.catagorie_id = catagorie_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



