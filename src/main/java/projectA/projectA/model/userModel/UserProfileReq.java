package projectA.projectA.model.userModel;

import lombok.Data;
import projectA.projectA.entity.User;

@Data
public class UserProfileReq {

  private int id;
  private String phone;
  private String nameCompany;
}
