package lk.ijse.mano_handcraft.dto;

public class InventoryManagementDto {
    private String inventory_id;
    private String ingredient_id;
    private Double quantity_in_stock;
    private String last_updated;

    public InventoryManagementDto(String inventory_id,String ingredient_id,Double quantity_in_stock,String last_updated) {
        this.inventory_id = inventory_id;
        this.ingredient_id = ingredient_id;
        this.quantity_in_stock = quantity_in_stock;
        this.last_updated = last_updated;
    }
    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void getIngredient_id(String product_id) {
        this.ingredient_id = product_id;
    }

    public Double getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(Double quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}
