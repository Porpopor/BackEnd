package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.entity.UserProfile;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.APIResponse;
import projectA.projectA.model.userModel.*;
import projectA.projectA.service.TokenService;
import projectA.projectA.service.UserProfileService;
import projectA.projectA.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

  private final UserService userService;
  private final UserMapper userMapper;
  private final UserProfileService userProfileService;
  private final TokenService tokenService;

  public UserBusiness(UserService userService, UserMapper userMapper, UserProfileService userProfileService, TokenService tokenService) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.userProfileService = userProfileService;
    this.tokenService = tokenService;
  }


  public String Login(LoginReq request) throws UserException {

    Optional<User> byEmail = userService.findByEmail(request.getEmail());
    if (byEmail.isEmpty()) {
      throw UserException.loginFailEmailNotFound();
    }
    User user = byEmail.get();
    if (!userService.matchPassword(request.getPassWord(), user.getPassWord())) {
      throw UserException.loginFailPasswordIncorrect();
    }
    String tokenize = tokenService.tokenize(user);
    System.out.printf(tokenize);
    return tokenize;
  }

  public RegisterResponse register(RegisterReq request) throws BaseException {
    User user = userService.create(request.getEmail(), request.getFirstName(), request.getLastName(), request.getPassWord());
    return userMapper.toRegisterResponse(user);
  }

  public UserEditResponse editUser(UserEditReq request) throws BaseException {
    User user = userService.upDateProfile(request.getId(),request.getFirstName(),request.getLastName(),request.getPhone(),request.getNameCompany());
    return userMapper.UserEditToRegisterResponse(user);
  }

  public UserProfile userProfile(UserProfileReq request) throws BaseException {

    Optional<User> byId = userService.findById(request.getId());
    if (byId.isEmpty()) {
      throw UserException.notFoundId();
    }

    return userProfileService.userProfileCreate(byId.get(),request.getPhone(),request.getNameCompany());

  }

  public List<UserProfileResponse> list(){
    List<User> all = userService.findAll();
    List<UserProfileResponse> userProfileResponse = userMapper.UserProfileToUserProfileResponse(all);
    return userProfileResponse;
  }

}
