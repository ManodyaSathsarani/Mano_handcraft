package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.EmployeeManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeMnagementModel {
    public boolean saveEmployee(EmployeeManagementDto employeeManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employees (employee_id, employee_name, role, hire_date,phone,address) VALUES ( ?,?,?,?,?,?)",
                employeeManagementDto.getEmployee_Id(),
                employeeManagementDto.getName(),
                employeeManagementDto.getRole(),
                employeeManagementDto.getHire_date(),
                employeeManagementDto.getPhone(),
                employeeManagementDto.getAddress());
    }

    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE employees SET employee_name = ?, role = ?, hire_date = ?, phone = ?, address = ? WHERE employee_id = ?",
                employeeManagementDto.getName(),
                employeeManagementDto.getRole(),
                employeeManagementDto.getHire_date(),
                employeeManagementDto.getPhone(),
                employeeManagementDto.getAddress());

    }

    public boolean deleteEmployee(String employee_Id) throws SQLException, ClassNotFoundException {;
        return CrudUtil.execute("DELETE FROM employees WHERE employee_id = ?", employee_Id);
    }

    public ArrayList<EmployeeManagementDto> getAllemployee() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employees");
        ArrayList<EmployeeManagementDto> employeeManagement = new ArrayList<>();

        while (resultSet.next()){
            EmployeeManagementDto customerManagementDto = new EmployeeManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );
            employeeManagement.add(customerManagementDto);

        }
        return employeeManagement;


    }

    public String getEmployeeId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT employee_id FROM employees ORDER BY employee_id DESC LIMIT 1");
        char tableCharacter = 'E';

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
