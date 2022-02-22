package projectA.projectA.model.userModel;


import lombok.Data;

@Data
public class ForgetPassword {

    private String email;

    private String subject = "Project-backend-Krissakorn";

    private String body = "<a href=\"http://localhost:4200/change-password\">ResetPassWord</a>";
}
