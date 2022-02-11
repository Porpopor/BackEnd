package projectA.projectA.service;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.repository.UserRepository;

import java.util.Objects;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User create(String email, String firstname, String lastname, String password) throws BaseException {
    if (Objects.isNull(email)) {
      throw UserException.createEmailNull();
    }
    if (firstname.equals("")&& lastname.equals("")&& password.equals("")){
      throw UserException.allFormNull();
    }
    if (Objects.isNull(firstname)|| firstname.equals("")){
      throw UserException.createFirstNameNull();
    }
    if (Objects.isNull(lastname)|| lastname.equals("")){
      throw UserException.createLastNameNull();
    }
    if (Objects.isNull(password)|| password.equals("")){
      throw UserException.createPasswordNull();
    }
    if(userRepository.existsByEmail(email)){
      throw UserException.createEmailDuplicated();
    }

    User entity = new User();
    entity.setEmail(email);
    entity.setFirstName(firstname);
    entity.setLastName(lastname);
    entity.setPassWord(password);

    return userRepository.save(entity);
  }
}
