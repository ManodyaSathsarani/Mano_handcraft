package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.OrderDetailsManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsManagementModel {
    public boolean saveOrderDetailsManagement(OrderDetailsManagementDto orderDetailsManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetails (orderdetails_id, order_id, product_id, quantity,price) VALUES (?, ?, ?, ?, ?)",
                orderDetailsManagementDto.getOrderdetail_id(),
                orderDetailsManagementDto.getOrder_id(),
                orderDetailsManagementDto.getProduct_id(),
                orderDetailsManagementDto.getQuantity(),
                orderDetailsManagementDto.getPrice());

    }

    public boolean updateOrderDetailsManagement(OrderDetailsManagementDto orderDetailsManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ("UPDATE orderdetails SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE orderdetails_id = ?",
                orderDetailsManagementDto.getOrder_id(),
                orderDetailsManagementDto.getProduct_id(),
                orderDetailsManagementDto.getQuantity(),
                orderDetailsManagementDto.getPrice(),
                orderDetailsManagementDto.getOrderdetail_id());

    }

    public boolean deleteOrderDetail(String orderDetail_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM orderdetails WHERE order_id = ?", orderDetail_Id);
    }

    public ArrayList<OrderDetailsManagementDto> getAllOrderdetail() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderdetails");
        ArrayList<OrderDetailsManagementDto> orderDetailsManagementDto = new ArrayList<>();

        while (resultSet.next()){
            OrderDetailsManagementDto customerManagementDto = new OrderDetailsManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
            orderDetailsManagementDto.add(customerManagementDto);


        }
        return orderDetailsManagementDto;
    }

    public String getNextOrderdetails() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT orderdetail_id FROM orderdetails ORDER BY orderdetail_id DESC LIMIT 1");
        char tableCharacter = 'O';

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
