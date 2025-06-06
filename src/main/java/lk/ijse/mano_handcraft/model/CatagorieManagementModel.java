package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.CatagorieManagementDto;
import lk.ijse.mano_handcraft.dto.CustomerManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatagorieManagementModel {
    public boolean saveCtagorie(CatagorieManagementDto catagorieManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO categories (category_id, category_name) VALUES ( ?,?)",
                catagorieManagementDto.getCatagorie_id(),
                catagorieManagementDto.getCatagorie_name());

    }

    public boolean updateCategory(CatagorieManagementDto catagorieManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE categories SET category_name = ? WHERE category_id = ?",
                catagorieManagementDto.getCatagorie_name(),
                catagorieManagementDto.getCatagorie_id());


    }

    public boolean deleteCatagorie(String catagorie_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ("DELETE FROM categories WHERE category_id = ?", catagorie_Id);
    }

    public ArrayList<CatagorieManagementDto> getAllCatagaries() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM categories");
        ArrayList<CatagorieManagementDto> catagorieManagement = new ArrayList<>();

        while (resultSet.next()){
            CatagorieManagementDto customerManagementDto = new CatagorieManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            catagorieManagement.add(customerManagementDto);
        }
        return catagorieManagement;
    }

    public String getNextCatagorieId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT category_id FROM categories ORDER BY category_id DESC LIMIT 1");
        char tableCharacter = 'C';

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
