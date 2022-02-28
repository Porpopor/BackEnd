package projectA.projectA.model.userModel;

import lombok.Data;

@Data
public class UserProfileResponse {

  private String email;

  private String firstName;

  private String lastName;

  private String phone;

  private String picture;

  private Integer verifyEmail;

  private String role;

}
