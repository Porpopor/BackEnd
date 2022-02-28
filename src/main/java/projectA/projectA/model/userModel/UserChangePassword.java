package projectA.projectA.model.userModel;

import lombok.Data;

@Data
public class UserChangePassword {

  private String oldPassWord;
  private String newPassWord;
}
