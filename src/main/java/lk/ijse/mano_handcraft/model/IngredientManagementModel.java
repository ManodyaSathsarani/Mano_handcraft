package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.CustomerManagementDto;
import lk.ijse.mano_handcraft.dto.IngredientManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientManagementModel {
    public boolean saveIngredient(IngredientManagementDto ingredientManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ("INSERT INTO ingredients (ingredient_id, ingredient_name, unit) VALUES ( ?,?,?)",
                ingredientManagementDto.getIngredient_Id(),
                ingredientManagementDto.getIngredient_Name(),
              ingredientManagementDto.getUnit());



    }

    public boolean updateIngredient(IngredientManagementDto ingredientManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ("UPDATE ingredients SET ingredient_name = ?, unit = ? WHERE ingredient_id = ?",
                ingredientManagementDto.getIngredient_Name(),
                ingredientManagementDto.getUnit(),
                ingredientManagementDto.getIngredient_Id());


    }
    public boolean deleteIngredient(String ingredient_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM ingredients WHERE ingredient_id = ?", ingredient_Id);


    }

    public ArrayList<IngredientManagementDto> getAllIngrediants() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM ingredients");
        ArrayList<IngredientManagementDto> ingredientManagement = new ArrayList<>();

        while (resultSet.next()){
            IngredientManagementDto ingredientManagementDto = new IngredientManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            ingredientManagement.add(ingredientManagementDto);
        }
        return ingredientManagement;
    }

    public String getNextIngredient_Id() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute ("SELECT ingredient_id FROM ingredients ORDER BY ingredient_id DESC LIMIT 1");
        char tableCharacter = 'I';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter+ "001";
    }
}
