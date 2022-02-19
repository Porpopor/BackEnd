package projectA.projectA.model.userModel;

import lombok.Data;

@Data
public class UserEditReq {

  private int id;
  private String firstName;
  private String lastName;
  private String oldPassWord;
  private String newPassWord;
  private String phone;
  private String nameCompany;

}
