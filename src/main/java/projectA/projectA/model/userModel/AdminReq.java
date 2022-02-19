package projectA.projectA.model.userModel;

import lombok.Data;
import projectA.projectA.entity.User;

import java.util.Date;

@Data
public class AdminReq {

  private String email = "admin@admin.com";

  private String firstName = "admin";

  private String lastName = "admin";

  private String passWord ="admin";

  private Date date;

  private User.Role role = User.Role.ADMIN;
}
