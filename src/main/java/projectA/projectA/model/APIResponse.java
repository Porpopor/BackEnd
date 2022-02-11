package projectA.projectA.model;

import lombok.Data;

@Data
public class APIResponse {

  private int status;

  private String massage;

  private Object data;

}
