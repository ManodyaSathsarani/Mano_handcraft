package lk.ijse.mano_handcraft.dto.tm;

public class UserListTM {

    private String user_Id;
    private String name;
    private String userName;
    private String Password;
    private String role;
    private String registration_Date;

    private UserListTM(){

    }

    public UserListTM (String user_Id ,String name,String userName,String password,String role,String registration_Date){
        this.user_Id =user_Id;
        this.name=name;
        this.userName=userName;
        this.Password= password;
        this.role=role;
        this.registration_Date=registration_Date;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegistration_Date() {
        return registration_Date;
    }

    public void setRegistration_Date(String registration_Date) {
        this.registration_Date = registration_Date;
    }
}
