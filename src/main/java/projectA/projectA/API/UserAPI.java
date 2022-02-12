package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.model.userModel.*;
import projectA.projectA.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserAPI {
  public final UserRepository userRepository;
  public final UserBusiness userBusiness;

  public UserAPI(UserRepository userRepository, UserBusiness userBusiness) {
    this.userRepository = userRepository;
    this.userBusiness = userBusiness;
  }

  @PostMapping("/login")
  public ResponseEntity<String >login(@RequestBody LoginReq request) throws UserException {
    String response = userBusiness.Login(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterReq request)throws BaseException {
    RegisterResponse response = userBusiness.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/editUser")
  public ResponseEntity<UserEditResponse>editUser(@RequestBody UserEditReq request) throws UserException {
    UserEditResponse response = userBusiness.editUser(request);
    return ResponseEntity.ok(response);

  }
}
