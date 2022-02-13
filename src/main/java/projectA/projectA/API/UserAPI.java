package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.entity.User;
import projectA.projectA.entity.UserProfile;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.model.userModel.*;
import projectA.projectA.repository.UserRepository;

import java.util.List;

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
  public ResponseEntity<UserEditResponse>editUser(@RequestBody UserEditReq request) throws BaseException {
    UserEditResponse response = userBusiness.editUser(request);
    return ResponseEntity.ok(response);

  }

  @PostMapping("/profile")
  public ResponseEntity<UserProfile >profile(@RequestBody UserProfileReq request) throws BaseException {
    UserProfile response = userBusiness.userProfile(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/test-user")
  public List<User> getAllUser(){
    List<User> all = (List<User>) this.userRepository.findAll();
    return all;
  }
}
