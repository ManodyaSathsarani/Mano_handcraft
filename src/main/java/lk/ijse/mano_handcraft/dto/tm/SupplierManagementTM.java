package lk.ijse.mano_handcraft.dto.tm;

public class SupplierManagementTM {
    private String supplier_id;
    private String supplier_name;
    private String contact_info;

    public SupplierManagementTM() {
    }
    public SupplierManagementTM(String supplier_id, String supplier_name, String contact_info) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.contact_info = contact_info;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }
}

