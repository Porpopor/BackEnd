package projectA.projectA.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.model.RegisterReq;
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

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterReq user)throws BaseException {
    User response = userBusiness.register(user);
    return ResponseEntity.ok(response);
  }
}
