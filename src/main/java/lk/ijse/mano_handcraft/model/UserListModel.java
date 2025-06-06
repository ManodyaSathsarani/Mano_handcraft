package lk.ijse.mano_handcraft.model;

import lk.ijse.mano_handcraft.dto.UserListDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserListModel {
    public boolean saveUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO users(user_id,username,password,role,Registration_date) VALUES ( ?,?,?,?,?)",
                userListDto.getUser_Id(),
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getRole(),
                userListDto.getRegistration_Date());


    }

    public boolean updateUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE users SET username = ?, password = ?, role = ?, Registration_date = ? WHERE user_Id = ?",
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getRole(),
                userListDto.getRegistration_Date(),
                userListDto.getUser_Id());

    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM users WHERE user_Id = ?", userId);
    }

    public ArrayList<UserListDto> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM users");
        ArrayList<UserListDto> userList = new ArrayList<>();

        while (resultSet.next()){
            UserListDto userListDto = new UserListDto(
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
        return tableCharacter+ "001";
    }
}
