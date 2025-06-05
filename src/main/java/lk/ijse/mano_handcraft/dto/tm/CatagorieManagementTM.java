package lk.ijse.mano_handcraft.dto.tm;

public class CatagorieManagementTM {
        private String catagorie_id;
        private String catagorie_name;


        public CatagorieManagementTM() {
        }

        public CatagorieManagementTM(String catagorie_id, String catagorie_name) {
            this.catagorie_id = catagorie_id;
            this.catagorie_name = catagorie_name;

        }

        public String getCatagorie_id() {
            return catagorie_id;
        }

        public void setCatagorie_id(String catagorie_id) {
            this.catagorie_id = catagorie_id;
        }

        public String getCatagorie_name() {
            return catagorie_name;
        }

        public void setCatagorie_name(String catagorie_name) {
            this.catagorie_name = catagorie_name;
        }


    }

