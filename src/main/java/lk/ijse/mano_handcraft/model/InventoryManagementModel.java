package lk.ijse.mano_handcraft.model;

import lk.ijse.mano_handcraft.dto.InventoryManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryManagementModel {

    public boolean saveInventory(InventoryManagementDto inventoryManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO inventory (inventory_id, ingredient_id, quantity_in_stock, last_updated) VALUES (?, ?, ?, ?)",
                inventoryManagementDto.getInventory_id(),
                inventoryManagementDto.getIngredient_id(),
                inventoryManagementDto.getQuantity_in_stock(),
                inventoryManagementDto.getLast_updated());
    }

    public boolean updateInventory(InventoryManagementDto inventoryManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE inventory SET ingredient_id = ?, quantity_in_stock = ?, last_updated = ? WHERE inventory_id = ?",
                inventoryManagementDto.getIngredient_id(),
                inventoryManagementDto.getQuantity_in_stock(),
                inventoryManagementDto.getLast_updated(),
                inventoryManagementDto.getInventory_id());
    }

    public boolean deleteInventory(String inventoryId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM inventory WHERE inventory_id = ?", inventoryId);
    }

    public ArrayList<InventoryManagementDto> getAllInventory() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM inventory");
        ArrayList<InventoryManagementDto> inventoryList = new ArrayList<>();

        while (resultSet.next()) {
            InventoryManagementDto inventoryManagementDto = new InventoryManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );
            inventoryList.add(inventoryManagementDto);
        }
        return inventoryList;
    }

    public String getLatestInventoryId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT inventory_id FROM inventory ORDER BY inventory_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

}