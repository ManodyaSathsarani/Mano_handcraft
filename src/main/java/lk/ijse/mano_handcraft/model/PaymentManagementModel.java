package lk.ijse.mano_handcraft.model;


import lk.ijse.mano_handcraft.dto.OrderManagementDto;
import lk.ijse.mano_handcraft.dto.PaymentManagementDto;
import lk.ijse.mano_handcraft.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentManagementModel {
    public boolean savePayment(PaymentManagementDto paymentManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customers (payment_id, order_id, amount, method,status,payment_date) VALUES ( ?,?,?,?,?,?)",
                paymentManagementDto.getPayment_id(),
                paymentManagementDto.getOrder_id(),
                paymentManagementDto.getAmount(),
                paymentManagementDto.getMethod(),
                paymentManagementDto.getStatus(),
                paymentManagementDto.getPayment_date());
    }

    public boolean updatePayment(PaymentManagementDto paymentManagementDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE payment SET order_id = ?, amount = ?, method = ?, status = ?, payment_date = ? WHERE payment_id = ?",
                paymentManagementDto.getOrder_id(),
                paymentManagementDto.getAmount(),
                paymentManagementDto.getMethod(),
                paymentManagementDto.getStatus(),
                paymentManagementDto.getPayment_date(),
                paymentManagementDto.getPayment_id());

    }

    public boolean deletePayment(String payment_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM payment WHERE payment_id = ?", payment_Id);
    }

    public ArrayList<PaymentManagementDto> getAllPayment() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment");
        ArrayList<PaymentManagementDto> paymentManagement = new ArrayList<>();

        while (resultSet.next()){
            PaymentManagementDto paymentManagementDto = new PaymentManagementDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            paymentManagement.add(paymentManagementDto);
        }
        return paymentManagement;
    }

    public String getNextPaymentId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
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
