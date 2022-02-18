package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.entity.UserProfile;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.APIResponse;
import projectA.projectA.model.userModel.*;
import projectA.projectA.repository.UserProfileRepository;
import projectA.projectA.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserAPI {
  public final UserRepository userRepository;
  public final UserBusiness userBusiness;
  public final UserMapper userMapper;

  public UserAPI(UserRepository userRepository, UserBusiness userBusiness, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userBusiness = userBusiness;
    this.userMapper = userMapper;
  }

  @PostMapping("/login")
  public ResponseEntity<APIResponse> login(@RequestBody LoginReq request) throws UserException {
    String response = userBusiness.Login(request);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(response);
    return ResponseEntity.ok(apiResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<APIResponse> register(@RequestBody RegisterReq request)throws BaseException {
    RegisterResponse response = userBusiness.register(request);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(response);
    return ResponseEntity.ok(apiResponse);
  }

  @PostMapping("/editUser")
  public ResponseEntity<APIResponse> editUser(@RequestBody UserEditReq request) throws BaseException {
    UserEditResponse response = userBusiness.editUser(request);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(response);
    return ResponseEntity.ok(apiResponse);

  }

  @PostMapping("/profile")
  public ResponseEntity<APIResponse> profile(@RequestBody UserProfileReq request) throws BaseException {
    UserProfile response = userBusiness.userProfile(request);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(response);
    return ResponseEntity.ok(apiResponse);
  }

  @GetMapping("/test-user")
  public Object getAllUser(){
    APIResponse apiResponse  = new APIResponse();
    apiResponse.setData(userBusiness.list());
    return apiResponse;
  }
}
