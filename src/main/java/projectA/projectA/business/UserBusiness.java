package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.RegisterReq;
import projectA.projectA.service.UserService;

@Service
public class UserBusiness {

  public final UserService userService;

  public UserBusiness(UserService userService) {
    this.userService = userService;
  }

  public User register(RegisterReq req)throws BaseException{
    User user = userService.create(req.getEmail(),req.getFirstName(),req.getLastName(),req.getPassWord());
    return user;
  }

}
