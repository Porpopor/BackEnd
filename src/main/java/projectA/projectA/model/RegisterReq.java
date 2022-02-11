package projectA.projectA.model;

import lombok.Data;

@Data
public class RegisterReq {

  private String email;

  private String firstName;

  private String lastName;

  private String passWord;
}
