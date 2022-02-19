package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.Response;
import projectA.projectA.model.userModel.*;
import projectA.projectA.service.TokenService;
import projectA.projectA.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

  private final UserService userService;
  private final UserMapper userMapper;
  private final TokenService tokenService;

  public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.tokenService = tokenService;
  }


  public Object Login(LoginReq request) throws UserException {

    Optional<User> byEmail = userService.findByEmail(request.getEmail());
    if (byEmail.isEmpty()) {
      throw UserException.loginFailEmailNotFound();
    }
    User user = byEmail.get();
    if (!userService.matchPassword(request.getPassWord(), user.getPassWord())) {
      throw UserException.loginFailPasswordIncorrect();
    }
    String tokenize = tokenService.tokenize(user);
//    System.out.printf(tokenize);
    return new Response().ok("Login success","token",tokenize);
  }

  public RegisterResponse register(RegisterReq request) throws BaseException {
    User user = userService.create(request.getEmail(), request.getFirstName(), request.getLastName(), request.getPassWord());
    return userMapper.toRegisterResponse(user);
  }

  public Object editProfile(UserEditReq request) throws BaseException {
    User user = tokenService.getUserByIdToken();
    if (request.getFirstName().isEmpty()||request.getFirstName().equals("")||request.getFirstName().contains(" ")){
      throw UserException.FirstNameNull();
    }
    if (request.getLastName().isEmpty()||request.getLastName().equals("")||request.getLastName().contains(" ")){
      throw UserException.LastNameNull();
    }
    if (!userService.matchPassword(request.getOldPassWord(),user.getPassWord())){
      throw UserException.passwordIncorrect();
    }

    userService.upDateProfile(user,request.getFirstName(),request.getLastName(),request.getNewPassWord(),request.getPhone(),request.getNameCompany());
    return new Response().success("แก้ไขสำเร็จ");
  }

  public List<UserProfileResponse> list(){
    List<User> all = userService.findAll();
    List<UserProfileResponse> userProfileResponse = userMapper.UserProfileToUserProfileResponse(all);
    return userProfileResponse;
  }

}
