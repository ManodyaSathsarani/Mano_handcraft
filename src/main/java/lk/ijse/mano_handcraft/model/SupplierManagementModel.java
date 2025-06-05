package lk.ijse.mano_handcraft.model;

import lk.ijse.mano_handcraft.dto.SupplierManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierManagementModel {

    public boolean saveSupplier(SupplierManagementDto supplierManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO suppliers (supplier_id, supplier_name, contact_info) VALUES ( ?,?,?)",
                supplierManagementDto.getSupplier_id(),
                supplierManagementDto.getSupplier_name(),
                supplierManagementDto.getContact_info());

    }

    public boolean updateSuppliier(SupplierManagementDto supplierManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE suppliers SET supplier_name = ?, contact_info = ? WHERE supplier_id = ?",
                supplierManagementDto.getSupplier_id(),
                supplierManagementDto.getSupplier_name(),
                supplierManagementDto.getContact_info());


    }

    public boolean deleteSupplier(String supplier_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM suppliers WHERE supplier_Id = ?", supplier_Id);
    }

    public ArrayList<SupplierManagementDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM suppliers");
        ArrayList<SupplierManagementDto> supplierManagement = new ArrayList<>();

        while (resultSet.next()){
            SupplierManagementDto supplierManagementDto = new SupplierManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );

            supplierManagement.add(supplierManagementDto);
        }
        return supplierManagement;
    }

    public String getNextSupplierrId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT supplier_id FROM suppliers ORDER BY supplier_id DESC LIMIT 1");
        char tableCharacter = 'C';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter+ "1";
    }
}
