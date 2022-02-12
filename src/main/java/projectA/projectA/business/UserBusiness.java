package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.mapper.UserMapper;
import projectA.projectA.model.userModel.*;
import projectA.projectA.service.UserService;

import java.util.Optional;

@Service
public class UserBusiness {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserBusiness(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
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
    String token = "JWT TO DO";

    return token;
  }

  public RegisterResponse register(RegisterReq request) throws BaseException {
    User user = userService.create(request.getEmail(), request.getFirstName(), request.getLastName(), request.getPassWord());
    return userMapper.toRegisterResponse(user);
  }

  public UserEditResponse editUser(UserEditReq request) throws UserException {
    User user = userService.upDateFirstName(request.getId(),request.getFirstName(),request.getLastName());
    return userMapper.UserEditToRegisterResponse(user);
  }

}
