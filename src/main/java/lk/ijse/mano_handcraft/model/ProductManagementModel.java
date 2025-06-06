package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.PaymentManagementDto;
import lk.ijse.mano_handcraft.dto.ProductManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductManagementModel {
    public boolean saveProduct(ProductManagementDto productManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO products (product_id, product_name, category_id, price,description) VALUES ( ?,?,?,?,?)",
                productManagementDto.getProduct_id(),
                productManagementDto.getProduct_name(),
                productManagementDto.getCatagorie_id(),
                productManagementDto.getPrice(),
                productManagementDto.getDescription());

    }

    public boolean updateProduct(ProductManagementDto productManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ("UPDATE products SET product_name = ?, category_id = ?, price = ?, description = ? WHERE product_id = ?",
                productManagementDto.getProduct_name(),
                productManagementDto.getCatagorie_id(),
                productManagementDto.getPrice(),
                productManagementDto.getDescription(),
                productManagementDto.getProduct_id());


    }

    public boolean deleteProduct(String product_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM products WHERE product_id = ?", product_Id);
    }

    public ArrayList<ProductManagementDto> getAllProduct() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM products");
        ArrayList<ProductManagementDto> productManagement = new ArrayList<>();

        while (resultSet.next()){
            ProductManagementDto productManagementDto = new ProductManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            );
            productManagement.add(productManagementDto);
        }
        return productManagement;
    }

    public String getNextProductId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT product_id FROM products ORDER BY product_id DESC LIMIT 1");
        char tableCharacter = 'P';

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
