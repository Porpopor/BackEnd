package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.APIResponse;
import projectA.projectA.model.userModel.*;
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
  public ResponseEntity<Object> login(@RequestBody LoginReq request) throws UserException {
//    String response = userBusiness.Login(request);
    Object respones = userBusiness.Login(request);
//    APIResponse apiResponse = new APIResponse();
//    apiResponse.setData(response);
    return ResponseEntity.ok(respones);
  }

  @PostMapping("/register")
  public ResponseEntity<APIResponse> register(@RequestBody RegisterReq request)throws BaseException {
    RegisterResponse response = userBusiness.register(request);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(response);
    return ResponseEntity.ok(apiResponse);
  }

  @PostMapping("/editUser")
  public ResponseEntity<Object> editUser(@RequestBody UserEditReq request) throws BaseException {
    Object response = userBusiness.editProfile(request);
//    APIResponse apiResponse = new APIResponse();
//    apiResponse.setData(response);
    return ResponseEntity.ok(response);

  }

  @GetMapping("/test-user")
  public Object getAllUser(){
    APIResponse apiResponse  = new APIResponse();
    apiResponse.setData(userBusiness.list());
    return apiResponse;
  }
}
