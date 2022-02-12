package projectA.projectA.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> findByEmail(String email){

    return userRepository.findByEmail(email);

  }



  public boolean matchPassword(String rawPassword, String endcodedPassword){
    return passwordEncoder.matches(rawPassword, endcodedPassword);
  }

  public User upDateFirstName(int id, String firstName , String lastName) throws UserException {

    Optional<User> edit = userRepository.findById(id);
    if (edit.isEmpty()){
      throw UserException.notFoundId();
    }
    User user = edit.get();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    return userRepository.save(user);

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
    entity.setPassWord(passwordEncoder.encode(password));

    return userRepository.save(entity);
  }
}
