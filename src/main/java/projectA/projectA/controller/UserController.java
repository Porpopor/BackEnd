package projectA.projectA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.APIResponse;
import projectA.projectA.model.userModel.*;
import projectA.projectA.repository.UserRepository;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {
  public final UserRepository userRepository;
  public final UserBusiness userBusiness;
  public final UserMapper userMapper;

  public UserController(UserRepository userRepository, UserBusiness userBusiness, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userBusiness = userBusiness;
    this.userMapper = userMapper;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody UserLoginReq request) throws BaseException {
//    String response = userBusiness.Login(request);
    Object respones = userBusiness.LoginUser(request);
//    APIResponse apiResponse = new APIResponse();
//    apiResponse.setData(response);
    return ResponseEntity.ok(respones);
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody UserRegisterReq request)throws BaseException {
    Object response = userBusiness.register(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/profile")
  public ResponseEntity<Object>profile() throws BaseException {
    Object response = userBusiness.findById();
    return ResponseEntity.ok(response);
  }

  @PutMapping("/editUser")
  public ResponseEntity<Object> editUser(@RequestBody UserEditReq request) throws BaseException {
    Object response = userBusiness.editUserProfile(request);
    return ResponseEntity.ok(response);

  }

  @PutMapping("/change-email")
  public ResponseEntity<Object>changeEmail(@RequestBody UserEmailReq request) throws BaseException, MessagingException {
    Object response = userBusiness.changeEmail(request);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/change-Password")
  public ResponseEntity<Object>changePassword(@RequestBody UserChangePassword request) throws BaseException {
    Object response = userBusiness.ChangePassWord(request);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/reset-password")
  public ResponseEntity<Object>resetPassword(@RequestBody UserResetPassWord request) throws BaseException {
    Object response = userBusiness.resetPassWord(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/userForget-password")
  public ResponseEntity<Object> forgetPassword(@RequestBody UserForgetPassword request) throws BaseException, MessagingException {
    Object response = userBusiness.userForgetPassword(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/sentVerify-email")
  public ResponseEntity<Object> sentVerifyEmail() throws BaseException, MessagingException {
    Object response = userBusiness.sentVerifyEmail();
    return ResponseEntity.ok(response);
  }

  @PutMapping ("/verify-email")
  public ResponseEntity<Object> verifyEmail() throws BaseException {
    Object response = userBusiness.verifyEmail();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/check-role")
  public ResponseEntity<Object>checkRoleUser() throws BaseException {
    Object response = userBusiness.checkRoleUser();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/test-user")
  public Object getAllUser(){
    APIResponse apiResponse  = new APIResponse();
    apiResponse.setData(userBusiness.list());
    return apiResponse;
  }
}
