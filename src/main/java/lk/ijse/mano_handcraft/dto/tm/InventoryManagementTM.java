package lk.ijse.mano_handcraft.dto.tm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InventoryManagementTM {
    private String inventory_id;
    private String ingredient_id;
    private Double quantity_in_stock;
    private String last_updated;

    public InventoryManagementTM(String inventory_id,String ingredient_id,Double quantity_in_stock,String last_updated) {
        this.inventory_id = inventory_id;
        this.ingredient_id = ingredient_id;
        this.quantity_in_stock = quantity_in_stock;
        this.last_updated = last_updated;
    }


}
