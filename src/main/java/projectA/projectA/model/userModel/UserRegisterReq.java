package projectA.projectA.model.userModel;

import lombok.Data;

@Data
public class UserRegisterReq {

  private String email;

  private String firstName;

  private String lastName;

  private String sex;

  private String passWord;

  private String phone;
}
