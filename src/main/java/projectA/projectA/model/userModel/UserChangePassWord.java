package projectA.projectA.model.userModel;

import lombok.Data;

@Data
public class UserChangePassWord {

  private String oldPassWord;
  private String newPassWord;
}
