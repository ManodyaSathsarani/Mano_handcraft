package lk.ijse.mano_handcraft.model;

import lk.ijse.mano_handcraft.dto.CustomerManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementModel {
    public boolean saveCustomer(CustomerManagementDto customerManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customers(customer_id,customer_name,phone,address) VALUES ( ?,?,?,?,?,?)",
                customerManagementDto.getCustomer_Id(),
                customerManagementDto.getName(),
                customerManagementDto.getPhone(),
                customerManagementDto.getAddress());
    }

    public boolean updateCustomer(CustomerManagementDto customerManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customers SET customer_name = ?, phone = ?, address = ? WHERE customer_id = ?",
                customerManagementDto.getName(),
                customerManagementDto.getPhone(),
                customerManagementDto.getAddress(),
                customerManagementDto.getCustomer_Id());

    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM users WHERE user_Id = ?", userId);
    }

    public ArrayList<CustomerManagementDto> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM users");
        ArrayList<CustomerManagementDto> userList = new ArrayList<>();

        while (resultSet.next()){
            CustomerManagementDto userListDto = new UserListDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            userList.add(userListDto);
        }
        return userList;
    }

    public String getNextUserId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
        char tableCharacter = 'U';

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
