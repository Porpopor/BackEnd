package projectA.projectA.model.userModel;


import lombok.Data;

@Data
public class ForgetPassword {

    private String email;

    private String subject = "Project-backend-Krissakorn";
}
