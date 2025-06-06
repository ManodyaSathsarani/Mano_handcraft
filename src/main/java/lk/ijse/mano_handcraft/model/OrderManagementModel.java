package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.OrderManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManagementModel {
    public boolean saveOrder(OrderManagementDto orderManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orders (order_id, customer_id, employee_id,oder_date,total_amount,product_id,quantity,price) VALUES ( ?,?,?,?,?,?,?)",
                orderManagementDto.getOrder_id(),
                orderManagementDto.getCustomer_id(),
                orderManagementDto.getEmployee_id(),
                orderManagementDto.getOrder_date(),
                orderManagementDto.getTotal_amount(),
                orderManagementDto.getProduct_id(),
                orderManagementDto.getQuantity(),
                orderManagementDto.getPrice());




    }

    public boolean updateOrder(OrderManagementDto orderManagementDto ) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE orders SET customer_id = ?, employee_id = ?, oder_date = ?, total_amount = ? product_id=? quantity = ? price=? WHERE order_id = ?",
                orderManagementDto.getCustomer_id(),
                orderManagementDto.getEmployee_id(),
                orderManagementDto.getOrder_date(),
                orderManagementDto.getTotal_amount(),
                orderManagementDto.getProduct_id(),
                orderManagementDto.getQuantity(),
                orderManagementDto.getPrice(),
                orderManagementDto.getOrder_id());


    }

    public boolean deleteOrder(String order_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM orders WHERE order_id = ?", order_Id);
    }

    public ArrayList<OrderManagementDto> getAllOrder() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders");
        ArrayList<OrderManagementDto> orderManagement = new ArrayList<>();

        while (resultSet.next()){
             OrderManagementDto orderManagementDto = new OrderManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)

            );
            orderManagement.add(orderManagementDto);
        }
        return orderManagement;
    }

    public String getNextOrderId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        char tableCharacter = 'O';

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
